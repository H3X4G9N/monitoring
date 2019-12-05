package com.datacollection.client.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import com.datacollection.client.components.ARRPType;
import com.datacollection.client.components.IDType;

public class ARRP extends Component {
	private ARRPType arrpType;
	private IDType idType;
	private TextField idInput;
	private PasswordField passwordInput;
	private PasswordField passwordConfirmationInput;
	private Button actionButton;
	private Hyperlink actionLink1;
	private Hyperlink actionLink2;
	
	private String[] idTypes = {
		"Email",
		"Username"
	};
	
    private class ActionLinkHandler1 implements EventHandler<ActionEvent> {
    	@Override
        public void handle(ActionEvent event) {
    		
    	}
    }
    
    private class ActionLinkHandler2 implements EventHandler<ActionEvent> {
    	@Override
        public void handle(ActionEvent event) {
    		
    	}
    }
	
    private class ARRPEventHandler implements EventHandler<ActionEvent> {
    	@Override
        public void handle(ActionEvent event) {
    		switch (arrpType) {
    		case Authorization:
    			break;
    		case Registration:
    			break;
    		case ResettingPassword:
    			break;
    		}
    	}
    }
    
	public ARRP(ARRPType arrpType, IDType idType) {
		this.arrpType = arrpType;
		this.idType = idType;
		
		this.getContainer().setAlignment(Pos.CENTER);
		
		VBox centeredContainer = new VBox();
		centeredContainer.setPrefWidth(320);
		centeredContainer.setPrefHeight(180);
		centeredContainer.setMaxWidth(320);
		centeredContainer.setMaxHeight(180);
		
		VBox textContainer = new VBox();

		Label monitoringHeading = new Label("Monitoring");
		monitoringHeading.setTextFill(Color.rgb(255, 255, 255));
		monitoringHeading.setPadding(new Insets(6, 6, 6, 6));

		monitoringHeading.setStyle("-fx-font-size:24");
		textContainer.getChildren().add(monitoringHeading);
		textContainer.setStyle("-fx-background-color:rgb(255, 190, 92)");
		
		VBox.setMargin(textContainer, new Insets(0, 0, 32, 0));
		
		this.idInput = new TextField();
		this.idInput.setPromptText(this.idTypes[this.idType.ordinal()]);
		VBox.setMargin(idInput, new Insets(0, 0, 8, 0));
		
		this.passwordInput = new PasswordField();
		this.passwordInput.setPromptText("Password");
		VBox.setMargin(passwordInput, new Insets(0, 0, 16, 0));
		
		this.actionButton = new Button();
		this.actionButton.setOnAction(new ARRPEventHandler());
		this.actionButton.setMaxWidth(Double.MAX_VALUE);
		
		Separator separator = new Separator();
		separator.setMinHeight(32);
		
		this.actionLink1 = new Hyperlink();
		this.actionLink1.setOnAction(new ActionLinkHandler1());
		
		this.actionLink2 = new Hyperlink();
		actionLink2.setOnAction(new ActionLinkHandler2());
		
		
	}
	
	void setARRPMode(ARRPType arrpType) {
		this.arrpType = arrpType;
		
		switch (arrpType) {
		case Authorization:
			break;
		case Registration:
			break;
		case ResettingPassword:
			break;
		}
	}
	
	void setValueType(IDType valueType) {
		this.idType = valueType;
		this.idInput.setPromptText(this.idTypes[this.idType.ordinal()]);
	}
}
