package com.datalogging.user.components;

import com.datalogging.user.Controller;
import com.datalogging.user.components.utility.CardComponent;
import com.datalogging.user.components.utility.TopBarComponent;
import com.datalogging.user.javafx.DLBuilder;
import com.datalogging.user.javafx.DLButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;

public class DataLoggingComponent extends Component {
    public DataLoggingComponent() {
        TopBarComponent topBarComponent = new TopBarComponent(this, false, "", TopBarComponent.GoBackType.None);
        topBarComponent.mainContainer.setAlignment(Pos.CENTER);

        FlowPane choiceContainer = new FlowPane();
        choiceContainer.setAlignment(Pos.CENTER);

        CardComponent manageCard = new CardComponent("Manage", "Manage all your data loggers!");
        manageCard.addButton("manage", new DLButton(new DLBuilder().setText("Manage").setActionEventEventHandler(new ManageHandler())));

        CardComponent browseCard = new CardComponent("Browse", "Browse all public data loggers!");
        browseCard.addButton("browse", new DLButton(new DLBuilder().setText("Browse").setActionEventEventHandler(new BrowseHandler())));

        getContainer().getChildren().add(topBarComponent.getContainer());
        topBarComponent.mainContainer.getChildren().add(choiceContainer);
        choiceContainer.getChildren().add(manageCard.getContainer());
        choiceContainer.getChildren().add(browseCard.getContainer());
    }

    private class ManageHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (Controller.componentMap.authorizationComponent == null) {
                Controller.componentMap.authorizationComponent = new AuthorizationComponent();
                Controller.componentMap.authorizationComponent.setPreviousContainer(Controller.componentMap.dataLoggingComponent.getContainer());
            }

            if (Controller.user != null) {
                Controller.scene.setRoot(Controller.componentMap.managementComponent.getContainer());
            } else {
                Controller.scene.setRoot(Controller.componentMap.authorizationComponent.getContainer());
            }
        }
    }

    private class BrowseHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (Controller.componentMap.browserComponent == null) {
                Controller.componentMap.browserComponent = new BrowserComponent();
                Controller.componentMap.browserComponent.setPreviousContainer(Controller.componentMap.dataLoggingComponent.getContainer());
            }

            Controller.scene.setRoot(Controller.componentMap.browserComponent.getContainer());
        }
    }
}
