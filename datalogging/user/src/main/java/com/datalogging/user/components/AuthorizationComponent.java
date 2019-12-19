package com.datalogging.user.components;

import com.datalogging.user.Controller;
import com.datalogging.user.components.utility.TopBarComponent;
import com.datalogging.user.javafx.*;
import com.datalogging.user.model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

public class AuthorizationComponent extends Component {
    public DLText heading;
    public DLTextField usernameInput;
    public DLText usernameErrorMessage;
    public DLTextField emailInput;
    public DLText emailErrorMessage;
    public DLPasswordField passwordInput;
    public DLText passwordErrorMessage;
    public DLButton submitButton;
    public DLButton switchModeButton;
    private AuthorizationMode authorizationMode;

    public AuthorizationComponent() {
        TopBarComponent topBarComponent = new TopBarComponent(this, true, "Authorization", TopBarComponent.GoBackType.Scene);
        topBarComponent.mainContainer.setAlignment(Pos.CENTER);

        VBox authorizationContainer = new VBox();
        authorizationContainer.setMaxWidth(640);
        authorizationContainer.setAlignment(Pos.CENTER);

        heading = new DLText(new DLBuilder().setFontSize(32).setMargin(new Insets(0, 0, 16, 0)));

        usernameInput = new DLTextField(new DLBuilder().setPromptText("Username"));

        usernameErrorMessage = new DLText(new DLBuilder().setFontSize(12));
        usernameErrorMessage.setManaged(false);

        emailInput = new DLTextField(new DLBuilder().setPromptText("Email (Optional)"));

        emailErrorMessage = new DLText(new DLBuilder().setFontSize(12));
        emailErrorMessage.setManaged(false);

        passwordInput = new DLPasswordField(new DLBuilder().setPromptText("Password"));

        passwordErrorMessage = new DLText(new DLBuilder().setFontSize(12));
        passwordErrorMessage.setManaged(false);

        submitButton = new DLButton(new DLBuilder());
        submitButton.setMaxWidth(Double.MAX_VALUE);

        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);
        VBox.setMargin(separator, new Insets(8, 0, 16, 0));

        switchModeButton = new DLButton(new DLBuilder().setActionEventEventHandler(new SwitchModeHandler()));
        switchModeButton.setMaxWidth(Double.MAX_VALUE);

        getContainer().getChildren().add(topBarComponent.getContainer());
        topBarComponent.mainContainer.getChildren().add(authorizationContainer);
        authorizationContainer.getChildren().add(heading);
        authorizationContainer.getChildren().add(usernameInput);
        authorizationContainer.getChildren().add(usernameErrorMessage);
        authorizationContainer.getChildren().add(emailInput);
        authorizationContainer.getChildren().add(emailErrorMessage);
        authorizationContainer.getChildren().add(passwordInput);
        authorizationContainer.getChildren().add(passwordErrorMessage);
        authorizationContainer.getChildren().add(submitButton);
        authorizationContainer.getChildren().add(separator);
        authorizationContainer.getChildren().add(switchModeButton);
        setAuthorizationMode(AuthorizationMode.Authorization);
    }

    public AuthorizationMode getAuthorizationMode() {
        return authorizationMode;
    }

    public void setAuthorizationMode(AuthorizationMode authorizationMode) {
        this.authorizationMode = authorizationMode;

        if (authorizationMode == AuthorizationMode.Authorization) {
            heading.setText("Log In");
            emailInput.setManaged(false);
            submitButton.setText("Log In");
            submitButton.setOnAction(new AuthorizeHandler());
            switchModeButton.setText("Create Account");
        } else {
            heading.setText("Create Account");
            emailInput.setManaged(true);
            submitButton.setText("Create Account");
            submitButton.setOnAction(new RegisterHandler());
            switchModeButton.setText("Log In");
        }
    }

    private Boolean validateUsername(String username) {
        Boolean isValid = true;
        String errorMessage = "";

        if (isValid) {
            return true;
        }

        usernameErrorMessage.setText(errorMessage);
        usernameErrorMessage.setManaged(true);
        return false;
    }

    private Boolean validateEmail(String email) {
        Boolean isValid = true;
        String errorMessage = "";

        if (isValid) {
            return true;
        }

        emailErrorMessage.setText(errorMessage);
        emailErrorMessage.setManaged(true);
        return false;
    }

    private Boolean validatePassword(String password) {
        Boolean isValid = true;
        String errorMessage = "";

        if (isValid) {
            return true;
        }

        passwordErrorMessage.setText(errorMessage);
        passwordErrorMessage.setManaged(true);
        return false;
    }

    public enum AuthorizationMode {
        Authorization,
        Registration
    }

    private class AuthorizeHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String username = usernameInput.getText();
            String password = passwordInput.getText();

            if (validateUsername(username) && validatePassword(password)) {
                User user = User.authorize(usernameInput.getText(), passwordInput.getText());

                if (user != null) {
                    String response = User.authenticate(user);

                    if (response != null) {
                        Controller.user = user;

                        usernameInput.setText("");
                        emailInput.setText("");
                        passwordInput.setText("");

                        if (Controller.componentMap.managementComponent == null) {
                            Controller.componentMap.managementComponent = new ManagementComponent();
                        }

                        Controller.scene.setRoot(Controller.componentMap.managementComponent.getContainer());
                    }
                }
            }
        }
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
