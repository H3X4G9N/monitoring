package com.datalogging.user.components.utility;

import com.datalogging.user.components.Component;
import com.datalogging.user.javafx.DLButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

public class CardComponent extends Component {
    public Text heading;
    public Text paragraph;
    public FlowPane buttonListContainer;
    private Map<String, DLButton> buttonMap;

    public CardComponent(String headingText, String paragraphText) {
        buttonMap = new HashMap<>();

        getContainer().setAlignment(Pos.CENTER);

        heading = new Text(headingText);
        VBox.setMargin(heading, new Insets(0, 0, 16, 0));
        heading.setStyle("-fx-font-size:32");

        paragraph = new Text(paragraphText);
        VBox.setMargin(paragraph, new Insets(0, 0, 8, 0));
        paragraph.setStyle("-fx-font-size:16");

        buttonListContainer = new FlowPane();
        VBox.setMargin(buttonListContainer, new Insets(0, 0, 16, 0));
        buttonListContainer.setAlignment(Pos.CENTER);

        this.getContainer().getChildren().add(heading);
        this.getContainer().getChildren().add(paragraph);
        this.getContainer().getChildren().add(buttonListContainer);
    }

    public Button getButton(String buttonName) {
        return this.buttonMap.get(buttonName);
    }

    public void addButton(String buttonName, DLButton button) {
        buttonListContainer.getChildren().add(button);
        buttonMap.put(buttonName, button);
    }

    public void removeButton(String buttonName) {
        buttonListContainer.getChildren().remove(buttonMap.get(buttonName));
        this.buttonMap.remove(buttonName);
    }
}
