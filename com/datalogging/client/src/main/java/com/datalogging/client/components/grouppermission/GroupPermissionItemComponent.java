package com.datalogging.client.components.grouppermission;

import com.datalogging.client.Controller;
import com.datalogging.client.components.Component;
import com.datalogging.client.model.GroupPermission;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GroupPermissionItemComponent extends Component {
    public TextField permittedUserValue;
    private GroupPermission groupPermission;

    public GroupPermissionItemComponent(Controller controller, GroupPermission groupPermission) {
        super(controller);
        this.groupPermission = groupPermission;

        Label permittedUserKey = new Label();
        permittedUserKey.setText("Permitted User");

        permittedUserValue = new TextField();
        permittedUserValue.setEditable(false);
        permittedUserValue.setText(groupPermission.getPermittedUser());

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
