package monitoring.containers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import monitoring.containers.IDType;
import javafx.scene.control.Separator;
import javafx.scene.control.Hyperlink;

public class SigningIn extends Container {
	private String[] idTypes = {
			"Email",
			"Username"
		};
		private IDType idType;
		private TextField idInput;
		private PasswordField passwordInput;
		private Button signInButton;
		
	    private class SignInEventHandler implements EventHandler<ActionEvent> {
	    	@Override
	        public void handle(ActionEvent event) {
	    		String value = idInput.getText();
	    		String key = passwordInput.getText();
	    		
	    		if (value.equals("") && key.equals("")) {
	    			getContainerController().setContainer("data-collector-management");
	    		}
	    	}
	    }
	
	public SigningIn(IDType idType) {
		this.idType = idType;
		

		this.getRoot().setAlignment(Pos.CENTER);
		
		
		VBox centerContainer = new VBox();
		centerContainer.setPrefWidth(320);
		centerContainer.setPrefHeight(180);
		centerContainer.setMaxWidth(320);
		centerContainer.setMaxHeight(180);

		this.getRoot().getChildren().add(centerContainer);
		
		VBox textContainer = new VBox();
		centerContainer.getChildren().add(textContainer);

		
		Label signInHeading = new Label("Monitoring");
		signInHeading.setTextFill(Color.rgb(255, 255, 255));
		signInHeading.setPadding(new Insets(6, 6, 6, 6));
		
		signInHeading.setStyle("-fx-font-size:24");
		textContainer.getChildren().add(signInHeading);
		textContainer.setStyle("-fx-background-color:rgb(255, 190, 92)");
		
		VBox.setMargin(textContainer, new Insets(0, 0, 32, 0));
		
		this.idInput = new TextField();
		this.idInput.setPromptText(this.idTypes[this.idType.ordinal()]);
		VBox.setMargin(idInput, new Insets(0, 0, 8, 0));
		centerContainer.getChildren().add(this.idInput);


		this.passwordInput = new PasswordField();
		this.passwordInput.setPromptText("Password");
		VBox.setMargin(passwordInput, new Insets(0, 0, 16, 0));
		centerContainer.getChildren().add(this.passwordInput);
		
		this.signInButton = new Button("Sign In");
		this.signInButton.setOnAction(new SignInEventHandler());
		this.signInButton.setMaxWidth(Double.MAX_VALUE);
		this.signInButton.getStyleClass().add("jfx-button");
		centerContainer.getChildren().add(this.signInButton);
		
		Separator separator = new Separator();
		separator.setMinHeight(32);
		centerContainer.getChildren().add(separator);

		Hyperlink signUpLink = new Hyperlink("Sign up");
		signUpLink.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			getContainerController().setContainer("signing-up");
		}
		});
		centerContainer.getChildren().add(signUpLink);

		Hyperlink resetPasswordLink = new Hyperlink("Reset password");
		resetPasswordLink.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			getContainerController().setContainer("resetting-password");
		}
		});
		centerContainer.getChildren().add(resetPasswordLink);
		
	}
	
	void setValueType(IDType valueType) {
		this.idType = valueType;
		this.idInput.setPromptText(this.idTypes[this.idType.ordinal()]);
	}
}
