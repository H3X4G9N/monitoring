package com.datalogging.client.components.grouppermission;

import com.datalogging.client.Controller;
import com.datalogging.client.components.Component;
import com.datalogging.client.model.Group;
import com.datalogging.client.model.GroupPermission;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;

import java.util.ArrayList;
import java.util.List;

public class GroupPermissionListComponent extends Component {
    public ScrollPane scrollPane;
    public ListView listContainer;
    private List<GroupPermissionItemComponent> groupPermissionList;

    public GroupPermissionListComponent(Controller controller) {
        super(controller);
        groupPermissionList = new ArrayList<>();
        scrollPane = new ScrollPane();
        getContainer().getChildren().add(scrollPane);
        listContainer = new ListView();
        scrollPane.setContent(listContainer);
    }

    public void getGroupItem() {

    }

    public void addGroupPermissionItem(GroupPermission groupPermission) {
        GroupPermissionItemComponent groupPermissionItem = new GroupPermissionItemComponent(getController(), groupPermission);
        groupPermissionItem.getContainer().getProperties().put("group-permission", groupPermission);
        groupPermissionList.add(groupPermissionItem);
        listContainer.getItems().add(groupPermissionItem.getContainer());
    }

    public void removeGroupPermissionItem(Group group) {

    }

    public void updateGroupItemList(List<GroupPermission> groupPermissionList) {
        clearGroupPermissionItemList();

        for (GroupPermission groupPermission : groupPermissionList) {
            addGroupPermissionItem(groupPermission);
        }
    }

    public void clearGroupPermissionItemList() {
        groupPermissionList.clear();
        listContainer.getItems().clear();
    }
}
