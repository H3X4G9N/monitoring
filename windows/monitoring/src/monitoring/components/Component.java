package monitoring.components;

import javafx.scene.layout.VBox;

public class Component {
	protected VBox container;
	
	public Component() {
		this.container = new VBox();
	}
	
	public VBox getContainer() {
		return this.container;
	}
}
