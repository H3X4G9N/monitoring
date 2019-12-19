package com.datalogging.user.components.group;

import com.datalogging.user.Controller;
import com.datalogging.user.components.Component;
import com.datalogging.user.components.DialogMode;
import com.datalogging.user.javafx.DLBuilder;
import com.datalogging.user.javafx.DLButton;
import com.datalogging.user.javafx.DLText;
import com.datalogging.user.model.Group;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class GroupManagerComponent extends Component {
    public DLButton createGroupButton;
    public DLButton editGroupButton;
    public DLButton deleteGroupButton;
    public GroupListComponent groupListComponent;
    public GroupDialogComponent groupDialogComponent;

    public GroupManagerComponent() {
        DLText rootGroupHeading = new DLText(new DLBuilder().setText("Root Group").setFontSize(32).setMargin(new Insets(0, 0, 16, 0)));

        Controller.componentMap.rootGroupItemComponent = new GroupItemComponent(Group.getRootFromUser(Controller.user), GroupItemComponent.GroupItemMode.AuthorizedRootGroup);
        Controller.componentMap.rootGroupItemComponent.getContainer().setPadding(new Insets(0, 0, 0, 16));

        DLText groupListHeading = new DLText(new DLBuilder().setText("Group List").setFontSize(32).setMargin(new Insets(0, 0, 16, 0)));

        FlowPane buttonListContainer = new FlowPane();
        buttonListContainer.setPadding(new Insets(0, 0, 0, 16));

        createGroupButton = new DLButton(new DLBuilder().setText("Create").setActionEventEventHandler(new CreateGroupHandler()));

        editGroupButton = new DLButton(new DLBuilder().setText("Edit").setActionEventEventHandler(new EditGroupHandler()));

        deleteGroupButton = new DLButton(new DLBuilder().setText("Delete").setActionEventEventHandler(new DeleteGroupHandler()));


        groupListComponent = new GroupListComponent();
        groupListComponent.getContainer().setPadding(new Insets(0, 0, 0, 16));

        groupDialogComponent = new GroupDialogComponent(groupListComponent);

        getContainer().getChildren().add(rootGroupHeading);
        getContainer().getChildren().add(Controller.componentMap.rootGroupItemComponent.getContainer());
        getContainer().getChildren().add(groupListHeading);
        getContainer().getChildren().add(buttonListContainer);
        buttonListContainer.getChildren().add(createGroupButton);
        buttonListContainer.getChildren().add(editGroupButton);
        buttonListContainer.getChildren().add(deleteGroupButton);
        getContainer().getChildren().add(groupListComponent.getContainer());

        groupListComponent.updateAuthorizedGroupList();
    }

    private class CreateGroupHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            groupDialogComponent.createOrEdit(null, DialogMode.Create);
        }
    }

    private class EditGroupHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            VBox selectedGroup = (VBox) groupListComponent.groupListContainer.getSelectionModel().getSelectedItem();

            if (selectedGroup != null) {
                groupDialogComponent.createOrEdit((Group) selectedGroup.getProperties().get("group"), DialogMode.Edit);
            }
        }
    }

    private class DeleteGroupHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            VBox selectedGroup = (VBox) groupListComponent.groupListContainer.getSelectionModel().getSelectedItem();

            if (selectedGroup != null) {
                groupDialogComponent.delete((Group) selectedGroup.getProperties().get("group"));
            }
        }
    }
}
