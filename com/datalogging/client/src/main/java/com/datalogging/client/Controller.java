package com.datalogging.client;

import com.datalogging.client.components.Component;
import com.datalogging.client.model.User;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class Controller {
    private VBox root;
    private Scene scene;
    private Map<String, Component> components;
    private User user;

    public Controller(VBox root, Scene scene) {
        this.root = root;
        this.scene = scene;
        components = new HashMap<>();
        user = new User();
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Component getComponent(String componentName) {
        return components.get(componentName);
    }

    public void addComponent(String componentName, Component component) {
        components.put(componentName, component);
    }

    public void removeComponent(String componentName) {
        components.remove(componentName);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VBox getRoot() {
        return root;
    }

    public void setRoot(String componentName) {
        scene.setRoot(components.get(componentName).getContainer());
    }

    public void setRoot(VBox root) {
        this.root = root;
    }
}
