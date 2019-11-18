package monitoring.containers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class DataCollectorBrowsing extends Container {
	private TextField searchInput;
	private ScrollPane searchResultContainer;
	
    private class SearchEventHandler implements EventHandler<ActionEvent> {
    	@Override
        public void handle(ActionEvent event) {
    		
    	}
    }
	
	public DataCollectorBrowsing() {
		HBox appTopBar = new HBox();
		this.getRoot().getChildren().add(appTopBar);
		
		Image backImage = new Image(getClass().getResource("/res/img/back.png").toExternalForm());
		Image searchImage = new Image(getClass().getResource("/res/img/search.png").toExternalForm());
		
		ImageView backIcon = new ImageView();
		backIcon.setFitWidth(16);
		backIcon.setFitHeight(16);
		backIcon.setImage(backImage);
		
		ImageView searchIcon = new ImageView();
		searchIcon.setFitWidth(16);
		searchIcon.setFitHeight(16);
		searchIcon.setImage(searchImage);
		
		Button backButton = new Button();
		backButton.setGraphic(backIcon);
		backButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getContainerController().setContainer("start");
			}
		});
		appTopBar.getChildren().add(backButton);
		
		Text browseDataCollectorsHeading = new Text("Browse Data Collectors");
		appTopBar.getChildren().add(browseDataCollectorsHeading);
		
		HBox searchBar = new HBox();
		this.getRoot().getChildren().add(searchBar);
		
		TextField searchInput = new TextField();
		searchBar.getChildren().add(searchInput);
		
		Button searchButton = new Button();
		searchButton.setGraphic(searchIcon);
		searchButton.setOnAction(new SearchEventHandler());
		searchBar.getChildren().add(searchButton);
		
		VBox filtersContainer = new VBox();
		this.getRoot().getChildren().add(filtersContainer);
		
		this.searchResultContainer = new ScrollPane();
		this.getRoot().getChildren().add(this.searchResultContainer);
	}
}
