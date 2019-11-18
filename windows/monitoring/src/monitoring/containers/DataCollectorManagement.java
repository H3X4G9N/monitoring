package monitoring.containers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class DataCollectorManagement extends Container {
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
			Image logoImage = new Image(getClass().getResource("/res/img/logo.png").toExternalForm());
			Image dashboardImage = new Image(getClass().getResource("/res/img/dashboard.png").toExternalForm());
			Image dataCollectorsImage = new Image(getClass().getResource("/res/img/data-collectors.png").toExternalForm());
			Image statisticsImage = new Image(getClass().getResource("/res/img/statistics.png").toExternalForm());
			Image browseImage = new Image(getClass().getResource("/res/img/search.png").toExternalForm());

 
			GridPane topBar = new GridPane();
			topBar.setStyle("-fx-background-color:#f0f0f0");
			topBar.setPadding(new Insets(8, 8, 8, 8));
			topBar.getColumnConstraints().addAll(column2Left, column2Right);
			this.getRoot().getChildren().add(topBar);

			SplitPane section = new SplitPane();
			section.setStyle("-fx-background-color:#e3e3e3");
			section.setPadding(new Insets(8, 8, 8, 8));
			this.getRoot().getChildren().add(section);
			VBox.setVgrow(section, Priority.ALWAYS);

			VBox sideBar = new VBox();
			sideBar.setMaxWidth(200);
			sideBar.setPadding(new Insets(0, 8, 0, 0));
			section.getItems().add(sideBar);

	
			HBox.setHgrow(sectionContainer, Priority.ALWAYS);
			HBox.setMargin(sectionContainer, new Insets(0, 0, 0, 8));
			sectionContainer.setStyle("-fx-background-color:#f0f0f0");
			section.getItems().add(sectionContainer);

			ImageView logoIcon = new ImageView();
			logoIcon.setImage(logoImage);
			logoIcon.setFitWidth(32);
			logoIcon.setFitHeight(32);

			Label appName = new Label("Monitoring");
			appName.setGraphic(logoIcon);
			topBar.add(appName, 0, 0);

			

			

			Button dashboardButton = new Button("Dashboard");
			dashboardButton.setAlignment(Pos.BASELINE_LEFT);
			dashboardButton.setMaxWidth(Double.MAX_VALUE);
			
			dashboardButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					sectionContainer.setContent(getContainerController().getContainer("dashboard").getRoot());
				}
				});
			
			sideBar.getChildren().add(dashboardButton);

			ImageView panelIcon = new ImageView();
			panelIcon.setImage(dashboardImage);
			panelIcon.setFitWidth(32);
			panelIcon.setFitHeight(32);
			dashboardButton.setMaxWidth(Double.MAX_VALUE);
			dashboardButton.setGraphic(panelIcon);



			Button dataCollectorsButton = new Button("Data collectors");
			dataCollectorsButton.setAlignment(Pos.BASELINE_LEFT);
			dataCollectorsButton.setMaxWidth(Double.MAX_VALUE);
			
			dataCollectorsButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					sectionContainer.setContent(getContainerController().getContainer("data-collectors").getRoot());
				}
				});
			
			sideBar.getChildren().add(dataCollectorsButton);

			ImageView dataCollectorsIcon = new ImageView();
			dataCollectorsIcon.setImage(dataCollectorsImage);
			dataCollectorsIcon.setFitWidth(32);
			dataCollectorsIcon.setFitHeight(32);
			dataCollectorsButton.setGraphic(dataCollectorsIcon);
			
			Button statisticsButton = new Button("Statistics");
			statisticsButton.setAlignment(Pos.BASELINE_LEFT);
			statisticsButton.setMaxWidth(Double.MAX_VALUE);
			
			statisticsButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					sectionContainer.setContent(getContainerController().getContainer("statistics").getRoot());
				}
				});
			
			sideBar.getChildren().add(statisticsButton);

			ImageView statisticsIcon = new ImageView();
			statisticsIcon.setImage(statisticsImage);
			statisticsIcon.setFitWidth(32);
			statisticsIcon.setFitHeight(32);
			statisticsButton.setGraphic(statisticsIcon);

			Button browseButton = new Button("Browse data collectors");
			browseButton.setAlignment(Pos.BASELINE_LEFT);
			browseButton.setMaxWidth(Double.MAX_VALUE);
			
			browseButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					sectionContainer.setContent(getContainerController().getContainer("data-collector-browsing").getRoot());
				}
				});
			
			sideBar.getChildren().add(browseButton);

			ImageView browseIcon = new ImageView();
			browseIcon.setImage(browseImage);
			browseIcon.setFitWidth(32);
			browseIcon.setFitHeight(32);
			browseButton.setGraphic(browseIcon);


			sectionContainer.setPadding(new Insets(8, 8, 8, 8));

			sectionContainer.setFitToWidth(true);
	}
}
