package com.datalogging.user.components.grouppermission;

import com.datalogging.user.components.Component;
import com.datalogging.user.javafx.DLBuilder;
import com.datalogging.user.javafx.DLLabel;
import com.datalogging.user.javafx.DLTextField;
import com.datalogging.user.model.GroupPermission;

public class GroupPermissionItemComponent extends Component {
    public DLTextField permittedUserValue;
    private GroupPermission groupPermission;

    public GroupPermissionItemComponent(GroupPermission groupPermission) {
        this.groupPermission = groupPermission;

        DLLabel permittedUserKey = new DLLabel(new DLBuilder().setText("Permitted User"));

        permittedUserValue = new DLTextField(new DLBuilder().setText(groupPermission.getPermittedUser()).setEditable(false));

        getContainer().getChildren().add(permittedUserKey);
        getContainer().getChildren().add(permittedUserValue);
    }

    public GroupPermission getGroupPermission() {
        return groupPermission;
    }

    public void setGroupPermission(GroupPermission groupPermission) {
        this.groupPermission = groupPermission;
    }

    void update() {
        permittedUserValue.setText(groupPermission.getUser());
    }
}
