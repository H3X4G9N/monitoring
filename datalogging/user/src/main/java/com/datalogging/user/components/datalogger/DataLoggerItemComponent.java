package com.datalogging.user.components.datalogger;

import com.datalogging.user.Controller;
import com.datalogging.user.components.Component;
import com.datalogging.user.javafx.*;
import com.datalogging.user.model.DataLogger;
import com.datalogging.user.model.Group;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.List;

public class DataLoggerItemComponent extends Component {
    public DLTextField nameValue;
    public DLTextField descriptionValue;
    public DLComboBox groupValue;
    public DLButton actionButton;
    private DataLogger dataLogger;
    private DataLoggerItemMode dataLoggerItemMode;

    public DataLoggerItemComponent(DataLogger dataLogger, DataLoggerItemMode dataLoggerItemMode) {
        this.dataLogger = dataLogger;
        this.dataLoggerItemMode = dataLoggerItemMode;

        DLLabel nameKey = new DLLabel(new DLBuilder().setText("Name"));
        getContainer().getChildren().add(nameKey);

        nameValue = new DLTextField(new DLBuilder().setText(dataLogger.getName()).setEditable(false));
        getContainer().getChildren().add(this.nameValue);

        DLLabel descriptionKey = new DLLabel(new DLBuilder().setText("Description"));
        getContainer().getChildren().add(descriptionKey);

        descriptionValue = new DLTextField(new DLBuilder().setText(dataLogger.getDescription()).setEditable(false));
        getContainer().getChildren().add(this.descriptionValue);

        if (dataLoggerItemMode != DataLoggerItemMode.DataLogger) {
            actionButton = new DLButton(new DLBuilder().setText("View More").setActionEventEventHandler(new ViewMoreHandler()));
            getContainer().getChildren().add(this.actionButton);
        }


    }

    public DataLogger getDataLogger() {
        return dataLogger;
    }

    public void setDataLogger(DataLogger datalogger) {
        this.dataLogger = datalogger;
    }

    public DataLoggerItemMode getDataLoggerItemMode() {
        return dataLoggerItemMode;
    }

    public void setDataLoggerItemMode(DataLoggerItemMode dataLoggerItemMode) {
        this.dataLoggerItemMode = dataLoggerItemMode;
    }

    void update() {
        nameValue.setText(dataLogger.getName());
        descriptionValue.setText(dataLogger.getDescription());
    }

    public enum DataLoggerItemMode {
        AuthorizedDataLoggerList,
        UnauthorizedDataLoggerList,
        DataLogger
    }

    private class ViewMoreHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (dataLoggerItemMode == DataLoggerItemMode.AuthorizedDataLoggerList) {
                Controller.componentMap.managementComponent.scrollContainer.setContent(new DataLoggerComponent(dataLogger, true).getContainer());
            } else {
                Controller.componentMap.dataLoggerComponent = new DataLoggerComponent(dataLogger, false);
                Controller.componentMap.dataLoggerComponent.setPreviousContainer(Controller.componentMap.groupComponent.getContainer());
                Controller.scene.setRoot(Controller.componentMap.dataLoggerComponent.getContainer());
            }
        }
    }
}
