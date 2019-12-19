package com.datalogging.user.components.group;

import com.datalogging.user.Controller;
import com.datalogging.user.components.Component;
import com.datalogging.user.model.Group;
import com.datalogging.user.model.GroupAuthentication;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;

public class GroupListComponent extends Component {
    public ListView groupListContainer;

    public GroupListComponent() {
        VBox.setVgrow(getContainer(), Priority.ALWAYS);

        groupListContainer = new ListView();
        VBox.setVgrow(groupListContainer, Priority.ALWAYS);

        getContainer().getChildren().add(groupListContainer);
    }

    public void add(Group group, GroupItemComponent.GroupItemMode groupItemMode) {
        GroupItemComponent groupItem = new GroupItemComponent(group, groupItemMode);
        groupItem.getContainer().getProperties().put("group", group);

        groupListContainer.getItems().add(groupItem.getContainer());
    }

    public void updateUnauthorizedRootGroupList() {
        clear();

        List<Group> groupList = Group.getAllVisibleRoot();

        for (Group group : groupList) {
            add(group, GroupItemComponent.GroupItemMode.UnauthorizedRootGroupList);
        }
    }

    public void updateUnauthorizedGroupList(GroupAuthentication groupAuthentication) {
        clear();

        List<Group> groupList = Group.getAllVisible(groupAuthentication);

        for (Group group2 : groupList) {
            add(group2, GroupItemComponent.GroupItemMode.UnauthorizedGroupList);
        }
    }

    public void updateAuthorizedGroupList() {
        clear();

        List<Group> groupList = Group.getAllFromUser(Controller.user);

        for (Group group : groupList) {
            add(group, GroupItemComponent.GroupItemMode.AuthorizedGroupList);
        }
    }

    public void clear() {
        groupListContainer.getItems().clear();
    }
}
