package com.datalogging.client.components.group;

import com.datalogging.client.Controller;
import com.datalogging.client.components.Component;
import com.datalogging.client.components.DialogMode;
import com.datalogging.client.model.Group;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class GroupManagerComponent extends Component {
    public Button createGroupButton;
    public Button editGroupButton;
    public Button deleteGroupButton;
    public Button reloadGroupListButton;
    public GroupListComponent groupListComponent;

    public GroupManagerComponent(Controller controller) {
        super(controller);
        FlowPane buttonListContainer = new FlowPane();
        groupListComponent = new GroupListComponent(getController());

        createGroupButton = new Button();
        createGroupButton.setText("Create");
        createGroupButton.setOnAction(new CreateGroupHandler());
        createGroupButton.setStyle("-fx-font-size:16");

        editGroupButton = new Button();
        editGroupButton.setText("Edit");
        editGroupButton.setOnAction(new EditGroupHandler());
        editGroupButton.setStyle("-fx-font-size:16");

        deleteGroupButton = new Button();
        deleteGroupButton.setText("Delete");
        deleteGroupButton.setOnAction(new DeleteGroupHandler());
        deleteGroupButton.setStyle("-fx-font-size:16");

        reloadGroupListButton = new Button();
        reloadGroupListButton.setText("Reload");
        reloadGroupListButton.setOnAction(new ReloadGroupListHandler());
        reloadGroupListButton.setStyle("-fx-font-size:16");

        getContainer().getChildren().add(buttonListContainer);
        buttonListContainer.getChildren().add(createGroupButton);
        buttonListContainer.getChildren().add(editGroupButton);
        buttonListContainer.getChildren().add(deleteGroupButton);
        buttonListContainer.getChildren().add(reloadGroupListButton);
        getContainer().getChildren().add(groupListComponent.getContainer());
    }

    private class ReloadGroupListHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            List<Group> groupList = Group.getAllFromUser(getController().getUser());

            if (groupList != null) {
                groupListComponent.updateGroupItemList(groupList);
            }
        }
    }

    private class CreateGroupHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            GroupDialog groupDialog = new GroupDialog(getController());
            groupDialog.createEdit(DialogMode.Create, new Group());
        }
    }

    private class EditGroupHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            GroupDialog groupDialog = new GroupDialog(getController());

            VBox selectedItem = (VBox) groupListComponent.listContainer.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                groupDialog.createEdit(DialogMode.Edit, (Group) selectedItem.getProperties().get("group"));
            }
        }
    }

    private class DeleteGroupHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            GroupDialog groupDialog = new GroupDialog(getController());

            VBox selectedItem = (VBox) groupListComponent.listContainer.getSelectionModel().getSelectedItem();


            if (selectedItem != null) {
                groupDialog.delete((Group) selectedItem.getProperties().get("group"));
            }
        }
    }
}
