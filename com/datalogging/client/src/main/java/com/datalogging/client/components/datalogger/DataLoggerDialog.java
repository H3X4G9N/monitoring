package com.datalogging.client.components.datalogger;

import com.datalogging.client.Controller;
import com.datalogging.client.components.Component;
import com.datalogging.client.components.DialogMode;
import com.datalogging.client.model.DataLogger;
import com.datalogging.client.model.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class DataLoggerDialog extends Component {
    public DataLoggerDialog(Controller controller) {
        super(controller);
    }

    public void createEdit(DialogMode dialogMode, DataLogger dataLoggerDetails) {
        final Stage dialog = new Stage();
        dialog.initOwner((Stage) getController().getScene().getWindow());
        dialog.initModality(Modality.WINDOW_MODAL);

        VBox container = new VBox();

        Label nameKey = new Label();
        nameKey.setText("Name");

        TextField nameValue = new TextField();

        Label descriptionKey = new Label();
        descriptionKey.setText("Description");

        TextArea descriptionValue = new TextArea();

        Label activationKeyKey = new Label();
        activationKeyKey.setText("Activation Key");

        TextField activationKeyValue = new TextField();

        Label groupKey = new Label();
        groupKey.setText("Group");


        ComboBox  groupValue = new ComboBox();

        List<Group> groups = Group.getAllFromUser(getController().getUser());

        for (Group group : groups) {
            groupValue.getItems().add(group.getName());
        }

        Button createEditButton = new Button();

        if (dialogMode == DialogMode.Create) {
            createEditButton.setText("Create");
        } else {
            createEditButton.setText("Edit");
            nameValue.setText(dataLoggerDetails.getName());
            descriptionValue.setText(dataLoggerDetails.getDescription());
            groupValue.setValue(dataLoggerDetails.getGroup());
        }

        createEditButton.setOnAction(event -> {
            String name = nameValue.getText();
            String description = descriptionValue.getText();
            String activationKey = activationKeyValue.getText();
            String group = groupValue.getValue().toString();

            if (dialogMode == DialogMode.Create) {
                DataLogger.activate(getController().getUser(), group, new DataLogger(name, description, activationKey));
            } else {
                DataLogger.update(getController().getUser(), dataLoggerDetails.getID(), group, new DataLogger(name, description, ""));
            }

            dialog.close();
        });

        container.getChildren().add(nameKey);
        container.getChildren().add(nameValue);
        container.getChildren().add(descriptionKey);
        container.getChildren().add(descriptionValue);

        if (dialogMode == DialogMode.Create) {
            container.getChildren().add(activationKeyKey);
            container.getChildren().add(activationKeyValue);
        }

        container.getChildren().add(groupKey);
        container.getChildren().add(groupValue);
        container.getChildren().add(createEditButton);

        dialog.setScene(new Scene(container));
        dialog.show();
    }

    public void delete(DataLogger dataLogger) {
        final Stage dialog = new Stage();
        dialog.setTitle("Confirmation");
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner((Stage) getController().getScene().getWindow());

        VBox container = new VBox();

        Button yesButton = new Button();
        yesButton.setText("Yes");

        Button noButton = new Button();
        noButton.setText("No");

        yesButton.setOnAction(event -> {
            DataLogger.deactivate(getController().getUser(), dataLogger.getID());
            dialog.close();
        });

        noButton.setOnAction(event -> {
            dialog.close();
        });

        container.getChildren().add(yesButton);
        container.getChildren().add(noButton);
        Scene dialogScene = new Scene(container);

        dialog.setScene(dialogScene);
        dialog.show();
    }
}
