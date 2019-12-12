package com.datalogging.client.components.datalogger;

import com.datalogging.client.Controller;
import com.datalogging.client.components.Component;
import com.datalogging.client.components.DialogMode;
import com.datalogging.client.model.DataLogger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class DataLoggerManagerComponent extends Component {
    public Button createGroupButton;
    public Button editGroupButton;
    public Button deleteGroupButton;
    public Button reloadGroupListButton;
    public DataLoggerListComponent dataLoggerListComponent;

    public DataLoggerManagerComponent(Controller controller) {
        super(controller);
        FlowPane buttonListContainer = new FlowPane();
        dataLoggerListComponent = new DataLoggerListComponent(getController());

        createGroupButton = new Button();
        createGroupButton.setText("Activate");
        createGroupButton.setOnAction(new ActivateDataLogger());
        createGroupButton.setStyle("-fx-font-size:16");

        editGroupButton = new Button();
        editGroupButton.setText("Edit");
        editGroupButton.setOnAction(new EditGroupHandler());
        editGroupButton.setStyle("-fx-font-size:16");

        deleteGroupButton = new Button();
        deleteGroupButton.setText("Deactivate");
        deleteGroupButton.setOnAction(new DeleteGroupHandler());
        deleteGroupButton.setStyle("-fx-font-size:16");

        reloadGroupListButton = new Button();
        reloadGroupListButton.setText("Reload");
        reloadGroupListButton.setOnAction(new ReloadGroupListHandler());
        reloadGroupListButton.setStyle("-fx-font-size:16");

        getContainer().getChildren().add(buttonListContainer);
        buttonListContainer.getChildren().add(createGroupButton);
        buttonListContainer.getChildren().add(editGroupButton);
        buttonListContainer.getChildren().add(deleteGroupButton);
        buttonListContainer.getChildren().add(reloadGroupListButton);
        getContainer().getChildren().add(dataLoggerListComponent.getContainer());
    }

    private class ReloadGroupListHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            List<DataLogger> groupList = DataLogger.getAllFromUser(getController().getUser());

            if (groupList != null) {
                dataLoggerListComponent.updateGroupItemList(groupList);
            }
        }
    }

    private class ActivateDataLogger implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            DataLoggerDialog dataloggerDialog = new DataLoggerDialog(getController());
            dataloggerDialog.createEdit(DialogMode.Create, new DataLogger());
        }
    }

    private class EditGroupHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            DataLoggerDialog dataLoggerDialog = new DataLoggerDialog(getController());

            VBox selectedItem = (VBox) dataLoggerListComponent.listContainer.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                dataLoggerDialog.createEdit(DialogMode.Edit, (DataLogger) selectedItem.getProperties().get("group"));
            }
        }
    }

    private class DeleteGroupHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            DataLoggerDialog dataLoggerDialog = new DataLoggerDialog(getController());

            VBox selectedItem = (VBox) dataLoggerListComponent.listContainer.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                dataLoggerDialog.delete((DataLogger) selectedItem.getProperties().get("group"));
            }
        }
    }
}
