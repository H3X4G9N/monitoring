package com.datacollection.client.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class EditableTextField extends Component {
	public
	VBox container;
	Label nameLabel;
	Button editButton;
	TextType type;
	
private
	TextField textField;
	TextArea textArea;
	
private class Handle implements EventHandler<ActionEvent>
{
    @Override
    public void handle(ActionEvent event)
    {

			textField.setEditable(true);
			textField.requestFocus();

    }
}

public EditableTextField(String name, TextType type) {
	this.type = type;
	
	this.container = new VBox();
	
	ColumnConstraints column2Left = new ColumnConstraints();
	column2Left.setPercentWidth(1.0 / 2.0 * 100.0);

	ColumnConstraints column2Right = new ColumnConstraints();
	column2Right.setPercentWidth(1.0 / 2.0 * 100.0);
	column2Right.setHalignment(HPos.RIGHT);
	
	GridPane panel = new GridPane();
	panel.getColumnConstraints().addAll(column2Left, column2Right);
	VBox.setMargin(panel, new Insets(0, 0, 4, 0));
	this.container.getChildren().add(panel);
	
	
	this.nameLabel = new Label(name);
	panel.add(this.nameLabel, 0, 0);		
	
	Image editImage = new Image(getClass().getResource("/res/img/edit.png").toExternalForm());

	ImageView editIcon = new ImageView(editImage);
	editIcon.setFitWidth(16);
	editIcon.setFitHeight(16);
	panel.getChildren().add(editIcon);
	
	this.editButton = new Button();
	Handle hand = new Handle();
	editButton.setOnAction(hand);
	editButton.setGraphic(editIcon);
	
	this.textField = new TextField();
	this.textArea = new TextArea();
	
	
	panel.add(editButton, 1, 0);
	
       textField.focusedProperty().addListener((ov, oldV, newV) -> {
           if (!newV) {
        	   textField.setEditable(false);
           }
        });
       textArea.focusedProperty().addListener((ov, oldV, newV) -> {
           if (!newV) {
        	   textArea.setEditable(false);
           }
        });
	

		this.textField.setEditable(false);
		this.container.getChildren().add(this.textField);
		

}

public String getText() {

		return this.textField.getText();

}
}