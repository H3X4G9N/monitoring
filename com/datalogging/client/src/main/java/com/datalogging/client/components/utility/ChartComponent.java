package com.datalogging.client.components.utility;

import com.datalogging.client.Controller;
import com.datalogging.client.components.Component;
import com.datalogging.client.model.Data;
import com.datalogging.client.model.DataLogger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

enum SelectionMode {
    MINUTE,
    HOUR,
    DAY,
    WEEK,
    MONTH,
    YEAR
}

public class ChartComponent extends Component {
    private DataLogger dataLogger;
    private XYChart.Series series;
    private LineChart lineChart;
    public Button loadButton;

    public ChartComponent(Controller controller, DataLogger dataLogger) {
        super(controller);
        this.dataLogger = dataLogger;

        NumberAxis xAxis = new NumberAxis(1960, 2020, 10);
        xAxis.setLabel("Time");

        NumberAxis yAxis = new NumberAxis(0, 350, 50);
        yAxis.setLabel("Carbon Dioxide");

        lineChart = new LineChart(xAxis, yAxis);

        series = new XYChart.Series();

        DatePicker date = new DatePicker();

        loadButton = new Button();
        loadButton.setText("Load");
        loadButton.setOnAction(new LoadHandler());

        this.getContainer().getChildren().add(lineChart);
        this.getContainer().getChildren().add(date);
        this.getContainer().getChildren().add(loadButton);
    }

    public void loadData(SelectionMode selectionMode) {
        switch (selectionMode) {
            case DAY:
                break;
            case HOUR:
                break;
            case WEEK:
                break;
            case YEAR:
                break;
            case MONTH:
                break;
            case MINUTE:
                break;
        }
        /*
        series.getData().add(new XYChart.Data(1970, 15));
        series.getData().add(new XYChart.Data(1980, 30));
        series.getData().add(new XYChart.Data(1990, 60));
        series.getData().add(new XYChart.Data(2000, 120));
        series.getData().add(new XYChart.Data(2013, 240));
        series.getData().add(new XYChart.Data(2014, 300));

        //Setting the data to Line chart
        lineChart.getData().add(series);

        XYChart.Series series2 = new XYChart.Series();
        series.setName("Temperature");

        series2.getData().add(new XYChart.Data(1970, 20));
        series2.getData().add(new XYChart.Data(1980, 70));
        series2.getData().add(new XYChart.Data(1990, 30));
        series2.getData().add(new XYChart.Data(2000, 70));
        series2.getData().add(new XYChart.Data(2013, 300));
        series2.getData().add(new XYChart.Data(2014, 260));
        series2.setName("Humidity");
        lineChart.getData().add(series2);
         */
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Latvia"));
        List<Data> dataList = Data.getAllFromDataLogger(getController().getUser(), dataLogger.getID());

        for (Data data : dataList) {
            Date timestamp = data.getTimestamp();
            cal.setTime(timestamp);

            //System.out.println(cal.get(Calendar.MINUTE));
        }
    }

    private class LoadHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {

        }
    }
}
