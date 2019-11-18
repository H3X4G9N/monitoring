package monitoring.containers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Start extends Container {
	public Start() {
		Text monitoringHeading = new Text("Monitoring");
		this.getRoot().getChildren().add(monitoringHeading);
		
		HBox buttonContainer = new HBox();
		this.getRoot().getChildren().add(buttonContainer);
		
		Button browseDataCollectorsButon = new Button("Browse Data Collectors");
		browseDataCollectorsButon.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getContainerController().setContainer("data-collector-browsing");
			}
		});
		buttonContainer.getChildren().add(browseDataCollectorsButon);
		
		Button manageDataCollectorsButon = new Button("Manage Data Collectors");
		manageDataCollectorsButon.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getContainerController().setContainer("signing-in");
			}
		});
		buttonContainer.getChildren().add(manageDataCollectorsButon);
	}
}
