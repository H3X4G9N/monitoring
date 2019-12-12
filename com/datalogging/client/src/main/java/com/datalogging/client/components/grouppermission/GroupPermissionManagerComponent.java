package com.datalogging.client.components.grouppermission;

import com.datalogging.client.Controller;
import com.datalogging.client.components.Component;
import com.datalogging.client.components.DialogMode;
import com.datalogging.client.model.Group;
import com.datalogging.client.model.GroupPermission;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class GroupPermissionManagerComponent extends Component {
    public Button createGroupPermissionButton;
    public Button editGroupPermissionButton;
    public Button deleteGroupPermissionButton;
    public Button reloadGroupPermissionListButton;
    public GroupPermissionListComponent groupPermissionListComponent;
    private Group group;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public GroupPermissionManagerComponent(Controller controller, Group group) {
        super(controller);
        FlowPane buttonListContainer = new FlowPane();
        groupPermissionListComponent = new GroupPermissionListComponent(getController());

        this.group = group;

        createGroupPermissionButton = new Button();
        createGroupPermissionButton.setText("Create");
        createGroupPermissionButton.setOnAction(new CreateGroupPermissionHandler());
        createGroupPermissionButton.setStyle("-fx-font-size:16");

        editGroupPermissionButton = new Button();
        editGroupPermissionButton.setText("Edit");
        editGroupPermissionButton.setOnAction(new EditGroupPermissionGroupHandler());
        editGroupPermissionButton.setStyle("-fx-font-size:16");

        deleteGroupPermissionButton = new Button();
        deleteGroupPermissionButton.setText("Delete");
        deleteGroupPermissionButton.setOnAction(new DeleteGroupPermissionHandler());
        deleteGroupPermissionButton.setStyle("-fx-font-size:16");

        reloadGroupPermissionListButton = new Button();
        reloadGroupPermissionListButton.setText("Reload");
        reloadGroupPermissionListButton.setOnAction(new ReloadGroupPermissionListHandler());
        reloadGroupPermissionListButton.setStyle("-fx-font-size:16");

        getContainer().getChildren().add(buttonListContainer);
        buttonListContainer.getChildren().add(createGroupPermissionButton);
        buttonListContainer.getChildren().add(editGroupPermissionButton);
        buttonListContainer.getChildren().add(deleteGroupPermissionButton);
        buttonListContainer.getChildren().add(reloadGroupPermissionListButton);
        getContainer().getChildren().add(groupPermissionListComponent.getContainer());
    }

    private class ReloadGroupPermissionListHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            List<GroupPermission> groupPermissionList = GroupPermission.getAllFromGroup(getController().getUser(), group.getName());

            if (groupPermissionList != null) {
                groupPermissionListComponent.updateGroupItemList(groupPermissionList);
            }
        }
    }

    private class CreateGroupPermissionHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            GroupPermissionDialog groupDialog = new GroupPermissionDialog(getController());
            groupDialog.createEdit(DialogMode.Create, group, new GroupPermission());
        }
    }

    private class EditGroupPermissionGroupHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            GroupPermissionDialog groupDialog = new GroupPermissionDialog(getController());

            VBox selectedItem = (VBox) groupPermissionListComponent.listContainer.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                groupDialog.createEdit(DialogMode.Edit, group, (GroupPermission) selectedItem.getProperties().get("group-permission"));
            }
        }
    }

    private class DeleteGroupPermissionHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            GroupPermissionDialog groupPermissionDialog = new GroupPermissionDialog(getController());

            VBox selectedItem = (VBox) groupPermissionListComponent.listContainer.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                groupPermissionDialog.delete((GroupPermission) selectedItem.getProperties().get("group-permission"));
            }
        }
    }
}
