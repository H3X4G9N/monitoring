package com.datalogging.client.components;

import com.datalogging.client.Controller;
import com.datalogging.client.components.group.GroupListComponent;
import com.datalogging.client.components.utility.IconComponent;
import com.datalogging.client.model.Group;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class BrowserComponent extends Component {
    public TextField searchInput;
    public GroupListComponent groupListComponent;
    public ScrollPane scrollContainer;

    public BrowserComponent(Controller controller) {
        super(controller);
        scrollContainer = new ScrollPane();
        VBox.setVgrow(scrollContainer, Priority.ALWAYS);

        groupListComponent = new GroupListComponent(getController());


        FlowPane topBarContainer = new FlowPane();
        topBarContainer.setMinHeight(48);

        IconComponent goBackIcon = new IconComponent(getController(),"/images/back.png");

        IconComponent searchIcon = new IconComponent(getController(),"/images/search.png");

        Button backButton = new Button();
        backButton.setGraphic(goBackIcon.imageView);
        backButton.setAlignment(Pos.CENTER_LEFT);
        backButton.setOnAction(new GoBackHandler());

        Text browseDataCollectorsHeading = new Text("Browse Data Collectors");
        browseDataCollectorsHeading.setStyle("-fx-font-size:16");

        HBox searchBar = new HBox();

        this.searchInput = new TextField();
        this.searchInput.setStyle("-fx-font-size:16");

        Button searchButton = new Button();
        searchButton.setGraphic(searchIcon.imageView);
        searchButton.setOnAction(new SearchHandler());

        VBox filtersContainer = new VBox();

        getContainer().getChildren().add(topBarContainer);
        topBarContainer.getChildren().add(backButton);
        topBarContainer.getChildren().add(browseDataCollectorsHeading);
        getContainer().getChildren().add(searchBar);
        searchBar.getChildren().add(searchInput);
        searchBar.getChildren().add(searchButton);
        getContainer().getChildren().add(filtersContainer);
        getContainer().getChildren().add(scrollContainer);
        scrollContainer.setContent(groupListComponent.getContainer());
        getContainer().getChildren().add(groupListComponent.getContainer());
    }

    private class SearchHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            List<Group> groupList = Group.getAllVisible();

            groupListComponent.updateGroupItemList(groupList);
        }
    }

    private class GoBackHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            getController().setRoot("data-logging");
        }
    }
}
