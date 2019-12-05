package com.datacollection.client;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import com.datacollection.client.components.Component;

public class Controller {
	private Scene scene;
	private Map <String, Component> elements;
	
	public Controller(Scene scene) {
		this.scene = scene;
		this.elements = new HashMap<String, Component>();
	}
	
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
	public void addElement(String elementName, Component element) {
		this.elements.put(elementName, element);
		element.setController(this);
	}
	
	public void removeElement(String elementName) {
		if (this.elements.containsKey(elementName)) {
			this.elements.remove(elementName);
		}
	}
	
	public void setElement(String elementName) {
		if (this.elements.containsKey(elementName)) {
			this.scene.setRoot(this.elements.get(elementName).getContainer());
		}
	}
	
	public Component getElement(String elementName) {
		if (this.elements.containsKey(elementName)) {
			return this.elements.get(elementName);
		}
		
		return null;
	}
}
