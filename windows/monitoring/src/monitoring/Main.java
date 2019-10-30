package monitoring;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.Hyperlink;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
	
	@Override
	public void start(Stage stage) throws Exception {
		VBox signInVB = new VBox();
		Scene signInS = new Scene(signInVB);
		
		VBox signUpVB = new VBox();
		VBox resetPasswordVB = new VBox();
		
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
			signInVB.getChildren().add(signInB);
			
			Separator separator1 = new Separator();
			signInVB.getChildren().add(separator1);
			
			Hyperlink signUpHL = new Hyperlink("Sign up");
			
			signUpHL.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	signInS.setRoot(signUpVB);
                }
            });
			
			signInVB.getChildren().add(signUpHL);
			
			Hyperlink resetPasswordUpHL = new Hyperlink("Reset password");
			
			resetPasswordUpHL.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	
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
			
			Button signInB = new Button("Sign up");
			signUpVB.getChildren().add(signInB);
			
			Separator separator1 = new Separator();
			signUpVB.getChildren().add(separator1);
			
			Hyperlink signUpHL = new Hyperlink("Sign in");
			
			signUpHL.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	signInS.setRoot(signInVB);
                }
            });
			
			signUpVB.getChildren().add(signUpHL);
			
			Hyperlink resetPasswordUpHL = new Hyperlink("Reset password");
			
			 resetPasswordUpHL.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	
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
		}
		
		
		stage.setTitle("Monitoring");
		stage.setScene(signInS);
		stage.show();
	}
}
