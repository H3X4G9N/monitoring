package com.datalogging.user.components.group;

import com.datalogging.user.Controller;
import com.datalogging.user.components.Component;
import com.datalogging.user.components.DialogMode;
import com.datalogging.user.javafx.*;
import com.datalogging.user.model.Group;
import com.datalogging.user.model.GroupAuthentication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GroupItemComponent extends Component {
    public DLTextField nameValue;
    public DLTextField descriptionValue;
    public DLCheckBox isVisibleValue;
    public DLCheckBox isPasswordRequiredValue;
    public DLCheckBox isPermissionRequiredValue;
    public DLButton actionButton;
    private Group group;
    private GroupItemMode groupItemMode;

    public GroupItemComponent(Group group, GroupItemMode groupItemMode) {
        this.group = group;
        this.groupItemMode = groupItemMode;

        DLLabel nameKey = new DLLabel(new DLBuilder().setText("Name"));
        getContainer().getChildren().add(nameKey);

        nameValue = new DLTextField(new DLBuilder().setText(group.getRoot() ? group.getRootName() : group.getName()).setEditable(false));
        getContainer().getChildren().add(nameValue);

        DLLabel descriptionKey = new DLLabel(new DLBuilder().setText("Description"));
        getContainer().getChildren().add(descriptionKey);

        descriptionValue = new DLTextField(new DLBuilder().setText(group.getDescription()).setEditable(false));
        getContainer().getChildren().add(descriptionValue);

        if (groupItemMode == GroupItemMode.AuthorizedRootGroup || groupItemMode == GroupItemMode.AuthorizedGroup) {
            isVisibleValue = new DLCheckBox(new DLBuilder().setText("Is Visible").setSelected(group.getVisible()));
            isVisibleValue.setDisable(true);
            getContainer().getChildren().add(isVisibleValue);

            isPasswordRequiredValue = new DLCheckBox(new DLBuilder().setText("Is Password Required").setSelected(group.getPasswordRequired()));
            isPasswordRequiredValue.setDisable(true);
            getContainer().getChildren().add(isPasswordRequiredValue);

            isPermissionRequiredValue = new DLCheckBox(new DLBuilder().setText("Is Permission Required"));
            isPermissionRequiredValue.setDisable(true);
            getContainer().getChildren().add(isPermissionRequiredValue);
        }

        actionButton = new DLButton(new DLBuilder());

        if (groupItemMode == GroupItemMode.AuthorizedRootGroup) {
            actionButton.setOnAction(new AuthorizedEditHandler());
            actionButton.setText("Edit");
        } else if (groupItemMode == GroupItemMode.AuthorizedGroupList) {
            actionButton.setOnAction(new AuthorizedViewMoreHandler());
            actionButton.setText("View More");
        } else if (groupItemMode == GroupItemMode.UnauthorizedGroupList) {
            actionButton.setOnAction(new UnauthorizedViewMoreHandler());
            actionButton.setText("View More");
        } else if (groupItemMode == GroupItemMode.UnauthorizedRootGroupList) {
            actionButton.setOnAction(new UnauthorizedViewMoreRootHandler());
            actionButton.setText("View More");
        }

        getContainer().getChildren().add(actionButton);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    void update() {
        if (group.getRoot()) {
            if (groupItemMode == GroupItemMode.AuthorizedRootGroup || groupItemMode == GroupItemMode.AuthorizedGroup) {
                nameValue.setText(group.getRootName());
                descriptionValue.setText(group.getDescription());
                isVisibleValue.setSelected(group.getVisible());
                isPasswordRequiredValue.setSelected(group.getPasswordRequired());
                isPermissionRequiredValue.setSelected(group.getPermissionRequired());
            }
        } else {
            nameValue.setText(group.getName());
            descriptionValue.setText(group.getDescription());
        }

    }

    public enum GroupItemMode {
        AuthorizedRootGroup,
        AuthorizedGroup,
        AuthorizedGroupList,
        UnauthorizedRootGroupList,
        UnauthorizedGroupList
    }

    private class AuthorizedEditHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            GroupDialogComponent groupDialogComponent = new GroupDialogComponent(null);
            groupDialogComponent.createOrEdit(group, DialogMode.Edit);
        }
    }

    private class AuthorizedViewMoreHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Controller.componentMap.managementComponent.scrollContainer.setContent(new GroupComponent(group, GroupComponent.GroupMode.Authorized).getContainer());
        }
    }

    private class UnauthorizedViewMoreRootHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            GroupAuthentication groupAuthentication = null;

            if (Controller.groupAuthenticationMap.containsKey(group.getID())) {
                groupAuthentication = Group.authenticate(Controller.groupAuthenticationMap.get(group.getID()));
            }

            if (groupAuthentication == null) {
                if (group.getPasswordRequired()) {
                    GroupDialogComponent groupDialogComponent = new GroupDialogComponent(null);
                    groupDialogComponent.authorize(group, false);
                } else {
                    GroupAuthentication groupAuthentication1;

                    if (Controller.user != null) {
                        groupAuthentication1 = Group.authorize(Controller.user, group, "");
                    } else {
                        groupAuthentication1 = Group.authorize(group, "");
                    }

                    if (groupAuthentication1 != null) {
                        Controller.groupAuthenticationMap.put(group.getID(), groupAuthentication1);
                        Controller.componentMap.rootGroupComponent = new GroupComponent(group, GroupComponent.GroupMode.UnauthorizedRoot);
                        Controller.componentMap.rootGroupComponent.setPreviousContainer(Controller.componentMap.browserComponent.getContainer());
                        Controller.scene.setRoot(Controller.componentMap.rootGroupComponent.getContainer());
                    }
                }
            } else {
                Controller.componentMap.rootGroupComponent = new GroupComponent(group, GroupComponent.GroupMode.UnauthorizedRoot);
                Controller.componentMap.rootGroupComponent.setPreviousContainer(Controller.componentMap.browserComponent.getContainer());
                Controller.scene.setRoot(Controller.componentMap.rootGroupComponent.getContainer());
            }
        }
    }

    private class UnauthorizedViewMoreHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            GroupAuthentication groupAuthentication = null;

            if (Controller.groupAuthenticationMap.containsKey(group.getID())) {
                groupAuthentication = Group.authenticate(Controller.groupAuthenticationMap.get(group.getID()));
            }

            if (groupAuthentication == null) {
                if (group.getPasswordRequired()) {
                    GroupDialogComponent groupDialogComponent = new GroupDialogComponent(null);
                    groupDialogComponent.authorize(group, false);
                } else {
                    GroupAuthentication groupAuthentication1 = Group.authorize(group, "");

                    if (groupAuthentication1 != null) {
                        Controller.groupAuthenticationMap.put(group.getID(), groupAuthentication1);

                        Controller.componentMap.groupComponent = new GroupComponent(group, GroupComponent.GroupMode.Unauthorized);
                        Controller.componentMap.groupComponent.setPreviousContainer(Controller.componentMap.rootGroupComponent.getContainer());
                        Controller.scene.setRoot(Controller.componentMap.groupComponent.getContainer());
                    }
                }
            } else {
                Controller.componentMap.groupComponent = new GroupComponent(group, GroupComponent.GroupMode.Unauthorized);
                Controller.componentMap.groupComponent.setPreviousContainer(Controller.componentMap.rootGroupComponent.getContainer());
                Controller.scene.setRoot(Controller.componentMap.groupComponent.getContainer());
            }
        }
    }
}
