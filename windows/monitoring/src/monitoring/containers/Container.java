package monitoring.containers;
import javafx.scene.layout.VBox;
import monitoring.ContainerController;

public class Container {
	private ContainerController containerController;
	private VBox root;
	
	public Container() {
		this.root = new VBox();
	}
	
	public void setContainerController(ContainerController containerController) {
		this.containerController = containerController;
	}
	
	public ContainerController getContainerController() {
		return this.containerController;
	}
	
	public VBox getRoot() {
		return this.root;
	}
}
