package monitoring.containers;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import monitoring.components.EditableText;
import javafx.scene.layout.VBox;
import monitoring.components.TextType;


public class DataCollector extends Container {
	public DataCollector() {


		
			ColumnConstraints column3Center = new ColumnConstraints();
			column3Center.setPercentWidth(1.0 / 3.0 * 100.0);
			column3Center.setHalignment(HPos.CENTER);


			

			EditableText name = new EditableText("Name", TextType.Field);
			VBox.setMargin(name.container, new Insets(0, 0, 8, 0));
			this.getRoot().getChildren().add(name.container);

			EditableText description = new EditableText("Description", TextType.Area);
			VBox.setMargin(description.container, new Insets(0, 0, 8, 0));
			this.getRoot().getChildren().add(description.container);
				
			GridPane sensorContainer = new GridPane();
			sensorContainer.getColumnConstraints().addAll(column3Center, column3Center, column3Center);

	}
}
