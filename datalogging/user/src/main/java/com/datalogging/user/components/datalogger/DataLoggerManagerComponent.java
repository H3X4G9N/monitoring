package com.datalogging.user.components.datalogger;

import com.datalogging.user.components.Component;
import com.datalogging.user.components.DialogMode;
import com.datalogging.user.javafx.DLBuilder;
import com.datalogging.user.javafx.DLButton;
import com.datalogging.user.javafx.DLText;
import com.datalogging.user.model.DataLogger;
import com.datalogging.user.model.Group;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class DataLoggerManagerComponent extends Component {
    public DLButton createGroupDLButton;
    public DLButton editGroupDLButton;
    public DLButton deleteGroupDLButton;
    public DataLoggerListComponent dataLoggerListComponent;
    public DataLoggerDialogComponent dataLoggerDialogComponent;

    public DataLoggerManagerComponent(Group group) {
        DLText dataLoggerListHeading = new DLText(new DLBuilder().setText("Data Logger List").setFontSize(32).setMargin(new Insets(0, 0, 16, 0)));

        FlowPane buttonListContainer = new FlowPane();
        buttonListContainer.setPadding(new Insets(0, 0, 0, 16));

        createGroupDLButton = new DLButton(new DLBuilder().setText("Activate").setActionEventEventHandler(new ActivateDataLoggerHandler()));

        editGroupDLButton = new DLButton(new DLBuilder().setText("Edit").setActionEventEventHandler(new EditDataLoggerHandler()));

        deleteGroupDLButton = new DLButton(new DLBuilder().setText("Deactivate").setActionEventEventHandler(new DeactivateDataLoggerHandler()));

        dataLoggerListComponent = new DataLoggerListComponent();
        dataLoggerListComponent.getContainer().setPadding(new Insets(0, 0, 0, 16));

        dataLoggerDialogComponent = new DataLoggerDialogComponent(dataLoggerListComponent);

        getContainer().getChildren().add(dataLoggerListHeading);
        getContainer().getChildren().add(buttonListContainer);
        buttonListContainer.getChildren().add(createGroupDLButton);
        buttonListContainer.getChildren().add(editGroupDLButton);
        buttonListContainer.getChildren().add(deleteGroupDLButton);
        getContainer().getChildren().add(dataLoggerListComponent.getContainer());

        if (group != null) {
            dataLoggerListComponent.update3(group);
        } else {
            dataLoggerListComponent.update();
        }
    }

    private class ActivateDataLoggerHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            dataLoggerDialogComponent.createEdit(DialogMode.Create, new DataLogger());
        }
    }

    private class EditDataLoggerHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            VBox selectedDataLogger = (VBox) dataLoggerListComponent.listContainer.getSelectionModel().getSelectedItem();

            if (selectedDataLogger != null) {
                dataLoggerDialogComponent.createEdit(DialogMode.Edit, (DataLogger) selectedDataLogger.getProperties().get("data-logger"));
            }
        }
    }

    private class DeactivateDataLoggerHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            VBox selectedDataLogger = (VBox) dataLoggerListComponent.listContainer.getSelectionModel().getSelectedItem();

            if (selectedDataLogger != null) {
                dataLoggerDialogComponent.delete((DataLogger) selectedDataLogger.getProperties().get("data-logger"));
            }
        }
    }
}
