package com.datalogging.user.components.group;

import com.datalogging.user.Controller;
import com.datalogging.user.components.Component;
import com.datalogging.user.components.DialogMode;
import com.datalogging.user.components.grouppermission.GroupPermissionManagerComponent;
import com.datalogging.user.javafx.*;
import com.datalogging.user.model.Group;
import com.datalogging.user.model.GroupAuthentication;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GroupDialogComponent extends Component {
    GroupListComponent groupListComponent;

    public GroupDialogComponent(GroupListComponent groupListComponent) {
        this.groupListComponent = groupListComponent;
    }

    public void createOrEdit(Group group, DialogMode dialogMode) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(Controller.scene.getWindow());

        VBox container = new VBox();
        container.setPadding(new Insets(8));

        DLLabel groupNameKey = new DLLabel(new DLBuilder().setText("Name"));
        container.getChildren().add(groupNameKey);

        DLTextField groupNameValue = new DLTextField(new DLBuilder());
        container.getChildren().add(groupNameValue);

        DLLabel groupDescriptionKey = new DLLabel(new DLBuilder().setText("Description"));
        container.getChildren().add(groupDescriptionKey);

        DLTextField groupDescriptionValue = new DLTextField(new DLBuilder());
        container.getChildren().add(groupDescriptionValue);

        DLCheckBox visibleValue = new DLCheckBox(new DLBuilder().setText("Is Visible"));
        container.getChildren().add(visibleValue);

        DLCheckBox passwordRequiredValue = new DLCheckBox(new DLBuilder().setText("Is Password Required"));
        container.getChildren().add(passwordRequiredValue);

        DLLabel passwordKey = new DLLabel(new DLBuilder().setText("Password"));
        container.getChildren().add(passwordKey);

        DLPasswordField passwordValue = new DLPasswordField(new DLBuilder());
        container.getChildren().add(passwordValue);

        DLCheckBox permissionRequiredValue = new DLCheckBox(new DLBuilder().setText("Is Permission Required"));
        container.getChildren().add(permissionRequiredValue);

        DLButton createOrEditButton = new DLButton(new DLBuilder());

        if (dialogMode == DialogMode.Create) {
            createOrEditButton.setText("Create");
        } else {
            groupNameValue.setText(group.getName());

            if (group.getRoot()) {
                GroupPermissionManagerComponent groupPermissionManagerComponent = new GroupPermissionManagerComponent(group);
                container.getChildren().add(groupPermissionManagerComponent.getContainer());
                groupNameValue.setText(group.getRootName());
            }

            groupDescriptionValue.setText(group.getDescription());
            visibleValue.setSelected(group.getVisible());
            passwordRequiredValue.setSelected(group.getPasswordRequired());
            passwordValue.setText(group.getPassword());
            permissionRequiredValue.setSelected(group.getPermissionRequired());
            createOrEditButton.setText("Edit");
        }
        container.getChildren().add(createOrEditButton);

        createOrEditButton.setOnAction(event -> {
            String name = groupNameValue.getText();
            String description = groupDescriptionValue.getText();
            Boolean visible = visibleValue.isSelected();
            Boolean passwordRequired = passwordRequiredValue.isSelected();
            String password = passwordValue.getText();
            Boolean permissionRequired = permissionRequiredValue.isSelected();

            Group group2;

            if (dialogMode == DialogMode.Create) {
                group2 = Group.create(Controller.user, new Group(name, description, visible, passwordRequired, password, permissionRequired));
            } else {
                group2 = Group.update(Controller.user, group, new Group(name, description, visible, passwordRequired, password, permissionRequired));

                if (group.getRoot()) {
                    Controller.componentMap.rootGroupItemComponent.setGroup(group2);
                }
            }


            dialog.close();


            if (!group2.getRoot()) {
                groupListComponent.updateAuthorizedGroupList();
            } else {
                Controller.componentMap.rootGroupItemComponent.update();
            }
        });

        dialog.setScene(new Scene(container));
        dialog.show();
    }

    public void delete(Group group) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(Controller.scene.getWindow());

        FlowPane container = new FlowPane();
        container.setPadding(new Insets(8));

        DLButton yesButton = new DLButton(new DLBuilder().setText("Yes"));
        container.getChildren().add(yesButton);

        DLButton noButton = new DLButton(new DLBuilder().setText("No"));
        container.getChildren().add(noButton);

        yesButton.setOnAction(event -> {
            Group.delete(Controller.user, group);
            dialog.close();
            groupListComponent.updateAuthorizedGroupList();
        });

        noButton.setOnAction(event -> {
            dialog.close();
        });


        dialog.setScene(new Scene(container));
        dialog.show();
    }

    public void authorize(Group group, Boolean isAuthorized) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(Controller.scene.getWindow());

        VBox container = new VBox();
        container.setPadding(new Insets(8));

        DLLabel passwordKey = new DLLabel(new DLBuilder().setText("Password"));
        container.getChildren().add(passwordKey);

        DLPasswordField passwordValue = new DLPasswordField(new DLBuilder());
        container.getChildren().add(passwordValue);

        DLButton authorizeButton = new DLButton(new DLBuilder().setText("Authorize"));
        container.getChildren().add(authorizeButton);

        authorizeButton.setOnAction(event -> {
            String password = passwordValue.getText();

            GroupAuthentication groupAuthentication;

            if (Controller.groupAuthenticationMap.containsKey(group.getID())) {
                groupAuthentication = Controller.groupAuthenticationMap.get(group.getID());
                groupAuthentication = Group.authenticate(groupAuthentication);
            } else {
                if (Controller.user != null) {
                    groupAuthentication = Group.authorize(Controller.user, group, password);
                } else {
                    groupAuthentication = Group.authorize(group, password);
                }

                if (groupAuthentication != null) {
                    Controller.groupAuthenticationMap.put(group.getID(), groupAuthentication);
                }
            }

            if (groupAuthentication != null) {
                Controller.groupAuthenticationMap.put(groupAuthentication.getGroup(), groupAuthentication);

                if (group.getRoot()) {
                    Controller.componentMap.rootGroupComponent = new GroupComponent(group, GroupComponent.GroupMode.UnauthorizedRoot);
                    Controller.componentMap.rootGroupComponent.setPreviousContainer(Controller.componentMap.browserComponent.getContainer());
                    Controller.scene.setRoot(Controller.componentMap.rootGroupComponent.getContainer());
                } else {
                    Controller.componentMap.groupComponent = new GroupComponent(group, GroupComponent.GroupMode.Unauthorized);
                    Controller.componentMap.groupComponent.setPreviousContainer(Controller.componentMap.rootGroupComponent.getContainer());
                    Controller.scene.setRoot(Controller.componentMap.groupComponent.getContainer());
                }
            }

            dialog.close();
        });

        dialog.setScene(new Scene(container));
        dialog.show();
    }
}
