package monitoring;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import monitoring.containers.Container;

public class ContainerController {
	private Scene scene;
	private Map <String, Container> containers;
	
	public ContainerController(Scene scene) {
		this.scene = scene;
		this.containers = new HashMap<String, Container>();
	}
	
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
	public void addContainer(String containerName, Container container) {
		this.containers.put(containerName, container);
		container.setContainerController(this);
	}
	
	public void removeContainer(String containerName) {
		if (this.containers.containsKey(containerName)) {
			this.containers.remove(containerName);
		}
	}
	
	public void setContainer(String containerName) {
		if (this.containers.containsKey(containerName)) {
			this.scene.setRoot(this.containers.get(containerName).getRoot());
		}
	}
	
	public Container getContainer(String containerName) {
		if (this.containers.containsKey(containerName)) {
			return this.containers.get(containerName);
		}
		
		return null;
	}
}
