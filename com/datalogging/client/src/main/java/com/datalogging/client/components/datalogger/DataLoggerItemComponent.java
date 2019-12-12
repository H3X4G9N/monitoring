package com.datalogging.client.components.datalogger;

import com.datalogging.client.Controller;
import com.datalogging.client.components.Component;
import com.datalogging.client.components.ManagementComponent;
import com.datalogging.client.components.group.GroupComponent;
import com.datalogging.client.model.DataLogger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class DataLoggerItemComponent extends Component {
    public TextField groupNameValue;
    public TextField groupDescriptionValue;
    public Button viewMoreButton;
    private DataLogger datalogger;

    public DataLoggerItemComponent(Controller controller, DataLogger datalogger) {
        super(controller);
        this.datalogger = datalogger;

        Label groupNameKey = new Label();
        groupNameKey.setText("Name");

        groupNameValue = new TextField();
        groupNameValue.setEditable(false);
        groupNameValue.setText(datalogger.getName());

        Label groupDescriptionKey = new Label();
        groupDescriptionKey.setText("Description");

        this.setContainer(new VBox());

        getContainer().setPadding(new Insets(16, 16, 16, 16));

        groupDescriptionValue = new TextField();
        groupDescriptionValue.setEditable(false);
        groupDescriptionValue.setText(datalogger.getDescription());

        viewMoreButton = new Button();
        viewMoreButton.setText("View More");
        viewMoreButton.setOnAction(new ViewMoreHandler());

        getContainer().getChildren().add(groupNameKey);
        getContainer().getChildren().add(groupNameValue);
        getContainer().getChildren().add(groupDescriptionKey);
        getContainer().getChildren().add(groupDescriptionValue);
        getContainer().getChildren().add(viewMoreButton);
    }

    public DataLogger getGroup() {
        return datalogger;
    }

    public void setGroup(DataLogger datalogger) {
        this.datalogger = datalogger;
    }

    void update() {
        groupNameValue.setText(datalogger.getName());
        groupDescriptionValue.setText(datalogger.getDescription());
    }

    private class ViewMoreHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            ManagementComponent managementComponent = (ManagementComponent)getController().getComponent("management");

            managementComponent.scrollContainer.setContent(new DataLoggerComponent(getController(), datalogger).getContainer());
        }
    }
}