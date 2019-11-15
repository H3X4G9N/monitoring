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
import javafx.scene.chart.XYChart;
import monitoring.Card;
import monitoring.EditableText;
import javafx.scene.control.DatePicker;


public class Main extends Application {	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		VBox signInRoot = new VBox();
		signInRoot.setAlignment(Pos.CENTER);

		VBox signUpRoot = new VBox();
		signUpRoot.setAlignment(Pos.CENTER);

		VBox resetPasswordRoot = new VBox();
		resetPasswordRoot.setAlignment(Pos.CENTER);

		VBox panelRoot = new VBox();

		Scene scene = new Scene(signInRoot);
		VBox dcsContainer;
		
		//////////////////////////////////////////////////////LOGIN FORM
		{
			VBox container = new VBox();
			container.setMinWidth(320);
			container.setMinHeight(180);
			container.setPrefWidth(320);
			container.setPrefHeight(180);
			container.requestFocus();
			signInRoot.getChildren().add(new Group(container));

			
			TextField username = new TextField();
			username.setPromptText("Username");
			VBox.setMargin(username, new Insets(0, 0, 16, 0));
			container.getChildren().add(username);


			PasswordField passwordInput = new PasswordField();
			passwordInput.setPromptText("password");
			VBox.setMargin(passwordInput, new Insets(0, 0, 16, 0));
			container.getChildren().add(passwordInput);

			VBox container1 = new VBox();
			Button signInButton = new Button("Sign in");
			signInButton.setPrefWidth(320);
			signInButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(username.getText().equals("root") && passwordInput.getText().equals("root"))
					scene.setRoot(panelRoot);
			}
			});
			container1.setAlignment(Pos.CENTER);
			container1.getChildren().add(signInButton);
			container.getChildren().add(container1);
			signInRoot.requestFocus();
		}
		////////////////////////////////////////////////////////////////
		{
			VBox container = new VBox();
			container.setMinWidth(320);
			container.setMinHeight(180);
			container.setPrefWidth(320);
			container.setPrefHeight(180);
			signUpRoot.getChildren().add(new Group(container));

			Label emailLabel = new Label("Email");
			container.getChildren().add(emailLabel);

			TextField emailInput = new TextField();
			VBox.setMargin(emailInput, new Insets(0, 0, 16, 0));
			container.getChildren().add(emailInput);

			Label passwordLabel = new Label("Password");
			container.getChildren().add(passwordLabel);

			PasswordField passwordInput = new PasswordField();
			VBox.setMargin(passwordInput, new Insets(0, 0, 16, 0));
			container.getChildren().add(passwordInput);

			Label passwordConfirmationLabel = new Label("Password confirmation");
			container.getChildren().add(passwordConfirmationLabel);

			PasswordField passwordConfirmationInput = new PasswordField();
			VBox.setMargin(passwordConfirmationInput, new Insets(0, 0, 16, 0));
			container.getChildren().add(passwordConfirmationInput);

			Button signUpButton = new Button("Sign up");
			signUpButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				scene.setRoot(panelRoot);
			}
			});
			container.getChildren().add(signUpButton);

			Separator separator = new Separator();
			separator.setMinHeight(32);
			container.getChildren().add(separator);

			Hyperlink signUpLink = new Hyperlink("Sign in");
			signUpLink.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				scene.setRoot(signInRoot);
			}
			});
			container.getChildren().add(signUpLink);

			Hyperlink resetPasswordLink = new Hyperlink("Reset password");
			resetPasswordLink.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				scene.setRoot(resetPasswordRoot);
			}
			});
			container.getChildren().add(resetPasswordLink);
		}

		{
			VBox container = new VBox();
			container.setMinWidth(320);
			container.setMinHeight(180);
			container.setPrefWidth(320);
			container.setPrefHeight(180);
			resetPasswordRoot.getChildren().add(new Group(container));

			Label emailLabel = new Label("Email");
			container.getChildren().add(emailLabel);

			TextField emailInput = new TextField();
			VBox.setMargin(emailInput, new Insets(0, 0, 16, 0));
			container.getChildren().add(emailInput);

			Button resetPasswordButton = new Button("Reset password");
			container.getChildren().add(resetPasswordButton);

			Separator separator = new Separator();
			separator.setMinHeight(32);
			container.getChildren().add(separator);

			Hyperlink signInLink = new Hyperlink("Sign in");
			signInLink.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				scene.setRoot(signInRoot);
			}
			});
			container.getChildren().add(signInLink);

			Hyperlink signUpLink = new Hyperlink("Sign up");
			signUpLink.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				scene.setRoot(signUpRoot);
			}
			});
			container.getChildren().add(signUpLink);
		}

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
		GridPane test = new GridPane();
		VBox dataCollectorContainer;
		
		VBox dashboardContainer = new VBox();
		
		{
			dataCollectorContainer = new VBox();
			sectionContainer.setContent(dataCollectorContainer);
			
		
			EditableText name = new EditableText("Name", TextType.Field);
			VBox.setMargin(name.container, new Insets(0, 0, 8, 0));
			dataCollectorContainer.getChildren().add(name.container);
		
			EditableText description = new EditableText("Description", TextType.Area);
			VBox.setMargin(description.container, new Insets(0, 0, 8, 0));
			dataCollectorContainer.getChildren().add(description.container);
				
			GridPane sensorContainer = new GridPane();
			sensorContainer.getColumnConstraints().addAll(column3Center, column3Center, column3Center);
		}
		
		
		{
			dcsContainer = new VBox();
			
			HBox dcContainer = new HBox();
			
			Label dcName = new Label("Data collector 1");
			dcContainer.getChildren().add(dcName);
			
			Label dcTemperature = new Label("Temperature: 0 C");
			dcContainer.getChildren().add(dcTemperature);
			
			Label dcHumidity = new Label("Humidity: 0 %");
			dcContainer.getChildren().add(dcHumidity);
			
			Label dcCarbonDioxide = new Label("Carbon dioxide: 0 PPM");
			dcContainer.getChildren().add(dcCarbonDioxide);
			
			dcsContainer.getChildren().add(dcContainer);
		}
		
		{
			Image dataCollectorsImage = new Image(getClass().getResource("/res/img/data_collectors.png").toExternalForm());
			Image dashboardImage = new Image(getClass().getResource("/res/img/dashboard.png").toExternalForm());
			Image logoImage = new Image(getClass().getResource("/res/img/logo.png").toExternalForm());
			//Image serverImage = new Image(getClass().getResource("/res/img/server.png").toExternalForm());
			Image settingsImage = new Image(getClass().getResource("/res/img/settings.png").toExternalForm());

			GridPane topBar = new GridPane();
			topBar.setStyle("-fx-background-color:#f0f0f0");
			topBar.setPadding(new Insets(8, 8, 8, 8));
			topBar.getColumnConstraints().addAll(column2Left, column2Right);
			panelRoot.getChildren().add(topBar);

			SplitPane section = new SplitPane();
			section.setStyle("-fx-background-color:#e3e3e3");
			section.setPadding(new Insets(8, 8, 8, 8));
			panelRoot.getChildren().add(section);
			VBox.setVgrow(section, Priority.ALWAYS);

			VBox sideBar = new VBox();
			sideBar.setMaxWidth(160);
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
					sectionContainer.setContent(dashboardContainer);
				}
				});
			
			sideBar.getChildren().add(dashboardButton);

			ImageView panelIcon = new ImageView();
			panelIcon.setImage(dashboardImage);
			panelIcon.setFitWidth(32);
			panelIcon.setFitHeight(32);
			dashboardButton.setMaxWidth(Double.MAX_VALUE);
			dashboardButton.setGraphic(panelIcon);

			//Button serverButton = new Button("Server");
			//serverButton.setAlignment(Pos.BASELINE_LEFT);
			//serverButton.setMaxWidth(Double.MAX_VALUE);
			//sideBar.getChildren().add(serverButton);

			//ImageView serverIcon = new ImageView();
			//serverIcon.setImage(serverImage);
			//serverIcon.setFitWidth(32);
			//serverIcon.setFitHeight(32);
			//serverButton.setGraphic(serverIcon);

			Button dataCollectorsButton = new Button("Data collectors");
			dataCollectorsButton.setAlignment(Pos.BASELINE_LEFT);
			dataCollectorsButton.setMaxWidth(Double.MAX_VALUE);
			
			dataCollectorsButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					sectionContainer.setContent(dcsContainer);
				}
				});
			
			sideBar.getChildren().add(dataCollectorsButton);

			ImageView dataCollectorsIcon = new ImageView();
			dataCollectorsIcon.setImage(dataCollectorsImage);
			dataCollectorsIcon.setFitWidth(32);
			dataCollectorsIcon.setFitHeight(32);
			dataCollectorsButton.setGraphic(dataCollectorsIcon);

			Button settingsButton = new Button("Settings");
			settingsButton.setAlignment(Pos.BASELINE_LEFT);
			settingsButton.setMaxWidth(Double.MAX_VALUE);
			sideBar.getChildren().add(settingsButton);

			ImageView settingsIcon = new ImageView();
			settingsIcon.setImage(settingsImage);
			settingsIcon.setFitWidth(32);
			settingsIcon.setFitHeight(32);
			settingsButton.setGraphic(settingsIcon);


			sectionContainer.setPadding(new Insets(8, 8, 8, 8));

			sectionContainer.setFitToWidth(true);
		}
		
		{	
			Label dashboardHeading = new Label("Dasboard");
			GridPane.setMargin(dashboardHeading, new Insets(0, 0, 8, 0));
			dashboardHeading.setStyle("-fx-font-size:32");
			dashboardContainer.getChildren().add(dashboardHeading);
			
			GridPane sensorDataContainer = new GridPane();
			sensorDataContainer.getColumnConstraints().add(column3Left);
			sensorDataContainer.getColumnConstraints().add(column3Left);
			sensorDataContainer.getColumnConstraints().add(column3Left);
			sensorDataContainer.setPadding(new Insets(16, 16, 16, 16));
			sensorDataContainer.setStyle("-fx-background-color:#e3e3e3");
			dashboardContainer.getChildren().add(sensorDataContainer);

			Card temperatureCard = new Card("Average temperature", "69 C");
			Card humidityCard = new Card("Humidity temperature", "69 %");
			Card carbonDioxideCard = new Card("Carbon dioxide", "69 PPM");
			
			sensorDataContainer.add(temperatureCard.container, 0, 0);
			sensorDataContainer.add(humidityCard.container, 1, 0);
			sensorDataContainer.add(carbonDioxideCard.container, 2, 0);
			
			
		      NumberAxis xAxis = new NumberAxis(1960, 2020, 10); 
		      xAxis.setLabel("Years"); 
		        
		      //Defining the y axis   
		      NumberAxis yAxis = new NumberAxis   (0, 350, 50); 
		      yAxis.setLabel("No.of schools"); 
		        
		      //Creating the line chart 
		      LineChart linechart = new LineChart(xAxis, yAxis);  
		        
		      //Prepare XYChart.Series objects by setting data 
		      XYChart.Series series = new XYChart.Series(); 

		      series.getData().add(new XYChart.Data(1970, 15)); 
		      series.getData().add(new XYChart.Data(1980, 30)); 
		      series.getData().add(new XYChart.Data(1990, 60)); 
		      series.getData().add(new XYChart.Data(2000, 120)); 
		      series.getData().add(new XYChart.Data(2013, 240)); 
		      series.getData().add(new XYChart.Data(2014, 300)); 
		            
		      //Setting the data to Line chart    
		      linechart.getData().add(series);    
		      
		      XYChart.Series series2 = new XYChart.Series(); 
		      series.setName("Temperature"); 
		        
		      series2.getData().add(new XYChart.Data(1970, 20)); 
		      series2.getData().add(new XYChart.Data(1980, 70)); 
		      series2.getData().add(new XYChart.Data(1990, 30)); 
		      series2.getData().add(new XYChart.Data(2000, 70)); 
		      series2.getData().add(new XYChart.Data(2013, 300)); 
		      series2.getData().add(new XYChart.Data(2014, 260));
		      series2.setName("Humidity");
		      linechart.getData().add(series2);
		      


			dashboardContainer.getChildren().add(linechart);
		      DatePicker date = new DatePicker();
		      
		      dashboardContainer.getChildren().add(date);
			
			sectionContainer.setContent(dashboardContainer);
		}
		
		sectionContainer.setContent(dashboardContainer);
		
		{
			//Temperature, Humidity, Carbon Dioxide
			//Interval in minutes
			//Minutes, Hours, Days, Weeks, Months, Years
		}
		
		stage.setTitle("Monitoring");
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.show();
	}
}
