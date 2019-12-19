package com.datalogging.user.components;

import com.datalogging.user.Controller;
import com.datalogging.user.components.datalogger.DataLoggerManagerComponent;
import com.datalogging.user.components.group.GroupManagerComponent;
import com.datalogging.user.components.utility.TopBarComponent;
import com.datalogging.user.javafx.DLBuilder;
import com.datalogging.user.javafx.DLButton;
import com.datalogging.user.model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ManagementComponent extends Component {
    public TopBarComponent topBarComponent;
    public ScrollPane scrollContainer;
    public DashboardComponent dashboardComponent;
    public GroupManagerComponent groupManagerComponent;
    public DataLoggerManagerComponent dataLoggerManagerComponent;


    public ManagementComponent() {
        topBarComponent = new TopBarComponent(this, true, "Management", TopBarComponent.GoBackType.None);
        getContainer().getChildren().add(topBarComponent.getContainer());

        DLButton logOutButton = new DLButton(new DLBuilder().setText("Log Out").setActionEventEventHandler(new LogOutHandler()));
        topBarComponent.topBarContainer.getChildren().add(logOutButton);

        SplitPane splitContainer = new SplitPane();
        VBox.setVgrow(splitContainer, Priority.ALWAYS);

        VBox leftSideBarContainer = new VBox();
        leftSideBarContainer.setMaxWidth(180);
        scrollContainer = new ScrollPane();
        scrollContainer.setPadding(new Insets(16, 16, 16, 16));
        scrollContainer.setFitToWidth(true);
        scrollContainer.setFitToHeight(true);


        DLButton goToDashboardDLButton = new DLButton(new DLBuilder().setText("Dashboard").setActionEventEventHandler(new GoToDashboardEvent()));
        goToDashboardDLButton.setAlignment(Pos.CENTER_LEFT);
        goToDashboardDLButton.setMaxWidth(Double.MAX_VALUE);

        DLButton goToGroupManagerDLButton = new DLButton(new DLBuilder().setText("Group Manager").setActionEventEventHandler(new GoToGroupManagerEvent()));
        goToGroupManagerDLButton.setAlignment(Pos.CENTER_LEFT);
        goToGroupManagerDLButton.setMaxWidth(Double.MAX_VALUE);

        DLButton goToDataLoggerManagerDLButton = new DLButton(new DLBuilder().setText("Data Logger Manager").setActionEventEventHandler(new GoToDataLoggerManagerEvent()));
        goToDataLoggerManagerDLButton.setAlignment(Pos.CENTER_LEFT);
        goToDataLoggerManagerDLButton.setMaxWidth(Double.MAX_VALUE);

        DLButton goToBrowserDLButton = new DLButton(new DLBuilder().setText("Browser").setActionEventEventHandler(new GoToBrowserEvent()));
        goToBrowserDLButton.setAlignment(Pos.BASELINE_LEFT);
        goToBrowserDLButton.setMaxWidth(Double.MAX_VALUE);


        topBarComponent.mainContainer.getChildren().add(splitContainer);
        splitContainer.getItems().add(leftSideBarContainer);
        leftSideBarContainer.getChildren().add(goToDashboardDLButton);
        leftSideBarContainer.getChildren().add(goToGroupManagerDLButton);
        leftSideBarContainer.getChildren().add(goToDataLoggerManagerDLButton);
        leftSideBarContainer.getChildren().add(goToBrowserDLButton);
        splitContainer.getItems().add(scrollContainer);
    }

    private class LogOutHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String response = User.unauthorize(Controller.user);

            if (response != null) {
                Controller.scene.setRoot(Controller.componentMap.dataLoggingComponent.getContainer());
                Controller.user = null;
                scrollContainer.setContent(null);
            }
        }
    }

    private class GoToDashboardEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (dashboardComponent == null) {
                dashboardComponent = new DashboardComponent();
                dashboardComponent.getContainer().setMinHeight(2880);
            }

            scrollContainer.setContent(dashboardComponent.getContainer());
        }
    }

    private class GoToGroupManagerEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (groupManagerComponent == null) {
                groupManagerComponent = new GroupManagerComponent();
                groupManagerComponent.getContainer().setMinHeight(2880);
            }

            scrollContainer.setContent(groupManagerComponent.getContainer());

        }
    }

    private class GoToDataLoggerManagerEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (dataLoggerManagerComponent == null) {
                dataLoggerManagerComponent = new DataLoggerManagerComponent(null);
                dataLoggerManagerComponent.getContainer().setMinHeight(2880);
            }

            scrollContainer.setContent(dataLoggerManagerComponent.getContainer());
        }
    }

    private class GoToBrowserEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (Controller.componentMap.browserComponent == null) {
                Controller.componentMap.browserComponent = new BrowserComponent();
                Controller.componentMap.browserComponent.topBarComponent.setGoBackType(TopBarComponent.GoBackType.Scene);
                Controller.componentMap.browserComponent.setPreviousContainer(Controller.componentMap.dataLoggingComponent.getContainer());
            }

            Controller.scene.setRoot(Controller.componentMap.browserComponent.getContainer());
        }
    }
}
