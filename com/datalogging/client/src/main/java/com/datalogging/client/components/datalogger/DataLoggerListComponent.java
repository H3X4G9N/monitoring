package com.datalogging.client.components.datalogger;

import com.datalogging.client.Controller;
import com.datalogging.client.components.Component;
import com.datalogging.client.model.DataLogger;
import com.datalogging.client.model.Group;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;

import java.util.ArrayList;
import java.util.List;

public class DataLoggerListComponent extends Component {
    public ScrollPane scrollPane;
    public ListView listContainer;
    private List<DataLoggerItemComponent> groupItemList;


    public DataLoggerListComponent(Controller controller) {
        super(controller);
        groupItemList = new ArrayList<>();
        scrollPane = new ScrollPane();
        getContainer().getChildren().add(scrollPane);
        listContainer = new ListView();
        scrollPane.setContent(listContainer);

    }

    public void getGroupItem() {

    }

    public void addGroupItem(DataLogger datalogger) {
        DataLoggerItemComponent groupItem = new DataLoggerItemComponent(getController(), datalogger);
        groupItem.getContainer().getProperties().put("group", datalogger);
        groupItemList.add(groupItem);
        listContainer.getItems().add(groupItem.getContainer());
    }

    public void removeGroupItem(Group group) {

    }

    public void updateGroupItemList(List<DataLogger> groupList) {
        clearGroupItemList();

        for (DataLogger datalogger : groupList) {
            addGroupItem(datalogger);
        }
    }

    public void clearGroupItemList() {
        groupItemList.clear();
        listContainer.getItems().clear();
    }
}