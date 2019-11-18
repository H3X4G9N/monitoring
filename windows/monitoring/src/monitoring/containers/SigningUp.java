package monitoring.containers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import monitoring.containers.IDType;
import javafx.scene.control.Separator;
import javafx.scene.control.Hyperlink;

public class SigningUp extends Container {
	public SigningUp() {
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

		Label passwordLabel = new Label("Password");
		container.getChildren().add(passwordLabel);

		PasswordField passwordInput = new PasswordField();
		VBox.setMargin(passwordInput, new Insets(0, 0, 16, 0));
		container.getChildren().add(passwordInput);

		Label passwordConfirmationLabel = new Label("Password confirmation");
		container.getChildren().add(passwordConfirmationLabel);

		PasswordField passwordConfirmationInput = new PasswordField();
		VBox.setMargin(passwordConfirmationInput, new Insets(0, 0, 16, 0));
		container.getChildren().add(passwordConfirmationInput);
		

		Button signUpButton = new Button("Sign up");
		signUpButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			getContainerController().setContainer("signing-in");
		}
		});
		container.getChildren().add(signUpButton);
	}
}
