package com.datalogging.client.components.group;

import com.datalogging.client.Controller;
import com.datalogging.client.components.Component;
import com.datalogging.client.components.ManagementComponent;
import com.datalogging.client.model.Group;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;



public class GroupItemComponent extends Component {
    public TextField groupNameValue;
    public TextField groupDescriptionValue;
    public Button viewMoreButton;
    private Group group;

    public GroupItemComponent(Controller controller, Group group) {
        super(controller);
        this.group = group;

        Label groupNameKey = new Label();
        groupNameKey.setText("Name");

        groupNameValue = new TextField();
        groupNameValue.setEditable(false);
        groupNameValue.setText(group.getName());

        Label groupDescriptionKey = new Label();
        groupDescriptionKey.setText("Description");

        this.setContainer(new VBox());

        groupDescriptionValue = new TextField();
        groupDescriptionValue.setEditable(false);
        groupDescriptionValue.setText(group.getDescription());

        viewMoreButton = new Button();
        viewMoreButton.setOnAction(new ViewMoreHandler());
        viewMoreButton.setText("View More");

        getContainer().getChildren().add(groupNameKey);
        getContainer().getChildren().add(groupNameValue);
        getContainer().getChildren().add(groupDescriptionKey);
        getContainer().getChildren().add(groupDescriptionValue);
        getContainer().getChildren().add(viewMoreButton);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    void update() {
        groupNameValue.setText(group.getName());
        groupDescriptionValue.setText(group.getDescription());
    }

    private class ViewMoreHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            ManagementComponent managementComponent = (ManagementComponent)getController().getComponent("management");

            managementComponent.scrollContainer.setContent(new GroupComponent(getController(), group).getContainer());
        }
    }
}
