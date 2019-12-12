package com.datalogging.client.components.group;

import com.datalogging.client.Controller;
import com.datalogging.client.components.Component;
import com.datalogging.client.components.DialogMode;
import com.datalogging.client.model.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GroupDialog extends Component {
    public GroupDialog(Controller controller) {
        super(controller);
    }

    public void createEdit(DialogMode dialogMode, Group group) {
        final Stage dialog = new Stage();
        dialog.setTitle("Confirmation");
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner((Stage) getController().getScene().getWindow());

        VBox container = new VBox();

        Label groupNameKey = new Label();
        groupNameKey.setText("Name");

        TextField groupNameValue = new TextField();

        Label groupDescriptionKey = new Label();
        groupDescriptionKey.setText("Description");

        TextArea groupDescriptionValue = new TextArea();

        CheckBox visibleValue = new CheckBox();
        visibleValue.setText("Is Visible");

        Label passwordKey = new Label();
        passwordKey.setText("Password");
        PasswordField passwordValue = new PasswordField();

        CheckBox permissionRequiredValue = new CheckBox();
        permissionRequiredValue.setText("Is Permission Required");

        CheckBox setAsRootValue = new CheckBox();
        setAsRootValue.setText("Set As Root");

        Button createEditButton = new Button();

        if (dialogMode == DialogMode.Create) {
            createEditButton.setText("Create");
        } else {
            createEditButton.setText("Edit");

            groupNameValue.setText(group.getName());
            groupDescriptionValue.setText(group.getDescription());
            visibleValue.setSelected(group.getVisible());
            passwordValue.setText(group.getPassword());
            permissionRequiredValue.setSelected(group.getPermissionRequired());
            setAsRootValue.setSelected(group.getRoot());
        }

        createEditButton.setOnAction(event -> {
            String name = groupNameValue.getText();
            String description = groupDescriptionValue.getText();
            Boolean visible = visibleValue.isSelected();
            String password = passwordValue.getText();
            Boolean permissionRequired = permissionRequiredValue.isSelected();

            if (dialogMode == DialogMode.Create) {
                Group newGroup = Group.create(getController().getUser(), new Group(name, description, visible, password, permissionRequired));

                if (setAsRootValue.isSelected()) {
                    Group.setAsRoot(getController().getUser(), newGroup);
                }
            } else {
                Group newGroup = Group.update(getController().getUser(), group.getName(), new Group(name, description, visible, password, permissionRequired));
                if (setAsRootValue.isSelected()) {
                    Group.setAsRoot(getController().getUser(), newGroup);
                }
            }
            dialog.close();
        });

        container.getChildren().add(groupNameKey);
        container.getChildren().add(groupNameValue);
        container.getChildren().add(groupDescriptionKey);
        container.getChildren().add(groupDescriptionValue);
        container.getChildren().add(visibleValue);
        container.getChildren().add(passwordKey);
        container.getChildren().add(passwordValue);
        container.getChildren().add(permissionRequiredValue);
        container.getChildren().add(setAsRootValue);
        container.getChildren().add(createEditButton);

        Scene dialogScene = new Scene(container);

        dialog.setScene(dialogScene);
        dialog.show();
    }

    public void delete(Group group) {
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
            Group.delete(getController().getUser(), group.getName());
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
