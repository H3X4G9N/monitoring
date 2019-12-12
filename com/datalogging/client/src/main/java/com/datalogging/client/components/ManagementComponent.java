package com.datalogging.client.components;

import com.datalogging.client.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ManagementComponent extends Component {
    public ScrollPane scrollContainer;

    public ManagementComponent(Controller controller) {
        super(controller);
        FlowPane topBarContainer = new FlowPane();
        topBarContainer.setMinHeight(48);

        SplitPane splitContainer = new SplitPane();
        VBox.setVgrow(splitContainer, Priority.ALWAYS);

        VBox leftSideBarContainer = new VBox();
        leftSideBarContainer.setMaxWidth(180);

        scrollContainer = new ScrollPane();

        Button goToDashboardButton = new Button();
        goToDashboardButton.setText("Dashboard");
        goToDashboardButton.setAlignment(Pos.CENTER_LEFT);
        goToDashboardButton.setMaxWidth(Double.MAX_VALUE);
        goToDashboardButton.setOnAction(new GoToDashboardEvent());
        goToDashboardButton.setStyle("-fx-font-size:16");

        //IconComponent dashboardIcon = new IconComponent("/images/dashboard.png");
        //goToDashboardButton.setGraphic(dashboardIcon.imageView);

        Button goToGroupManagerButton = new Button();
        goToGroupManagerButton.setText("Group Manager");
        goToGroupManagerButton.setAlignment(Pos.CENTER_LEFT);
        goToGroupManagerButton.setMaxWidth(Double.MAX_VALUE);
        goToGroupManagerButton.setOnAction(new GoToGroupManagerEvent());
        goToGroupManagerButton.setStyle("-fx-font-size:16");

        //IconComponent groupManagerButton = new IconComponent("/images/group-manager.png");
        //goToGroupManagerButton.setGraphic(groupManagerButton.imageView);

        Button goToDataLoggerManagerButton = new Button();
        goToDataLoggerManagerButton.setText("Data Logger Manager");
        goToDataLoggerManagerButton.setAlignment(Pos.CENTER_LEFT);
        goToDataLoggerManagerButton.setMaxWidth(Double.MAX_VALUE);
        goToDataLoggerManagerButton.setOnAction(new GoToDataLoggerManagerEvent());
        goToDataLoggerManagerButton.setStyle("-fx-font-size:16");

        //IconComponent dataLoggerManagerIcon = new IconComponent("/images/data-logger-manager.png");
        //goToDataLoggerManagerButton.setGraphic(dataLoggerManagerIcon.imageView);

        Button goToBrowserButton = new Button();
        goToBrowserButton.setText("Browser");
        goToBrowserButton.setAlignment(Pos.BASELINE_LEFT);
        goToBrowserButton.setMaxWidth(Double.MAX_VALUE);
        goToBrowserButton.setOnAction(new GoToBrowserEvent());
        goToBrowserButton.setStyle("-fx-font-size:16");

        //IconComponent browserIcon = new IconComponent("/images/browser.png");
        //goToBrowserButton.setGraphic(browserIcon.imageView);

        getContainer().getChildren().add(topBarContainer);
        getContainer().getChildren().add(splitContainer);
        splitContainer.getItems().add(leftSideBarContainer);
        leftSideBarContainer.getChildren().add(goToDashboardButton);
        leftSideBarContainer.getChildren().add(goToGroupManagerButton);
        leftSideBarContainer.getChildren().add(goToDataLoggerManagerButton);
        leftSideBarContainer.getChildren().add(goToBrowserButton);
        splitContainer.getItems().add(scrollContainer);
    }

    private class GoToDashboardEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            scrollContainer.setContent(getController().getComponent("dashboard").getContainer());
        }
    }

    private class GoToGroupManagerEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            scrollContainer.setContent(getController().getComponent("group-manager").getContainer());
        }
    }

    private class GoToDataLoggerManagerEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            scrollContainer.setContent(getController().getComponent("data-logger-manager").getContainer());
        }
    }

    private class GoToBrowserEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            scrollContainer.setContent(getController().getComponent("browser").getContainer());
        }
    }
}
