package com.datalogging.client.components.grouppermission;

import com.datalogging.client.Controller;
import com.datalogging.client.components.Component;
import com.datalogging.client.components.DialogMode;
import com.datalogging.client.model.Group;
import com.datalogging.client.model.GroupPermission;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GroupPermissionDialog extends Component {
    public GroupPermissionDialog(Controller controller) {
        super(controller);
    }

    public void createEdit(DialogMode dialogMode, Group groupDetails, GroupPermission groupPermissionDetails) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner((Stage) getController().getScene().getWindow());

        VBox container = new VBox();

        Label permittedUserKey = new Label();
        permittedUserKey.setText("Permitted User");

        TextField permittedUserValue = new TextField();

        Button createEditButton = new Button();

        ComboBox groups = new ComboBox();

        if (dialogMode == DialogMode.Create) {
            createEditButton.setText("Create");
        } else {
            createEditButton.setText("Edit");

            permittedUserValue.setText(groupPermissionDetails.getPermittedUser());
        }

        createEditButton.setOnAction(event -> {
            String permittedUser = permittedUserValue.getText();

            if (dialogMode == DialogMode.Create) {
                GroupPermission newGroup = GroupPermission.create(getController().getUser(), new GroupPermission(permittedUser, groupDetails.getName()));
            } else {
                GroupPermission newGroup = GroupPermission.update(getController().getUser(), groupPermissionDetails.getPermittedUser(), groupPermissionDetails.getPermissibleGroup(), new GroupPermission(permittedUser, groupDetails.getName()));
            }

            dialog.close();
        });

        container.getChildren().add(permittedUserKey);
        container.getChildren().add(permittedUserValue);
        container.getChildren().add(createEditButton);

        Scene dialogScene = new Scene(container);

        dialog.setScene(dialogScene);
        dialog.show();
    }

    public void delete(GroupPermission groupPermission) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner((Stage) getController().getScene().getWindow());

        VBox container = new VBox();

        Button yesButton = new Button();
        yesButton.setText("Yes");

        Button noButton = new Button();
        noButton.setText("No");

        yesButton.setOnAction(event -> {
            GroupPermission.delete(getController().getUser(), groupPermission.getPermittedUser(), groupPermission.getPermissibleGroup());
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
