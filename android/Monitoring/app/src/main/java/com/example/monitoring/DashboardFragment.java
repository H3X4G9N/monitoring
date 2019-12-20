package com.example.monitoring;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.monitoring.model.Data;
import com.example.monitoring.model.DataLogger;
import com.example.monitoring.model.User;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {


    Data latestData;
    Context context = getActivity();
    List<DataLogger> dataloggers;
    DataLogger dashboardDC;
    TextView dashboardLatestTempereature, dashboardLatestHumidity, DashboardLatestco2, currentActiveDataCollecor;
    TextView dashboardLatestTempereatureTemplate, dashboardLatestHumidityTemplate, DashboardLatestco2Template;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View dashboardview = inflater.inflate(R.layout.fragment_dashboard, container, false);

        dataloggers = new ArrayList<DataLogger>();

        User dashboarduser = (User) getArguments().getSerializable("DashboardUser");
        dataloggers = DataLogger.getAllFromUser(dashboarduser);
        if(dataloggers != null && dataloggers.isEmpty() == false) {

            for(int i = 0; i < dataloggers.size(); i++) {
                if(dataloggers.get(i) != null)
                dashboardDC = dataloggers.get(i);
            }

            latestData = Data.getLatestFromDataLogger(dashboarduser, dashboardDC);

            dashboardLatestTempereatureTemplate = (TextView) dashboardview.findViewById(R.id.title_temperature_template);
            dashboardLatestTempereatureTemplate.setText(dashboardDC.getName() + " latest temperature");

            dashboardLatestTempereature = (TextView) dashboardview.findViewById(R.id.temperature);
            if(latestData != null)
            dashboardLatestTempereature.setText(new DecimalFormat("##.##").format(latestData.getTemperature()) + "°C");

            dashboardLatestHumidityTemplate = (TextView) dashboardview.findViewById(R.id.title_humidity_template);
            dashboardLatestHumidityTemplate.setText(dashboardDC.getName() + " latest humididty");

            dashboardLatestHumidity = (TextView) dashboardview.findViewById(R.id.humidity);
            if(latestData != null)
            dashboardLatestHumidity.setText(new DecimalFormat("##.##").format(latestData.getHumidity()) + "%");

            DashboardLatestco2Template = (TextView) dashboardview.findViewById(R.id.title_carbon_dioxide_template);
            DashboardLatestco2Template.setText(dashboardDC.getName() + " latest carbon dioxide");


            DashboardLatestco2 = (TextView) dashboardview.findViewById(R.id.carbon_dioxide);
            if(latestData != null)
            DashboardLatestco2.setText(new DecimalFormat("##.##").format(latestData.getCarbonDioxide()) + "PPM");


            currentActiveDataCollecor = (TextView) dashboardview.findViewById(R.id.title_active_collectors_template);
            currentActiveDataCollecor.setText("You have " + dataloggers.size() + " data loggers");

            return dashboardview;
        }else{

            dashboardLatestTempereatureTemplate = (TextView) dashboardview.findViewById(R.id.title_temperature_template);
            dashboardLatestTempereatureTemplate.setText("No Data Collectors to show data");

            dashboardLatestTempereature = (TextView) dashboardview.findViewById(R.id.temperature);
            dashboardLatestTempereature.setText("0");

            dashboardLatestHumidityTemplate = (TextView) dashboardview.findViewById(R.id.title_humidity_template);
            dashboardLatestHumidityTemplate.setText("No Data Collectors to show data");

            dashboardLatestHumidity = (TextView) dashboardview.findViewById(R.id.humidity);
            dashboardLatestHumidity.setText("0");

            DashboardLatestco2Template = (TextView) dashboardview.findViewById(R.id.title_carbon_dioxide_template);
            DashboardLatestco2Template.setText("No Data Collectors to show data");


            DashboardLatestco2 = (TextView) dashboardview.findViewById(R.id.carbon_dioxide);
            DashboardLatestco2.setText("0");


            currentActiveDataCollecor = (TextView) dashboardview.findViewById(R.id.title_active_collectors_template);
            currentActiveDataCollecor.setText("No Data Collectors to show data");

            return dashboardview;
        }
    }
}
