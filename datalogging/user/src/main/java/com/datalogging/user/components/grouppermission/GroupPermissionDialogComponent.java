package com.datalogging.user.components.grouppermission;

import com.datalogging.user.Controller;
import com.datalogging.user.components.Component;
import com.datalogging.user.components.DialogMode;
import com.datalogging.user.javafx.*;
import com.datalogging.user.model.Group;
import com.datalogging.user.model.GroupPermission;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GroupPermissionDialogComponent extends Component {
    GroupPermissionListComponent groupPermissionListComponent;
    Group groupDetails;

    public GroupPermissionDialogComponent(GroupPermissionListComponent groupPermissionListComponent, Group groupDetails) {
        this.groupPermissionListComponent = groupPermissionListComponent;
        this.groupDetails = groupDetails;
    }

    public void createEdit(DialogMode dialogMode, GroupPermission groupPermissionDetails) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner((Stage) Controller.scene.getWindow());

        VBox container = new VBox();
        container.setPadding(new Insets(16, 16, 16, 16));

        DLLabel permittedUserKey = new DLLabel(new DLBuilder().setText("Permitted User"));

        DLTextField permittedUserValue = new DLTextField(new DLBuilder());

        DLButton createEditDLButton = new DLButton(new DLBuilder());

        DLComboBox groups = new DLComboBox(new DLBuilder());

        if (dialogMode == DialogMode.Create) {
            createEditDLButton.setText("Create");
        } else {
            createEditDLButton.setText("Edit");

            permittedUserValue.setText(groupPermissionDetails.getPermittedUser());
        }

        createEditDLButton.setOnAction(event -> {
            String permittedUser = permittedUserValue.getText();

            if (dialogMode == DialogMode.Create) {
                GroupPermission.create(Controller.user, new GroupPermission(permittedUser, groupDetails.getID()));
            } else {
                GroupPermission.update(Controller.user, groupPermissionDetails.getPermittedUser(), groupPermissionDetails.getPermissibleGroup(), new GroupPermission(permittedUser, groupDetails.getID()));
            }

            dialog.close();
            groupPermissionListComponent.update(groupDetails);
        });

        container.getChildren().add(permittedUserKey);
        container.getChildren().add(permittedUserValue);
        container.getChildren().add(createEditDLButton);

        dialog.setScene(new Scene(container));
        dialog.show();
    }

    public void delete(GroupPermission groupPermission) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner((Stage) Controller.scene.getWindow());

        FlowPane container = new FlowPane();
        container.setPadding(new Insets(16, 16, 16, 16));

        DLButton yesDLButton = new DLButton(new DLBuilder().setText("Yes"));

        DLButton noDLButton = new DLButton(new DLBuilder().setText("No"));

        yesDLButton.setOnAction(event -> {
            GroupPermission.delete(Controller.user, groupPermission.getPermittedUser(), groupPermission.getPermissibleGroup());
            dialog.close();
            groupPermissionListComponent.update(groupDetails);
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
