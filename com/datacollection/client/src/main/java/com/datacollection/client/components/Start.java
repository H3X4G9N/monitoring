package com.datacollection.client.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import com.datacollection.client.components.Card;

public class Start extends Component {
	public Start() {		
		this.getContainer().setAlignment(Pos.CENTER);
		
		Text monitoringHeading = new Text("Monitoring");
		VBox.setMargin(monitoringHeading, new Insets(0, 0, 32, 0));
		monitoringHeading.setStyle("-fx-font-size:64");
		this.getContainer().getChildren().add(monitoringHeading);
		
		FlowPane choiceContainer = new FlowPane();
		this.getContainer().getChildren().add(choiceContainer);
		choiceContainer.setAlignment(Pos.CENTER);
		
		Card browseDCCard = new Card("Browse Data Collectors", "Browse public data collector collections.");
		choiceContainer.getChildren().add(browseDCCard.getContainer());
		browseDCCard.addButon("browse", "Browse");
		
		Card manageDCCard = new Card("Manage Data Collectors", "Create and manage your data collector collection");
		choiceContainer.getChildren().add(manageDCCard.getContainer());
		manageDCCard.addButon("manage", "Manage");
		
		browseDCCard.getButton("browse").setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getController().setElement("data-collector-browser");
			}
		});
		
		manageDCCard.getButton("manage").setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getController().setElement("arrp");
			}
		});
	}
}
