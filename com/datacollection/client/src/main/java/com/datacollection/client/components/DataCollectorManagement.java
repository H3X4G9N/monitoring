package com.datacollection.client.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class DataCollectorManagement extends Component {
	public DataCollectorManagement() {
		ColumnConstraints column1Left = new ColumnConstraints();
		column1Left.setPercentWidth(1.0 / 1.0 * 100.0);

		ColumnConstraints column2Left = new ColumnConstraints();
		column2Left.setPercentWidth(1.0 / 2.0 * 100.0);

		ColumnConstraints column2Right = new ColumnConstraints();
		column2Right.setPercentWidth(1.0 / 2.0 * 100.0);
		column2Right.setHalignment(HPos.RIGHT);

		ColumnConstraints column3Left = new ColumnConstraints();
		column3Left.setPercentWidth(1.0 / 3.0 * 100.0);
		
		ColumnConstraints column3Center = new ColumnConstraints();
		column3Center.setPercentWidth(1.0 / 3.0 * 100.0);
		column3Center.setHalignment(HPos.CENTER);
		
		ScrollPane sectionContainer = new ScrollPane();
 
		GridPane topBar = new GridPane();
		topBar.setStyle("-fx-background-color:#f0f0f0");
		topBar.setPadding(new Insets(8, 8, 8, 8));
		topBar.getColumnConstraints().addAll(column2Left, column2Right);
		this.getContainer().getChildren().add(topBar);
		

		SplitPane section = new SplitPane();
		section.setStyle("-fx-background-color:#e3e3e3");
		section.setPadding(new Insets(8, 8, 8, 8));
		this.getContainer().getChildren().add(section);
		VBox.setVgrow(section, Priority.ALWAYS);

		VBox sideBar = new VBox();
		sideBar.setMaxWidth(200);
		sideBar.setPadding(new Insets(0, 8, 0, 0));
		section.getItems().add(sideBar);


		HBox.setHgrow(sectionContainer, Priority.ALWAYS);
		HBox.setMargin(sectionContainer, new Insets(0, 0, 0, 8));
		sectionContainer.setStyle("-fx-background-color:#f0f0f0");
		section.getItems().add(sectionContainer);

		Icon logoIcon = new Icon("/images/dashboard.png");


		Label appName = new Label("Monitoring");
		appName.setGraphic(logoIcon.imageView);
		topBar.add(appName, 0, 0);

		

		

		Button dashboardButton = new Button("Dashboard");
		dashboardButton.setAlignment(Pos.BASELINE_LEFT);
		dashboardButton.setMaxWidth(Double.MAX_VALUE);
		
		dashboardButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sectionContainer.setContent(getController().getElement("dashboard").getContainer());
			}
			});
		
		sideBar.getChildren().add(dashboardButton);

		Icon panelIcon = new Icon("/images/dashboard.png");
		dashboardButton.setGraphic(panelIcon.imageView);



		Button dataCollectorsButton = new Button("Data collectors");
		dataCollectorsButton.setAlignment(Pos.BASELINE_LEFT);
		dataCollectorsButton.setMaxWidth(Double.MAX_VALUE);
		
		dataCollectorsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sectionContainer.setContent(getController().getElement("data-collector-list").getContainer());
			}
			});
		
		sideBar.getChildren().add(dataCollectorsButton);

		Icon dataCollectorsIcon = new Icon("/images/dashboard.png");
		dataCollectorsButton.setGraphic(dataCollectorsIcon.imageView);
		
		Button statisticsButton = new Button("Statistics");
		statisticsButton.setAlignment(Pos.BASELINE_LEFT);
		statisticsButton.setMaxWidth(Double.MAX_VALUE);
		
		statisticsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sectionContainer.setContent(getController().getElement("statistics").getContainer());
			}
			});
		
		sideBar.getChildren().add(statisticsButton);

		Icon statisticsIcon = new Icon("/images/dashboard.png");
		statisticsButton.setGraphic(statisticsIcon.imageView);

		Button browseButton = new Button("Browse data collectors");
		browseButton.setAlignment(Pos.BASELINE_LEFT);
		browseButton.setMaxWidth(Double.MAX_VALUE);
		
		browseButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sectionContainer.setContent(getController().getElement("data-collector-browsing").getContainer());
			}
			});
		
		sideBar.getChildren().add(browseButton);

		Icon browseIcon = new Icon("/images/dashboard.png");
		browseButton.setGraphic(browseIcon.imageView);


		sectionContainer.setPadding(new Insets(8, 8, 8, 8));

		sectionContainer.setFitToWidth(true);
	}
}
