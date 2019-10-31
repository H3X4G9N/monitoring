package monitoring;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.Hyperlink;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import javafx.scene.layout.GridPane;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
	
	@Override
	public void start(Stage stage) throws FileNotFoundException {
		VBox signInVB = new VBox();
		VBox signUpVB = new VBox();
		VBox resetPasswordVB = new VBox();
		VBox dashboardVB = new VBox();
		
		Scene scene = new Scene(signInVB);
		
		{
			Label emailL = new Label("Email");
			signInVB.getChildren().add(emailL);
			
			TextField emailTF = new TextField();
			signInVB.getChildren().add(emailTF);
			
			Label passwordL = new Label("Password");
			signInVB.getChildren().add(passwordL);
			
			PasswordField passwordPF = new PasswordField();
			signInVB.getChildren().add(passwordPF);
			
			Button signInB = new Button("Sign in");	
			
			signInB.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	scene.setRoot(dashboardVB);
                }
            });
			
			signInVB.getChildren().add(signInB);
			
			Separator separator1 = new Separator();
			signInVB.getChildren().add(separator1);
			
			Hyperlink signUpHL = new Hyperlink("Sign up");
			
			signUpHL.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	scene.setRoot(signUpVB);
                }
            });
			
			signInVB.getChildren().add(signUpHL);
			
			Hyperlink resetPasswordUpHL = new Hyperlink("Reset password");
			
			resetPasswordUpHL.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	scene.setRoot(resetPasswordVB);
                }
            });
			
			signInVB.getChildren().add(resetPasswordUpHL);
		}
		
		{
			Label emailL = new Label("Email");
			signUpVB.getChildren().add(emailL);
			
			TextField emailTF = new TextField();
			signUpVB.getChildren().add(emailTF);
			
			Label passwordL = new Label("Password");
			signUpVB.getChildren().add(passwordL);
			
			PasswordField passwordPF = new PasswordField();
			signUpVB.getChildren().add(passwordPF);
			
			Label passwordConfirmationL = new Label("Password confirmation");
			signUpVB.getChildren().add(passwordConfirmationL);
			
			PasswordField passwordConfirmationPF = new PasswordField();
			signUpVB.getChildren().add(passwordConfirmationPF);
			
			Button signUpB = new Button("Sign up");
			
			signUpB.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	scene.setRoot(dashboardVB);
                }
            });
			
			signUpVB.getChildren().add(signUpB);
			
			Separator separator1 = new Separator();
			signUpVB.getChildren().add(separator1);
			
			Hyperlink signUpHL = new Hyperlink("Sign in");
			
			signUpHL.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	scene.setRoot(signInVB);
                }
            });
			
			signUpVB.getChildren().add(signUpHL);
			
			Hyperlink resetPasswordUpHL = new Hyperlink("Reset password");
			
			resetPasswordUpHL.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	scene.setRoot(resetPasswordVB);
                }
            });
			
			signUpVB.getChildren().add(resetPasswordUpHL);
		}
		
		{
			Label emailL = new Label("Email");
			resetPasswordVB.getChildren().add(emailL);
			
			TextField emailTF = new TextField();
			resetPasswordVB.getChildren().add(emailTF);
			
			Button signInB = new Button("Reset password");
			resetPasswordVB.getChildren().add(signInB);
			
			Separator separator1 = new Separator();
			resetPasswordVB.getChildren().add(separator1);
			
			Hyperlink signInHL = new Hyperlink("Sign in");
			
			signInHL.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	scene.setRoot(signInVB);
                }
            });
			
			resetPasswordVB.getChildren().add(signInHL);
			
			Hyperlink signUpHL = new Hyperlink("Sign up");
			
			signUpHL.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	scene.setRoot(signUpVB);
                }
            });
			
			resetPasswordVB.getChildren().add(signUpHL);
		}
		
		{
			HBox unnamed1 = new HBox();
			dashboardVB.getChildren().add(unnamed1);
			

			Image logoI = new Image(getClass().getResource("/res/img/logo.png").toExternalForm());
			Image dashboardI = new Image(getClass().getResource("/res/img/dashboard.png").toExternalForm());
			Image serverI = new Image(getClass().getResource("/res/img/server.png").toExternalForm());
			Image dataCollectorsI = new Image(getClass().getResource("/res/img/data_collectors.png").toExternalForm());
			Image settingsI = new Image(getClass().getResource("/res/img/settings.png").toExternalForm());

			ImageView logoIV = new ImageView();
			logoIV.setFitWidth(32);
			logoIV.setFitHeight(32);
			logoIV.setImage(logoI);
			
			unnamed1.getChildren().add(logoIV);
			
			Label companyName = new Label("Monitoring");
			unnamed1.getChildren().add(companyName);
			
			HBox unnamed2 = new HBox();
			dashboardVB.getChildren().add(unnamed2);
			
			VBox unnamed3 = new VBox();
			unnamed2.getChildren().add(unnamed3);
			
			Button dashboardB = new Button("Dashboard");
			unnamed3.getChildren().add(dashboardB);
			
			ImageView dashboardIV = new ImageView();
			dashboardIV.setImage(dashboardI);
			dashboardIV.setFitWidth(32);
			dashboardIV.setFitHeight(32);
			dashboardB.setGraphic(dashboardIV);
			
			Button serverB = new Button("Server");
			unnamed3.getChildren().add(serverB);
			
			ImageView serverIV = new ImageView();
			serverIV.setImage(serverI);
			serverIV.setFitWidth(32);
			serverIV.setFitHeight(32);
			serverB.setGraphic(serverIV);
			
			Button dataCollectorsB = new Button("Data collectors");
			unnamed3.getChildren().add(dataCollectorsB);
			
			ImageView dataCollectorsIV = new ImageView();
			dataCollectorsIV.setImage(dataCollectorsI);
			dataCollectorsIV.setFitWidth(32);
			dataCollectorsIV.setFitHeight(32);
			dataCollectorsB.setGraphic(dataCollectorsIV);
			
			Button settingsB = new Button("Settings");
			unnamed3.getChildren().add(settingsB);
			
			ImageView settingsIV = new ImageView();
			settingsIV.setImage(settingsI);
			settingsIV.setFitWidth(32);
			settingsIV.setFitHeight(32);
			settingsB.setGraphic(settingsIV);
		}
		
		stage.setTitle("Monitoring");
		stage.setScene(scene);
		stage.show();
	}
}
