package com.datalogging.user.components;

import com.datalogging.user.components.group.GroupListComponent;
import com.datalogging.user.components.utility.TopBarComponent;
import com.datalogging.user.javafx.DLBuilder;
import com.datalogging.user.javafx.DLButton;
import com.datalogging.user.javafx.DLTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class BrowserComponent extends Component {
    public ScrollPane scrollPane;
    public TopBarComponent topBarComponent;
    public DLButton searchButton;
    public GroupListComponent groupListComponent;

    public BrowserComponent() {
        scrollPane = new ScrollPane();
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        getContainer().setMinHeight(2880);
        VBox.setVgrow(getContainer(), Priority.ALWAYS);

        topBarComponent = new TopBarComponent(this, true, "Browser", TopBarComponent.GoBackType.Scene);

        HBox searchContainer = new HBox();

        searchButton = new DLButton(new DLBuilder().setText("Load").setActionEventEventHandler(new SearchHandler()));

        groupListComponent = new GroupListComponent();
        VBox.setVgrow(groupListComponent.getContainer(), Priority.ALWAYS);
        VBox.setVgrow(groupListComponent.groupListContainer, Priority.ALWAYS);

        getContainer().getChildren().add(scrollPane);
        scrollPane.setContent(topBarComponent.getContainer());
        topBarComponent.mainContainer.getChildren().add(searchContainer);
        searchContainer.getChildren().add(searchButton);
        topBarComponent.mainContainer.getChildren().add(groupListComponent.getContainer());
    }

    private class SearchHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            groupListComponent.updateUnauthorizedRootGroupList();
        }
    }
}
