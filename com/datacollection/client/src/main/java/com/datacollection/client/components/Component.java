package com.datacollection.client.components;

import javafx.scene.layout.VBox;
import com.datacollection.client.Controller;

public class Component {
	private Controller controller;
	private VBox container;
	
	public Component() {
		this.container = new VBox();
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public Controller getController() {
		return this.controller;
	}
	
	public VBox getContainer() {
		return this.container;
	}
}
