package com.datalogging.user.components;

import com.datalogging.user.Controller;
import com.datalogging.user.javafx.DLBuilder;
import com.datalogging.user.javafx.DLButton;
import com.datalogging.user.javafx.DLLabel;
import com.datalogging.user.model.Data;
import com.datalogging.user.model.DataLogger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardComponent extends Component {
    public TableView tableContainer;

    public DashboardComponent() {
        ColumnConstraints column1Left = new ColumnConstraints();
        column1Left.setPercentWidth(1.0 / 1.0 * 100.0);

        ColumnConstraints column2Left = new ColumnConstraints();
        column2Left.setPercentWidth(1.0 / 2.0 * 100.0);

        ColumnConstraints column2Right = new ColumnConstraints();
        column2Right.setPercentWidth(1.0 / 2.0 * 100.0);
        column2Right.setHalignment(HPos.RIGHT);

        ColumnConstraints column3Left = new ColumnConstraints();
        column3Left.setPercentWidth(1.0 / 3.0 * 100.0);

        ColumnConstraints column3Center = new ColumnConstraints();
        column3Center.setPercentWidth(1.0 / 3.0 * 100.0);
        column3Center.setHalignment(HPos.CENTER);

        DLLabel dashboardHeading = new DLLabel(new DLBuilder().setText("Dashboard").setFontSize(32).setMargin(new Insets(0, 0, 16, 0)));
        this.getContainer().getChildren().add(dashboardHeading);

        tableContainer = new TableView();
        getContainer().getChildren().add(tableContainer);

        TableColumn<DataWithDataLogger, String> column1 = new TableColumn<>("Data Logger");
        column1.setCellValueFactory(new PropertyValueFactory<>("dataLogger"));

        TableColumn<DataWithDataLogger, String> column2 = new TableColumn<>("Carbon Dioxide");
        column2.setCellValueFactory(new PropertyValueFactory<>("carbonDioxide"));

        TableColumn<DataWithDataLogger, String> column3 = new TableColumn<>("Humidity");
        column3.setCellValueFactory(new PropertyValueFactory<>("humidity"));

        TableColumn<DataWithDataLogger, String> column4 = new TableColumn<>("Temperature");
        column4.setCellValueFactory(new PropertyValueFactory<>("temperature"));


        tableContainer.getColumns().add(column1);
        tableContainer.getColumns().add(column2);
        tableContainer.getColumns().add(column3);
        tableContainer.getColumns().add(column4);
        tableContainer.setStyle("-fx-font-size:16");
        VBox.setMargin(tableContainer, new Insets(0, 0, 8, 0));

        DLButton reloadButton = new DLButton(new DLBuilder().setText("Reload").setActionEventEventHandler(new ReloadHandler()));
        getContainer().getChildren().add(reloadButton);

        reloadData();
    }

    private class ReloadHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            reloadData();
        }
    }

    public class DataWithDataLogger {
        private String dataLogger;
        private String carbonDioxide;
        private String humidity;
        private String temperature;

        public DataWithDataLogger() {}


        public DataWithDataLogger(DataLogger dataLogger, Data data) {
            this.dataLogger = dataLogger.getName();
            this.carbonDioxide = new DecimalFormat("##.##").format(data.getCarbonDioxide());
            this.humidity = new DecimalFormat("##.##").format(data.getHumidity());
            this.temperature = new DecimalFormat("##.##").format(data.getTemperature());
        }

        public String getDataLogger() {
            return dataLogger;
        }

        public void setDataLogger(String dataLogger) {
            this.dataLogger = dataLogger;
        }

        public String getCarbonDioxide() {
            return carbonDioxide;
        }

        public void setCarbonDioxide(String carbonDioxide) {
            this.carbonDioxide = carbonDioxide;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }
    }

    public void reloadData() {
        tableContainer.getItems().clear();
        Map<Long, Data> dataMap = new HashMap<>();
        List<Data> dataList = Data.getLatestFromUser(Controller.user);

        if (dataList != null) {
            for (Data data : dataList) {
                if (data != null) {
                    dataMap.put(data.getDataLogger(), data);
                }
            }

            List<DataLogger> dataLoggerList = DataLogger.getAllFromUser(Controller.user);

            for (DataLogger dataLogger : dataLoggerList) {
                if (dataMap.containsKey(dataLogger.getID())) {
                    Data data = dataMap.get(dataLogger.getID());
                    tableContainer.getItems().add(new DataWithDataLogger(dataLogger, data));
                }

            }
        }
    }
}