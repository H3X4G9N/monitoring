package com.datalogging.client.components.group;

import com.datalogging.client.Controller;
import com.datalogging.client.components.Component;
import com.datalogging.client.components.grouppermission.GroupPermissionManagerComponent;
import com.datalogging.client.model.Group;
import javafx.scene.control.*;

public class GroupComponent extends Component {
    public TextField groupNameValue;
    public TextArea groupDescriptionValue;
    public CheckBox permissionRequiredValue;
    public GroupPermissionManagerComponent groupPermissionManagerComponent;

    public GroupComponent(Controller controller, Group group) {
        super(controller);
        Label groupNameKey = new Label();
        groupNameKey.setText("Name");

        groupNameValue = new TextField();
        groupNameValue.setText(group.getName());
        groupNameValue.setEditable(false);

        Label groupDescriptionKey = new Label();
        groupDescriptionKey.setText("Description");

        groupDescriptionValue = new TextArea();
        groupDescriptionValue.setText(group.getDescription());
        groupDescriptionValue.setEditable(false);

        CheckBox visibleValue = new CheckBox();
        visibleValue.setText("Is Visible");
        visibleValue.setDisable(true);
        visibleValue.setStyle("-fx-opacity: 1");

        permissionRequiredValue = new CheckBox();
        permissionRequiredValue.setText("Is Permission Required");
        permissionRequiredValue.setSelected(group.getPermissionRequired());
        permissionRequiredValue.setDisable(true);
        permissionRequiredValue.setStyle("-fx-opacity: 1");

        groupPermissionManagerComponent = new GroupPermissionManagerComponent(getController(), group);

        getContainer().getChildren().add(groupNameKey);
        getContainer().getChildren().add(groupNameValue);
        getContainer().getChildren().add(groupDescriptionKey);
        getContainer().getChildren().add(groupDescriptionValue);
        getContainer().getChildren().add(visibleValue);
        getContainer().getChildren().add(permissionRequiredValue);
        getContainer().getChildren().add(groupPermissionManagerComponent.getContainer());
    }
}
