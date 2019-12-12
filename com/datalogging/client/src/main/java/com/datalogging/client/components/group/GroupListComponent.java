package com.datalogging.client.components.group;

import com.datalogging.client.Controller;
import com.datalogging.client.components.Component;
import com.datalogging.client.model.Group;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;

import java.util.ArrayList;
import java.util.List;



public class GroupListComponent extends Component {
    public ScrollPane scrollPane;
    public ListView listContainer;
    private List<GroupItemComponent> groupItemList;

    public GroupListComponent(Controller controller) {
        super(controller);
        groupItemList = new ArrayList<>();
        scrollPane = new ScrollPane();
        getContainer().getChildren().add(scrollPane);
        listContainer = new ListView();
        scrollPane.setContent(listContainer);
    }

    public void getGroupItem() {

    }

    public void addGroupItem(Group group) {
        GroupItemComponent groupItem = new GroupItemComponent(getController(), group);
        groupItem.getContainer().getProperties().put("group", group);
        groupItemList.add(groupItem);
        listContainer.getItems().add(groupItem.getContainer());
    }

    public void removeGroupItem(Group group) {

    }

    public void updateGroupItemList(List<Group> groupList) {
        clearGroupItemList();

        for (Group group : groupList) {
            addGroupItem(group);
        }
    }

    public void clearGroupItemList() {
        groupItemList.clear();
        listContainer.getItems().clear();
    }
}
