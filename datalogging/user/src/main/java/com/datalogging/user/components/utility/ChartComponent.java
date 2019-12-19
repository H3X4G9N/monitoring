package com.datalogging.user.components.utility;

import com.datalogging.user.Controller;
import com.datalogging.user.components.Component;
import com.datalogging.user.javafx.DLBuilder;
import com.datalogging.user.javafx.DLButton;
import com.datalogging.user.model.Data;
import com.datalogging.user.model.DataLogger;
import com.datalogging.user.model.GroupAuthentication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.TimeZone;

public class ChartComponent extends Component {
    public LineChart<String, Number> lineChart;
    public XYChart.Series<String, Number> series;
    public DateTimeComponent fromDateTimeComponent;
    public DateTimeComponent toDateTimeComponent;
    public DLButton loadButton;
    private DataLogger dataLogger;
    private DataType dataType;

    public ChartComponent(DataLogger dataLogger, DataType dataType, Boolean isAuthorized) {
        this.dataLogger = dataLogger;
        this.dataType = dataType;

        CategoryAxis axisX = new CategoryAxis();
        axisX.setLabel("Date / Time");
        axisX.setAnimated(false);

        NumberAxis axisY = new NumberAxis();
        axisY.setAnimated(false);

        lineChart = new LineChart<>(axisX, axisY);
        lineChart.setAnimated(false);
        lineChart.setStyle("-fx-font-size:16");
        VBox.setMargin(lineChart, new Insets(0, 0, 8, 0));

        series = new XYChart.Series<>();


        switch (dataType) {
            case CarbonDioxide:
                axisY.setLabel("Carbon Dioxide (PPM)");
                lineChart.setTitle("Carbon Dioxide");
                series.setName("Carbon Dioxide");
                break;
            case Humidity:
                axisY.setLabel("Humidity (%)");
                lineChart.setTitle("Humidity");
                series.setName("Humidity");
                break;
            case Temperature:
                axisY.setLabel("Temperaature (C)");
                lineChart.setTitle("Temperature");
                series.setName("Temperature");
                break;
        }

        fromDateTimeComponent = new DateTimeComponent();
        toDateTimeComponent = new DateTimeComponent();

        loadButton = new DLButton(new DLBuilder().setText("Load"));


        if (isAuthorized) {
            loadButton.setOnAction(new AuthorizedLoadHandler());
        } else {
            loadButton.setOnAction(new UnauthorizedLoadHandler());
        }

        getContainer().getChildren().add(lineChart);
        lineChart.getData().add(series);
        getContainer().getChildren().add(fromDateTimeComponent.getContainer());
        getContainer().getChildren().add(toDateTimeComponent.getContainer());
        getContainer().getChildren().add(loadButton);
    }

    public void authorizedLoadData() {
        series.getData().clear();

        LocalDateTime fromDateTime = fromDateTimeComponent.getValue();
        LocalDateTime toDateTime = toDateTimeComponent.getValue();

        long difference = toDateTime.toEpochSecond(ZoneOffset.UTC) - fromDateTime.toEpochSecond(ZoneOffset.UTC);


        if (difference > 0) {
            List<Data> dataList = Data.getAllFromDataLogger(Controller.user, dataLogger.getID(), fromDateTime, toDateTime);

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

            if (difference > 1440 * 60) {
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            } else if (difference < 60 * 60) {
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }

            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

            switch (dataType) {
                case CarbonDioxide:
                    for (Data data : dataList) {
                        series.getData().add(new XYChart.Data<>(sdf.format(data.getTimestamp()), data.getCarbonDioxide()));
                    }
                    break;
                case Humidity:
                    for (Data data : dataList) {
                        series.getData().add(new XYChart.Data<>(sdf.format(data.getTimestamp()), data.getHumidity()));
                    }
                    break;
                case Temperature:
                    for (Data data : dataList) {
                        series.getData().add(new XYChart.Data<>(sdf.format(data.getTimestamp()), data.getTemperature()));
                    }
                    break;
            }
        }
    }

    public void unauthorizedLoadData() {
        series.getData().clear();

        LocalDateTime fromDateTime = fromDateTimeComponent.getValue();
        LocalDateTime toDateTime = toDateTimeComponent.getValue();

        if (Controller.groupAuthenticationMap.containsKey(dataLogger.getGroup())) {
            GroupAuthentication groupAuthentication = Controller.groupAuthenticationMap.get(dataLogger.getGroup());

            long difference = toDateTime.toEpochSecond(ZoneOffset.UTC) - fromDateTime.toEpochSecond(ZoneOffset.UTC);

            if (difference > 0) {
                List<Data> dataList = Data.getAllFromDataLogger(dataLogger.getID(), groupAuthentication, fromDateTime, toDateTime);
                System.out.println(dataList.size());

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

                if (difference > 1440 * 60) {
                    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                } else if (difference < 60 * 60) {
                    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                }

                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

                switch (dataType) {
                    case CarbonDioxide:
                        for (Data data : dataList) {
                            series.getData().add(new XYChart.Data<>(sdf.format(data.getTimestamp()), data.getCarbonDioxide()));
                        }
                        break;
                    case Humidity:
                        for (Data data : dataList) {
                            series.getData().add(new XYChart.Data<>(sdf.format(data.getTimestamp()), data.getHumidity()));
                        }
                        break;
                    case Temperature:
                        for (Data data : dataList) {
                            series.getData().add(new XYChart.Data<>(sdf.format(data.getTimestamp()), data.getTemperature()));
                        }
                        break;
                }
            }
        }
    }

    public enum DataType {
        CarbonDioxide,
        Temperature,
        Humidity
    }

    private class AuthorizedLoadHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {


            authorizedLoadData();
        }
    }

    private class UnauthorizedLoadHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            unauthorizedLoadData();
        }
    }
}
