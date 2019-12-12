package com.datalogging.client.components.datalogger;

import com.datalogging.client.Controller;
import com.datalogging.client.components.Component;
import com.datalogging.client.components.utility.ChartComponent;
import com.datalogging.client.model.Data;
import com.datalogging.client.model.DataLogger;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DataLoggerComponent extends Component {
    public TextField nameValue;
    public TextArea descriptionValue;
    public ComboBox groupValue;
    public ChartComponent chartComponent;
    private DataLogger dataLogger;

    public DataLoggerComponent(Controller controller, DataLogger dataLogger) {
        super(controller);
        this.dataLogger = dataLogger;

        Label nameKey = new Label();
        nameKey.setText("Name");

        nameValue = new TextField();
        nameValue.setText(dataLogger.getName());
        nameValue.setEditable(false);

        Label descriptionKey = new Label();
        descriptionKey.setText("Description");

        descriptionValue = new TextArea();
        descriptionValue.setText(dataLogger.getDescription());
        descriptionValue.setEditable(false);

        Label groupKey = new Label();
        groupKey.setText("Group");

        groupValue = new ComboBox();
        groupValue.getItems().add(dataLogger.getGroup());
        groupValue.setValue(dataLogger.getGroup());
        groupValue.setEditable(false);

        chartComponent = new ChartComponent(getController(), dataLogger);

        getContainer().getChildren().add(nameKey);
        getContainer().getChildren().add(nameValue);
        getContainer().getChildren().add(descriptionKey);
        getContainer().getChildren().add(descriptionValue);
        getContainer().getChildren().add(groupKey);
        getContainer().getChildren().add(groupValue);
        getContainer().getChildren().add(chartComponent.getContainer());
    }


}
