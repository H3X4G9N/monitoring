package monitoring.containers;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class DataCollectors extends Container {
	public DataCollectors() {
		
		

		
		HBox dcContainer = new HBox();
		
		Label dcName = new Label("Data collector 1");
		dcContainer.getChildren().add(dcName);
		
		Label dcTemperature = new Label("Temperature: 0 C");
		dcContainer.getChildren().add(dcTemperature);
		
		Label dcHumidity = new Label("Humidity: 0 %");
		dcContainer.getChildren().add(dcHumidity);
		
		Label dcCarbonDioxide = new Label("Carbon dioxide: 0 PPM");
		dcContainer.getChildren().add(dcCarbonDioxide);
		
		this.getRoot().getChildren().add(dcContainer);
	}
}
