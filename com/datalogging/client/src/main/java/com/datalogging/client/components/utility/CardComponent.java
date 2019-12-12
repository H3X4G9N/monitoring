package com.datalogging.client.components.utility;

import com.datalogging.client.Controller;
import com.datalogging.client.components.Component;
import javafx.geometry.Insets;
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
    private Map<String, Button> buttonMap;

    public CardComponent(Controller controller, String headingText, String paragraphText) {
        super(controller);
        buttonMap = new HashMap<String, Button>();

        heading = new Text(headingText);
        VBox.setMargin(heading, new Insets(0, 0, 12, 0));
        heading.setStyle("-fx-font-size:24");

        paragraph = new Text(paragraphText);
        VBox.setMargin(paragraph, new Insets(0, 0, 8, 0));
        paragraph.setStyle("-fx-font-size:16");

        buttonListContainer = new FlowPane();
        VBox.setMargin(buttonListContainer, new Insets(0, 0, 16, 0));

        this.getContainer().getChildren().add(heading);
        this.getContainer().getChildren().add(paragraph);
        this.getContainer().getChildren().add(buttonListContainer);
    }

    public Button getButton(String buttonName) {
        return this.buttonMap.get(buttonName);
    }

    public void addButon(String buttonName, String buttonText) {
        Button button = new Button();
        button.setText(buttonText);
        FlowPane.setMargin(button, new Insets(0, 8, 8, 0));
        button.setStyle("-fx-font-size:16");
        buttonListContainer.getChildren().add(button);
        buttonMap.put(buttonName, button);
    }

    public void removeButton(String buttonName) {
        this.buttonMap.remove(buttonName);
    }
}
