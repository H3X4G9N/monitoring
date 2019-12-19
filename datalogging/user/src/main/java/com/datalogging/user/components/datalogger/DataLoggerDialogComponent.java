package com.datalogging.user.components.datalogger;

import com.datalogging.user.Controller;
import com.datalogging.user.components.Component;
import com.datalogging.user.components.DialogMode;
import com.datalogging.user.javafx.*;
import com.datalogging.user.model.DataLogger;
import com.datalogging.user.model.Group;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class DataLoggerDialogComponent extends Component {
    public DataLoggerListComponent dataLoggerListComponent;

    public DataLoggerDialogComponent(DataLoggerListComponent dataLoggerListComponent) {
        this.dataLoggerListComponent = dataLoggerListComponent;
    }

    public void createEdit(DialogMode dialogMode, DataLogger dataLoggerDetails) {
        final Stage dialog = new Stage();
        dialog.initOwner((Stage) Controller.scene.getWindow());
        dialog.initModality(Modality.WINDOW_MODAL);

        VBox container = new VBox();
        container.setPadding(new Insets(16, 16, 16, 16));


        DLLabel nameKey = new DLLabel(new DLBuilder().setText("Name"));

        DLTextField nameValue = new DLTextField(new DLBuilder());

        DLLabel descriptionKey = new DLLabel(new DLBuilder().setText("Description"));

        DLTextField descriptionValue = new DLTextField(new DLBuilder());

        DLLabel activationKeyKey = new DLLabel(new DLBuilder().setText("Activation Key"));

        DLTextField activationKeyValue = new DLTextField(new DLBuilder());

        DLLabel groupKey = new DLLabel(new DLBuilder().setText("Group"));

        DLComboBox groupValue = new DLComboBox(new DLBuilder());

        List<Group> groups = Group.getAllFromUser(Controller.user);

        for (Group group : groups) {
            groupValue.getItems().add(group.getName());
        }

        DLButton createEditDLButton = new DLButton(new DLBuilder());

        if (dialogMode == DialogMode.Create) {
            createEditDLButton.setText("Create");
        } else {
            createEditDLButton.setText("Edit");
            nameValue.setText(dataLoggerDetails.getName());
            descriptionValue.setText(dataLoggerDetails.getDescription());

            Group group3 = groups.get(0);

            for (Group group2 : groups) {
                if (dataLoggerDetails.getGroup() == group2.getID()) {
                    group3 = group2;
                }
            }

            groupValue.setValue(group3.getName());
        }

        createEditDLButton.setOnAction(event -> {
            String name = nameValue.getText();
            String description = descriptionValue.getText();
            String activationKey = activationKeyValue.getText();
            String group = groupValue.getValue().toString();

            Group group3 = groups.get(0);

            for (Group group2 : groups) {
                if (group2.getName().equals(group)) {
                    group3 = group2;
                }
            }

            if (dialogMode == DialogMode.Create) {
                DataLogger.activate(Controller.user, group3, new DataLogger(name, description, activationKey));
            } else {
                DataLogger.update(Controller.user, dataLoggerDetails.getID(), group3, new DataLogger(name, description, ""));
            }

            dialog.close();
            dataLoggerListComponent.update();
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
        container.getChildren().add(createEditDLButton);
        dialog.setScene(new Scene(container));
        dialog.show();
    }

    public void delete(DataLogger dataLogger) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner((Stage) Controller.scene.getWindow());

        FlowPane container = new FlowPane();
        container.setPadding(new Insets(16, 16, 16, 16));

        DLButton yesDLButton = new DLButton(new DLBuilder().setText("Yes"));

        DLButton noDLButton = new DLButton(new DLBuilder().setText("No"));

        yesDLButton.setOnAction(event -> {
            DataLogger.deactivate(Controller.user, dataLogger.getID());
            dialog.close();
            dataLoggerListComponent.update();
        });

        noDLButton.setOnAction(event -> {
            dialog.close();
        });

        container.getChildren().add(yesDLButton);
        container.getChildren().add(noDLButton);

        dialog.setScene(new Scene(container));
        dialog.show();
    }
}
