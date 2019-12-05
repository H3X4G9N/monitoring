package com.datacollection.client.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DataCollectorBrowser extends Component {
	private TextField searchInput;
	private ScrollPane searchResultContainer;
	
    private class SearchEventHandler implements EventHandler<ActionEvent> {
    	@Override
        public void handle(ActionEvent event) {
    		
    	}
    }
	
	public DataCollectorBrowser() {
		HBox appTopBar = new HBox();
		this.getContainer().getChildren().add(appTopBar);
		
		Icon backIcon = new Icon("/images/back.png");
		Icon searchIcon = new Icon("/images/search.png");
		
		Button backButton = new Button();
		backButton.setGraphic(backIcon.imageView);
		backButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		appTopBar.getChildren().add(backButton);
		
		Text browseDataCollectorsHeading = new Text("Browse Data Collectors");
		browseDataCollectorsHeading.setStyle("-fx-font-size:24");
		appTopBar.getChildren().add(browseDataCollectorsHeading);
		
		HBox searchBar = new HBox();
		this.getContainer().getChildren().add(searchBar);
		
		this.searchInput = new TextField();
		this.searchInput.setStyle("-fx-font-size:16");
		searchBar.getChildren().add(searchInput);
		
		Button searchButton = new Button();
		searchButton.setGraphic(searchIcon.imageView);
		searchButton.setOnAction(new SearchEventHandler());
		searchBar.getChildren().add(searchButton);
		
		VBox filtersContainer = new VBox();
		this.getContainer().getChildren().add(filtersContainer);
		
		this.searchResultContainer = new ScrollPane();
		this.getContainer().getChildren().add(this.searchResultContainer);
	}
}
