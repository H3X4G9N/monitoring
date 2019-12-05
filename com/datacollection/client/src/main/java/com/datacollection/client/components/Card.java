package com.datacollection.client.components;

import javafx.geometry.Insets;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class Card extends Component {
	private Text headerText;
	private Text supportingText;
	private FlowPane buttonContainer;
	private Map<String, Button> buttons;
	
	public Card(String headerText, String supportingText) {
		this.headerText = new Text(headerText);
		this.headerText.setStyle("-fx-font-size:24");
		VBox.setMargin(this.headerText, new Insets(0, 0, 12, 0));
		this.getContainer().getChildren().add(this.headerText);
		
		this.supportingText = new Text(supportingText);
		this.supportingText.setStyle("-fx-font-size:16");
		VBox.setMargin(this.supportingText, new Insets(0, 0, 8, 0));
		this.getContainer().getChildren().add(this.supportingText);
		
		this.buttonContainer = new FlowPane();
		VBox.setMargin(buttonContainer, new Insets(0, 0, 16, 0));
		this.getContainer().getChildren().add(this.buttonContainer);
		
		this.buttons = new HashMap<String, Button>();
	}
	
	void addButon(String buttonName, String buttonText) {
		Button button = new Button(buttonText);
		button.setStyle("-fx-font-size:16");
		FlowPane.setMargin(button, new Insets(0, 8, 8, 0));
		this.buttonContainer.getChildren().add(button);
		
		this.buttons.put(buttonName, button);
	}
	
	void removeButton(String buttonName) {
		if (this.buttons.containsKey(buttonName)) {
			this.buttons.remove(buttonName);
		}
	}
	
	Button getButton(String buttonName) {
		if (this.buttons.containsKey(buttonName)) {
			return this.buttons.get(buttonName);
		}
		
		return null;
	}
}
