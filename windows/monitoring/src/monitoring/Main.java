package monitoring;
import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import monitoring.containers.*;
import monitoring.ContainerController;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Scene scene = new Scene(new VBox());
		
		ContainerController containerController = new ContainerController(scene);
		containerController.addContainer("start", new Start());
		containerController.addContainer("data-collector-browsing", new DataCollectorBrowsing());
		containerController.addContainer("signing-in", new SigningIn(IDType.Email));
		containerController.addContainer("signing-up", new SigningUp());
		containerController.addContainer("resetting-password", new ResettingPassword());
		containerController.addContainer("data-collector-management", new DataCollectorManagement());
		containerController.addContainer("porfile", new Profile());
		containerController.addContainer("settings", new Settings());
		containerController.addContainer("dashboard", new Dashboard());
		containerController.addContainer("data-collectors", new DataCollectors());
		containerController.addContainer("statistics", new Statistics());
		containerController.addContainer("data-collector", new DataCollector());
		containerController.addContainer("data-collector-collection", new DataCollectorCollection());
		containerController.setContainer("start");
		
		stage.setMaximized(true);
		stage.setTitle("Monitoring");
		stage.setScene(scene);
		stage.show();
	}
}
