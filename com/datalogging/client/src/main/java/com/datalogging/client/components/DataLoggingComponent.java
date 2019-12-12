package com.datalogging.client.components;

import com.datalogging.client.Controller;
import com.datalogging.client.components.utility.CardComponent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DataLoggingComponent extends Component {
    public DataLoggingComponent(Controller controller) {
        super(controller);
        getContainer().setAlignment(Pos.CENTER);

        Text monitoringHeading = new Text("Monitoring");
        VBox.setMargin(monitoringHeading, new Insets(0, 0, 32, 0));
        monitoringHeading.setStyle("-fx-font-size:64");

        FlowPane choiceContainer = new FlowPane();
        choiceContainer.setAlignment(Pos.CENTER);

        CardComponent browseCard = new CardComponent(getController(),"Browse", "Browse all visible data loggers!");
        browseCard.addButon("browse", "Browse");
        browseCard.getButton("browse").setOnAction(new BrowseHandler());
        browseCard.getContainer().setAlignment(Pos.CENTER);
        browseCard.buttonListContainer.setAlignment(Pos.CENTER);

        CardComponent manageCard = new CardComponent(getController(),"Manage", "Manage all your data loggers!");
        manageCard.addButon("manage", "Manage");
        manageCard.getButton("manage").setOnAction(new ManageHandler());
        manageCard.getContainer().setAlignment(Pos.CENTER);
        manageCard.buttonListContainer.setAlignment(Pos.CENTER);

        getContainer().getChildren().add(monitoringHeading);
        getContainer().getChildren().add(choiceContainer);
        choiceContainer.getChildren().add(browseCard.getContainer());
        choiceContainer.getChildren().add(manageCard.getContainer());
    }

    private class BrowseHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            getController().setRoot("browser");
        }
    }

    private class ManageHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            getController().setRoot("authorization");
        }
    }
}
