package com.datalogging.user.components.grouppermission;

import com.datalogging.user.components.Component;
import com.datalogging.user.components.DialogMode;
import com.datalogging.user.javafx.DLBuilder;
import com.datalogging.user.javafx.DLButton;
import com.datalogging.user.javafx.DLText;
import com.datalogging.user.model.Group;
import com.datalogging.user.model.GroupPermission;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class GroupPermissionManagerComponent extends Component {
    public DLButton createGroupPermissionDLButton;
    public DLButton editGroupPermissionDLButton;
    public DLButton deleteGroupPermissionDLButton;
    public GroupPermissionListComponent groupPermissionListComponent;
    public GroupPermissionDialogComponent groupPermissionDialogComponent;
    private Group group;

    public GroupPermissionManagerComponent(Group group) {
        this.group = group;

        DLText groupPermissionHeading = new DLText(new DLBuilder().setText("Group Permission").setFontSize(32).setMargin(new Insets(0, 0, 16, 0)));
        getContainer().getChildren().add(groupPermissionHeading);

        FlowPane buttonListContainer = new FlowPane();
        buttonListContainer.setPadding(new Insets(0, 0, 0, 16));

        createGroupPermissionDLButton = new DLButton(new DLBuilder().setText("Create").setActionEventEventHandler(new CreateGroupPermissionHandler()));

        editGroupPermissionDLButton = new DLButton(new DLBuilder().setText("Edit").setActionEventEventHandler(new EditGroupPermissionHandler()));

        deleteGroupPermissionDLButton = new DLButton(new DLBuilder().setText("Delete").setActionEventEventHandler(new DeleteGroupPermissionHandler()));

        groupPermissionListComponent = new GroupPermissionListComponent();
        groupPermissionListComponent.getContainer().setPadding(new Insets(0, 0, 0, 16));

        groupPermissionDialogComponent = new GroupPermissionDialogComponent(groupPermissionListComponent, group);

        getContainer().getChildren().add(buttonListContainer);
        buttonListContainer.getChildren().add(createGroupPermissionDLButton);
        buttonListContainer.getChildren().add(editGroupPermissionDLButton);
        buttonListContainer.getChildren().add(deleteGroupPermissionDLButton);
        getContainer().getChildren().add(groupPermissionListComponent.getContainer());

        groupPermissionListComponent.update(group);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    private class CreateGroupPermissionHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            groupPermissionDialogComponent.createEdit(DialogMode.Create, new GroupPermission());
            groupPermissionListComponent.update(group);
        }
    }

    private class EditGroupPermissionHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            VBox selectedGroupPermission = (VBox) groupPermissionListComponent.listContainer.getSelectionModel().getSelectedItem();

            if (selectedGroupPermission != null) {
                groupPermissionDialogComponent.createEdit(DialogMode.Edit, (GroupPermission) selectedGroupPermission.getProperties().get("group-permission"));
            }
        }
    }

    private class DeleteGroupPermissionHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            VBox selectedGroupPermission = (VBox) groupPermissionListComponent.listContainer.getSelectionModel().getSelectedItem();

            if (selectedGroupPermission != null) {
                groupPermissionDialogComponent.delete((GroupPermission) selectedGroupPermission.getProperties().get("group-permission"));
            }
        }
    }
}
