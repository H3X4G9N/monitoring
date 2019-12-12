package com.datalogging.client.components;

import com.datalogging.client.Controller;
import com.datalogging.client.model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AuthorizationComponent extends Component {
    private AuthorizationMode authorizationMode;
    private TextField usernameInput;
    private Text usernameErrorMessage;
    private TextField emailInput;
    private Text emailErrorMessage;
    private PasswordField passwordInput;
    private Text passwordErrorMessage;
    private Button submitButton;
    private Button switchModeButton;

    public AuthorizationComponent(Controller controller, AuthorizationMode authorizationMode) {
        super(controller);
        VBox authorizationContainer = new VBox();

        usernameInput = new TextField();
        usernameInput.setPromptText("Username");

        usernameErrorMessage = new Text();

        emailInput = new TextField();
        emailInput.setPromptText("Email");

        emailErrorMessage = new Text();

        passwordInput = new PasswordField();
        passwordInput.setPromptText("Password");

        passwordErrorMessage = new Text();

        submitButton = new Button();

        switchModeButton = new Button();
        switchModeButton.setOnAction(new SwitchModeHandler());

        authorizationContainer.getChildren().add(usernameInput);
        authorizationContainer.getChildren().add(emailInput);
        authorizationContainer.getChildren().add(passwordInput);
        authorizationContainer.getChildren().add(submitButton);
        authorizationContainer.getChildren().add(switchModeButton);
        getContainer().getChildren().add(authorizationContainer);

        setAuthorizationMode(authorizationMode);
    }

    public AuthorizationMode getAuthorizationMode() {
        return authorizationMode;
    }

    public void setAuthorizationMode(AuthorizationMode authorizationMode) {
        this.authorizationMode = authorizationMode;

        if (authorizationMode == AuthorizationMode.Authorization) {
            emailInput.setManaged(false);
            submitButton.setText("Sign In");
            submitButton.setOnAction(new AuthorizeHandler());
            switchModeButton.setText("Sign Up");
        } else {
            emailInput.setManaged(true);
            submitButton.setText("Sign Up");
            submitButton.setOnAction(new RegisterHandler());
            switchModeButton.setText("Sign In");
        }
    }

    private Boolean validateUsername(String username) {
        Boolean isValid = true;
        String errorMessage = "";

        if (isValid) {
            return true;
        }

        usernameErrorMessage.setText(errorMessage);
        return false;
    }

    private Boolean validateEmail(String email) {
        Boolean isValid = true;
        String errorMessage = "";

        if (isValid) {
            return true;
        }

        emailErrorMessage.setText(errorMessage);
        return false;
    }

    private Boolean validatePassword(String password) {
        Boolean isValid = true;
        String errorMessage = "";

        if (isValid) {
            return true;
        }

        passwordErrorMessage.setText(errorMessage);
        return false;
    }

    private class RegisterHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String username = usernameInput.getText();
            String email = emailInput.getText();
            String password = passwordInput.getText();

            if (validateUsername(username) && validateEmail(email) && validatePassword(password)) {
                String response = User.register(usernameInput.getText(), emailInput.getText(), passwordInput.getText());

                if (response != null) {
                    setAuthorizationMode(AuthorizationMode.Authorization);
                }
            }
        }
    }

    private class AuthorizeHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            System.out.println(getController());
            String username = usernameInput.getText();
            String password = passwordInput.getText();

            if (validateUsername(username) && validatePassword(password)) {
                User user = User.authorize(usernameInput.getText(), passwordInput.getText());

                if (user != null) {
                    String response = User.authenticate(user);

                    if (response != null) {
                        getController().setUser(user);
                        getController().setRoot("management");
                    }
                }
            }
        }
    }

    private class SwitchModeHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (authorizationMode == AuthorizationMode.Authorization) {
                setAuthorizationMode(AuthorizationMode.Registration);
            } else {
                setAuthorizationMode(AuthorizationMode.Authorization);
            }
        }
    }
}
