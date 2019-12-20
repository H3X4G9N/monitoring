package com.example.monitoring;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.monitoring.model.Data;
import com.example.monitoring.model.DataLogger;
import com.example.monitoring.model.GroupAuthentication;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DataLoggerInfoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    DataLogger currentDataLogger;
    List<Data> dataLoggersData;
    Data latestData;
    GroupAuthentication currentgroupAuthentication;

    TextView dataLoggerName, dataLoggerDescription, latesttemperature, latesthumidity, latestco2, startdate, enddate;
    Date startdatevar, enddatevar, temp;
    boolean startdatechosen, enddatechosen;
    LineGraphSeries<DataPoint> series;
    GraphView temperaturegraph, humiditygraph, co2graph;
    double x, y;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_logger_info);

        Intent DCIntent = getIntent();
        currentDataLogger = (DataLogger) DCIntent.getSerializableExtra("ChosenDataLogger");
        currentgroupAuthentication = (GroupAuthentication) DCIntent.getSerializableExtra("authorize");

        latestData = Data.getLatestFromDataLogger(currentDataLogger, currentgroupAuthentication);

        dataLoggerName = (TextView)findViewById(R.id.current_data_logger_name);
        dataLoggerName.setText(currentDataLogger.getName());

        dataLoggerDescription = (TextView)findViewById(R.id.current_data_logger_description);
        dataLoggerDescription.setText(currentDataLogger.getDescription());

        latesttemperature = (TextView)findViewById(R.id.data_logger_last_temperature);
        if(latestData != null)
        latesttemperature.setText(new DecimalFormat("##.##").format(latestData.getTemperature()) + "°C");

        latesthumidity = (TextView)findViewById(R.id.data_logger_last_humidity);
        if(latestData != null)
        latesthumidity.setText(new DecimalFormat("##.##").format(latestData.getHumidity()) + "%");

        latestco2 = (TextView)findViewById(R.id.data_logger_last_co2);
        if(latestData != null)
        latestco2.setText(new DecimalFormat("##.##").format(latestData.getCarbonDioxide()) + "ppm");

        startdate = (TextView)findViewById(R.id.start_date_view);
        enddate = (TextView)findViewById(R.id.end_date_view);


    }

    public void pickStartDate(View view){
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "Start date picker");
        startdatechosen = true;
    }

    public void pickEndDate(View view){
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "End date picker");
        enddatechosen = true;
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);


        if(startdatechosen == true) {
            startdate.setText(DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(c.getTime()));
            startdatevar = c.getTime();
            startdatechosen = false;
        }
        if(enddatechosen == true){
            enddate.setText(DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(c.getTime()));
            enddatevar = c.getTime();
            enddatechosen = false;
        }

    }



    public void reloadData(View view){

        latestData = Data.getLatestFromDataLogger(currentDataLogger, currentgroupAuthentication);

        latesttemperature.setText(new DecimalFormat("##.##").format(latestData.getTemperature()) + "°C");

        latesthumidity.setText(new DecimalFormat("##.##").format(latestData.getHumidity()) + "%");

        latestco2.setText(new DecimalFormat("##.##").format(latestData.getCarbonDioxide()) + "ppm");

    }



    public void loadDataForGraph(View view){

        if(enddatevar.before(startdatevar)){

            temp = startdatevar;
            startdatevar = enddatevar;
            enddatevar = temp;
            startdate.setText(DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(startdatevar));
            enddate.setText(DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(enddatevar));

        }


        dataLoggersData = Data.getAllFromDataLogger(currentDataLogger.getID(), currentgroupAuthentication, startdatevar, enddatevar);

        // Temperature
        x = 0;
        temperaturegraph = (GraphView) findViewById(R.id.temperature_graph);

        temperaturegraph.removeSeries(series);
        series = new LineGraphSeries<DataPoint>();
        temperaturegraph.setTitle("Temperature");
        for(int i = 0; i < dataLoggersData.size(); i++){
            if(dataLoggersData.get(i).getTemperature() != 0) {
                x = dataLoggersData.get(i).getTimestamp().getTime();
                y = dataLoggersData.get(i).getTemperature();
                series.appendData(new DataPoint(x, y), true, 20);
            }
        }
        temperaturegraph.addSeries(series);


        // Humidity
        x = 0;
        humiditygraph = (GraphView) findViewById(R.id.humidity_graph);

        humiditygraph.removeSeries(series);
        series = new LineGraphSeries<DataPoint>();
        humiditygraph.setTitle("Humidity");

        for(int i = 0; i < dataLoggersData.size(); i++){
            x = dataLoggersData.get(i).getTimestamp().getTime();
            y = dataLoggersData.get(i).getHumidity();
            series.appendData(new DataPoint(x, y), true, 20);
        }
        humiditygraph.addSeries(series);


        // co2
        x = 0;
        co2graph = (GraphView) findViewById(R.id.co2_graph);

        co2graph.removeSeries(series);
        series = new LineGraphSeries<DataPoint>();
        co2graph.setTitle("Humidity");

        for(int i = 0; i < dataLoggersData.size(); i++){
            x = dataLoggersData.get(i).getTimestamp().getTime();
            y = dataLoggersData.get(i).getCarbonDioxide();
            series.appendData(new DataPoint(x, y), true, 20);
        }
        co2graph.addSeries(series);
    }


}
