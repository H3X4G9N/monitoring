package com.datalogging.user.components.datalogger;

import com.datalogging.user.Controller;
import com.datalogging.user.components.Component;
import com.datalogging.user.model.DataLogger;
import com.datalogging.user.model.Group;
import com.datalogging.user.model.GroupAuthentication;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;

public class DataLoggerListComponent extends Component {
    public ListView listContainer;

    public DataLoggerListComponent() {
        listContainer = new ListView();

        VBox.setVgrow(listContainer, Priority.ALWAYS);

        VBox.setVgrow(getContainer(), Priority.ALWAYS);

        getContainer().getChildren().add(listContainer);
    }


    public void add(DataLogger dataLogger, DataLoggerItemComponent.DataLoggerItemMode dataLoggerItemMode) {
        DataLoggerItemComponent dataLoggerItemComponent = new DataLoggerItemComponent(dataLogger, dataLoggerItemMode);
        dataLoggerItemComponent.getContainer().getProperties().put("data-logger", dataLogger);

        listContainer.getItems().add(dataLoggerItemComponent.getContainer());
    }

    public void update2(GroupAuthentication groupAuthentication) {
        clear();

        List<DataLogger> dataLoggerList = DataLogger.getAllFromGroup(groupAuthentication);

        for (DataLogger datalogger : dataLoggerList) {
            add(datalogger, DataLoggerItemComponent.DataLoggerItemMode.UnauthorizedDataLoggerList);
        }
    }

    public void update3(Group group) {
        clear();

        List<DataLogger> dataLoggerList = DataLogger.getAllFromGroup(Controller.user, group);

        for (DataLogger datalogger : dataLoggerList) {
            add(datalogger, DataLoggerItemComponent.DataLoggerItemMode.AuthorizedDataLoggerList);
        }
    }

    public void update() {
        clear();

        List<DataLogger> dataLoggerList = DataLogger.getAllFromUser(Controller.user);

        for (DataLogger datalogger : dataLoggerList) {
            add(datalogger, DataLoggerItemComponent.DataLoggerItemMode.AuthorizedDataLoggerList);
        }
    }

    public void clear() {
        listContainer.getItems().clear();
    }
}