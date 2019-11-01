package monitoring;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.Axis;
import javafx.scene.chart.NumberAxis;
import monitoring.Card;
import monitoring.EditableText;

public class Card {
	public
		VBox container;
		Label title;
		Label text;
	
	public Card(String title, String text) {
		container = new VBox();
		
		Label titleLabel = new Label(title);
		container.getChildren().add(titleLabel);
		
		Label textLabel = new Label(text);
		textLabel.setStyle("-fx-font-size:24");
		container.getChildren().add(textLabel);
	}
}
