package com.datalogging.user.components.group;

import com.datalogging.user.Controller;
import com.datalogging.user.components.Component;
import com.datalogging.user.components.datalogger.DataLoggerListComponent;
import com.datalogging.user.components.datalogger.DataLoggerManagerComponent;
import com.datalogging.user.components.grouppermission.GroupPermissionManagerComponent;
import com.datalogging.user.components.utility.TopBarComponent;
import com.datalogging.user.javafx.DLBuilder;
import com.datalogging.user.javafx.DLText;
import com.datalogging.user.model.Group;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class GroupComponent extends Component {
    public TopBarComponent topBarComponent;
    public GroupItemComponent groupItemComponent;
    public GroupPermissionManagerComponent groupPermissionManagerComponent;
    public DataLoggerManagerComponent dataLoggerManagerComponent;
    public GroupListComponent groupListComponent;
    public DataLoggerListComponent dataLoggerListComponent;

    public GroupComponent(Group group, GroupMode groupMode) {
        getContainer().setMinHeight(1440);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        VBox.setVgrow(getContainer(), Priority.ALWAYS);

        getContainer().getChildren().add(scrollPane);
        VBox.setVgrow(getContainer(), Priority.ALWAYS);

        topBarComponent = new TopBarComponent(this, true, "Group", TopBarComponent.GoBackType.Scene);
        topBarComponent.getContainer().setMinHeight(2880);
        VBox.setVgrow(topBarComponent.getContainer(), Priority.ALWAYS);
        scrollPane.setContent(topBarComponent.getContainer());

        DLText groupHeading = new DLText(new DLBuilder().setText("Group").setFontSize(32).setMargin(new Insets(0, 0, 16, 0)));
        topBarComponent.mainContainer.getChildren().add(groupHeading);

        switch (groupMode) {
            case Authorized:
                groupItemComponent = new GroupItemComponent(group, GroupItemComponent.GroupItemMode.AuthorizedRootGroup);
                topBarComponent.mainContainer.getChildren().add(groupItemComponent.getContainer());

                groupPermissionManagerComponent = new GroupPermissionManagerComponent(group);
                topBarComponent.mainContainer.getChildren().add(groupPermissionManagerComponent.getContainer());

                dataLoggerManagerComponent = new DataLoggerManagerComponent(group);
                topBarComponent.mainContainer.getChildren().add(dataLoggerManagerComponent.getContainer());
                break;
            case UnauthorizedRoot:
                groupItemComponent = new GroupItemComponent(group, GroupItemComponent.GroupItemMode.UnauthorizedRootGroupList);
                groupItemComponent.getContainer().setPadding(new Insets(0, 0, 0, 16));
                topBarComponent.mainContainer.getChildren().add(groupItemComponent.getContainer());
                groupItemComponent.getContainer().getChildren().remove(groupItemComponent.actionButton);

                DLText groupListHeading = new DLText(new DLBuilder().setText("Group List").setFontSize(32).setMargin(new Insets(0, 0, 16, 0)));

                groupListComponent = new GroupListComponent();
                groupListComponent.getContainer().setPadding(new Insets(0, 0, 0, 16));
                topBarComponent.mainContainer.getChildren().add(groupListHeading);
                topBarComponent.mainContainer.getChildren().add(groupListComponent.getContainer());

                if (Controller.groupAuthenticationMap.containsKey(group.getID())) {
                    groupListComponent.updateUnauthorizedGroupList(Controller.groupAuthenticationMap.get(group.getID()));
                }
                break;
            case Unauthorized:
                groupItemComponent = new GroupItemComponent(group, GroupItemComponent.GroupItemMode.UnauthorizedGroupList);
                topBarComponent.mainContainer.getChildren().add(groupItemComponent.getContainer());
                groupItemComponent.getContainer().getChildren().remove(groupItemComponent.actionButton);
                groupItemComponent.getContainer().setPadding(new Insets(0, 0, 0, 16));

                DLText dataLoggerListHeading = new DLText(new DLBuilder().setText("Data Logger List").setFontSize(32).setMargin(new Insets(0, 0, 16, 0)));

                dataLoggerListComponent = new DataLoggerListComponent();
                dataLoggerListComponent.getContainer().setPadding(new Insets(0, 0, 0, 16));
                topBarComponent.mainContainer.getChildren().add(dataLoggerListHeading);
                topBarComponent.mainContainer.getChildren().add(dataLoggerListComponent.getContainer());

                if (Controller.groupAuthenticationMap.containsKey(group.getID())) {
                    dataLoggerListComponent.update2(Controller.groupAuthenticationMap.get(group.getID()));
                }
                break;
        }
    }

    public enum GroupMode {
        Authorized,
        UnauthorizedRoot,
        Unauthorized
    }
}
