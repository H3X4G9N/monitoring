package com.datalogging.user.components;

import com.datalogging.user.Controller;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class Component {
    private VBox container;
    private VBox previousContainer;
    private ScrollPane scrollPane;

    public Component() {
        this.container = new VBox();
    }

    public VBox getContainer() {
        return container;
    }

    public void setContainer(VBox container) {
        this.container = container;
    }

    public VBox getPreviousContainer() {
        return previousContainer;
    }

    public void setPreviousContainer(VBox previousContainer) {
        this.previousContainer = previousContainer;
    }

    public void goBackInScene() {
        if (previousContainer != null) {
            Controller.scene.setRoot(previousContainer);
        }
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(ScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public void goBackInScrollPane() {
        if (previousContainer != null) {
            scrollPane.setContent(previousContainer);
        }
    }
}
