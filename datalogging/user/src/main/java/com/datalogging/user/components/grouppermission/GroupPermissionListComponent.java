package com.datalogging.user.components.grouppermission;

import com.datalogging.user.Controller;
import com.datalogging.user.components.Component;
import com.datalogging.user.model.Group;
import com.datalogging.user.model.GroupPermission;
import javafx.scene.control.ListView;

import java.util.List;

public class GroupPermissionListComponent extends Component {
    public ListView listContainer;

    public GroupPermissionListComponent() {
        listContainer = new ListView();

        getContainer().getChildren().add(listContainer);
    }

    public void add(GroupPermission groupPermission) {
        GroupPermissionItemComponent groupPermissionItem = new GroupPermissionItemComponent(groupPermission);
        groupPermissionItem.getContainer().getProperties().put("group-permission", groupPermission);

        listContainer.getItems().add(groupPermissionItem.getContainer());
    }

    public void update(Group group) {
        clear();

        List<GroupPermission> groupPermissionList = GroupPermission.getAllFromGroup(Controller.user, group);

        for (GroupPermission groupPermission : groupPermissionList) {
            add(groupPermission);
        }
    }

    public void clear() {
        listContainer.getItems().clear();
    }
}
