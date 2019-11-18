package monitoring.containers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ResettingPassword extends Container {
	public ResettingPassword() {
		HBox appTopBar = new HBox();
		this.getRoot().getChildren().add(appTopBar);
		
		Image backImage = new Image(getClass().getResource("/res/img/back.png").toExternalForm());
		
		ImageView backIcon = new ImageView();
		backIcon.setFitWidth(16);
		backIcon.setFitHeight(16);
		backIcon.setImage(backImage);
		
		Button backButton = new Button();
		backButton.setGraphic(backIcon);
		backButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getContainerController().setContainer("signing-in");
			}
		});
		appTopBar.getChildren().add(backButton);
		
		Text browseDataCollectorsHeading = new Text("Browse Data Collectors");
		appTopBar.getChildren().add(browseDataCollectorsHeading);
		
		VBox container = new VBox();
		container.setMinWidth(320);
		container.setMinHeight(180);
		container.setPrefWidth(320);
		container.setPrefHeight(180);
		this.getRoot().getChildren().add(container);

		Label emailLabel = new Label("Email");
		container.getChildren().add(emailLabel);

		TextField emailInput = new TextField();
		VBox.setMargin(emailInput, new Insets(0, 0, 16, 0));
		container.getChildren().add(emailInput);

		Button resetPasswordButton = new Button("Reset password");
		resetPasswordButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getContainerController().setContainer("signing-in");
			}
		});
		container.getChildren().add(resetPasswordButton);
	}
}
