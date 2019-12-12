package com.datalogging.client.components;

import com.datalogging.client.Controller;
import javafx.scene.layout.VBox;

public class Component {
    private Controller controller;
    private VBox container;

    public Component(Controller controller) {
        this.controller = controller;
        this.container = new VBox();
    }

    public VBox getContainer() {
        return container;
    }

    public void setContainer(VBox container) {
        this.container = container;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
