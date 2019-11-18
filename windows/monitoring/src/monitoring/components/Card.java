package monitoring.components;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Card extends Component {
	private Label title;
	private Label text;

	public Card(String title, String text) {
		Label titleLabel = new Label(title);
		this.container.getChildren().add(titleLabel);
	
		Label textLabel = new Label(text);
		textLabel.setStyle("-fx-font-size:24");
		this.container.getChildren().add(textLabel);
	}
}


