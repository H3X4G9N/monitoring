package com.datalogging.user.components.datalogger;

import com.datalogging.user.Controller;
import com.datalogging.user.components.Component;
import com.datalogging.user.components.utility.ChartComponent;
import com.datalogging.user.components.utility.TopBarComponent;
import com.datalogging.user.javafx.*;
import com.datalogging.user.model.Data;
import com.datalogging.user.model.DataLogger;

import com.datalogging.user.model.GroupAuthentication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.text.DecimalFormat;

public class DataLoggerComponent extends Component {
    public TopBarComponent topBarComponent;
    public DataLoggerItemComponent dataLoggerItemComponent;
    public DLLabel currentCarbonDioxideValue;
    public DLLabel currentHumidityValue;
    public DLLabel currentTemperatureValue;
    public ChartComponent chartComponent;
    public ChartComponent chartComponent2;
    public ChartComponent chartComponent3;
    private DataLogger dataLogger;
    private Boolean isAuthorized;

    public void reload() {
        Data latest = null;

        if (isAuthorized) {
            latest = Data.getLatestFromDataLogger(Controller.user, dataLogger);
        } else {
            GroupAuthentication groupAuthentication = Controller.groupAuthenticationMap.get(dataLogger.getGroup());
            if (groupAuthentication != null) {
                latest = Data.getLatestFromDataLogger(dataLogger, groupAuthentication);
            }
        }
        if (latest != null) {

            currentCarbonDioxideValue.setText(new DecimalFormat("##.##").format(latest.getCarbonDioxide()) + " PPM");
            currentHumidityValue.setText(new DecimalFormat("##.##").format(latest.getHumidity()) + " %");
            currentTemperatureValue.setText(new DecimalFormat("##.##").format(latest.getTemperature()) + "  C");
        }
    }

    private class ReloadHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            reload();
        }
    }

    public DataLoggerComponent(DataLogger dataLogger, Boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        VBox.setVgrow(getContainer(), Priority.ALWAYS);

        getContainer().getChildren().add(scrollPane);
        VBox.setVgrow(getContainer(), Priority.ALWAYS);

        topBarComponent = new TopBarComponent(this, true, "Data Logger", TopBarComponent.GoBackType.Scene);
        VBox.setVgrow(topBarComponent.getContainer(), Priority.ALWAYS);
        topBarComponent.getContainer().setMinHeight(2880);
        scrollPane.setContent((topBarComponent.getContainer()));

        this.dataLogger = dataLogger;

        DLText dataLoggerHeading = new DLText(new DLBuilder().setText("Data Logger").setFontSize(32).setMargin(new Insets(0, 0, 16, 0)));

        dataLoggerItemComponent = new DataLoggerItemComponent(dataLogger, DataLoggerItemComponent.DataLoggerItemMode.DataLogger);
        dataLoggerItemComponent.getContainer().setPadding(new Insets(0, 0, 0, 16));

        DLText dataHeading = new DLText(new DLBuilder().setText("Data Charts").setFontSize(32).setMargin(new Insets(0, 0, 16, 0)));

        DLText currentDataHeading = new DLText(new DLBuilder().setText("Current Data").setFontSize(32).setMargin(new Insets(0, 0, 16, 0)));

        VBox container = new VBox();

        container.setPadding(new Insets(0, 0, 0, 16));

        DLLabel currentCarbonDioxideKey = new DLLabel(new DLBuilder().setText("Carbon Dioxide"));
        container.getChildren().add(currentCarbonDioxideKey);

        currentCarbonDioxideValue = new DLLabel(new DLBuilder());
        container.getChildren().add(currentCarbonDioxideValue);

        DLLabel currentHumidityKey = new DLLabel(new DLBuilder().setText("Humidity"));
        container.getChildren().add(currentHumidityKey);

        currentHumidityValue = new DLLabel(new DLBuilder());
        container.getChildren().add(currentHumidityValue);

        DLLabel currentTemperatureKey = new DLLabel(new DLBuilder().setText("Temperature"));
        container.getChildren().add(currentTemperatureKey);

        currentTemperatureValue = new DLLabel(new DLBuilder());
        container.getChildren().add(currentTemperatureValue);

        DLButton reloadButton = new DLButton(new DLBuilder().setText("Reload").setActionEventEventHandler(new ReloadHandler()));
        reload();

        chartComponent = new ChartComponent(dataLogger, ChartComponent.DataType.CarbonDioxide, isAuthorized);
        chartComponent.getContainer().setPadding(new Insets(0, 0, 0, 16));

        DLSeparator separator1 = new DLSeparator(new DLBuilder().setOrientation(Orientation.HORIZONTAL).setMargin(new Insets(16, 0, 16, 0)));


        chartComponent2 = new ChartComponent(dataLogger, ChartComponent.DataType.Humidity, isAuthorized);
        chartComponent2.getContainer().setPadding(new Insets(0, 0, 0, 16));

        DLSeparator separator2 = new DLSeparator(new DLBuilder().setOrientation(Orientation.HORIZONTAL).setMargin(new Insets(16, 0, 16, 0)));

        chartComponent3 = new ChartComponent(dataLogger, ChartComponent.DataType.Temperature, isAuthorized);
        chartComponent3.getContainer().setPadding(new Insets(0, 0, 0, 16));

        DLSeparator separator3 = new DLSeparator(new DLBuilder().setOrientation(Orientation.HORIZONTAL).setMargin(new Insets(16, 0, 16, 0)));

        topBarComponent.mainContainer.getChildren().add(dataLoggerHeading);
        topBarComponent.mainContainer.getChildren().add(dataLoggerItemComponent.getContainer());
        topBarComponent.mainContainer.getChildren().add(currentDataHeading);
        topBarComponent.mainContainer.getChildren().add(container);
        topBarComponent.mainContainer.getChildren().add(reloadButton);
        topBarComponent.mainContainer.getChildren().add(dataHeading);
        topBarComponent.mainContainer.getChildren().add(chartComponent.getContainer());
        topBarComponent.mainContainer.getChildren().add(separator1);
        topBarComponent.mainContainer.getChildren().add(chartComponent2.getContainer());
        topBarComponent.mainContainer.getChildren().add(separator2);
        topBarComponent.mainContainer.getChildren().add(chartComponent3.getContainer());
        topBarComponent.mainContainer.getChildren().add(separator3);
    }
}
