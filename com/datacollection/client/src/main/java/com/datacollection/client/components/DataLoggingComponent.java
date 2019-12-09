package com.datacollection.client.components;

import com.datacollection.client.model.DC;
import com.datacollection.client.model.DCGroup;
import com.datacollection.client.model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class DataLoggingComponent extends Component {
    User user;
    List<DCGroup> groups;
    List<DC> dcs;

    private AuthorizationComponent authorizationComponent;
    private GroupManagerComponent groupManagerComponent;

    public DataLoggingComponent() {
        authorizationComponent = new AuthorizationComponent(AuthorizationMode.Registration);
        groupManagerComponent = new GroupManagerComponent();

        getContainer().getChildren().add(authorizationComponent.getContainer());
        getContainer().getChildren().add(groupManagerComponent.getContainer());
    }

    private enum AuthorizationMode {
        Authorization,
        Registration
    }

    private class AuthorizationComponent extends Component {
        private AuthorizationMode authorizationMode;
        private User user;
        private TextField usernameInput;
        private Text usernameErrorMessage;
        private TextField emailInput;
        private Text emailErrorMessage;
        private PasswordField passwordInput;
        private Text passwordErrorMessage;
        private Button submitButton;
        private Button switchModeButton;

        public AuthorizationComponent(AuthorizationMode authorizationMode) {
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

        public void setAuthorizationMode(AuthorizationMode registrationAuthorizationMode) {
            this.authorizationMode = registrationAuthorizationMode;

            if (registrationAuthorizationMode == AuthorizationMode.Authorization) {
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
                        System.out.println(response);
                    }
                }
            }
        }

        private class AuthorizeHandler implements EventHandler<ActionEvent> {
            @Override
            public void handle(ActionEvent event) {
                String username = usernameInput.getText();
                String password = passwordInput.getText();

                if (validateUsername(username) && validatePassword(password)) {
                    user = User.authorize(usernameInput.getText(), passwordInput.getText());

                    if (user != null) {
                        String response = User.authenticate(user);

                        if (response != null) {
                            System.out.println(response);
                        }
                    }
                }
            }
        }

        private class SwitchModeHandler implements EventHandler<ActionEvent> {
            @Override
            public void handle(ActionEvent event) {
                if (authorizationMode == AuthorizationMode.Authorization) {
                    setAuthorizationMode(AuthorizationMode.Authorization);
                } else {
                    setAuthorizationMode(AuthorizationMode.Registration);
                }
            }
        }
    }

    private class GroupComponent extends Component {
        private DCGroup group;
        private TextField groupNameValue;
        private TextField groupDescriptionValue;

        public GroupComponent(DCGroup group) {
            this.group = group;

            VBox groupContainer = new VBox();

            Label groupNameKey = new Label();
            groupNameKey.setText("Group Name");

            groupNameValue = new TextField();
            groupNameValue.setEditable(false);

            Label groupDescriptionKey = new Label();
            groupDescriptionKey.setText("Group Name");

            groupDescriptionValue = new TextField();
            groupDescriptionValue.setEditable(false);

            groupContainer.getChildren().add(groupNameKey);
            groupContainer.getChildren().add(groupNameValue);
            groupContainer.getChildren().add(groupDescriptionKey);
            groupContainer.getChildren().add(groupDescriptionValue);
            getContainer().getChildren().add(groupContainer);
        }
    }

    private class GroupManagerComponent extends Component {
        private TextField groupNameInput;
        private TextArea groupDescriptionInput;
        private Button createGroupButton;
        private Button reloadGroupListButton;

        public GroupManagerComponent() {
            VBox groupManagerContainer = new VBox();

            Label labelGroupName = new Label();
            labelGroupName.setText("Group Name");

            groupNameInput = new TextField();

            Label labelGroupDescription = new Label();
            labelGroupDescription.setText("Group Description");

            groupDescriptionInput = new TextArea();

            createGroupButton = new Button();
            createGroupButton.setText("Create");
            createGroupButton.setOnAction(new CreateGroupHandler());

            reloadGroupListButton = new Button();
            reloadGroupListButton.setText("Reload");
            reloadGroupListButton.setOnAction(new ReloadGroupListHandler());

            groupManagerContainer.getChildren().add(labelGroupName);
            groupManagerContainer.getChildren().add(groupNameInput);
            groupManagerContainer.getChildren().add(labelGroupDescription);
            groupManagerContainer.getChildren().add(groupDescriptionInput);
            groupManagerContainer.getChildren().add(createGroupButton);
            getContainer().getChildren().add(groupManagerContainer);
        }

        private class ReloadGroupListHandler implements EventHandler<ActionEvent> {
            @Override
            public void handle(ActionEvent event) {
                DCGroup.getAllFromUser(user);
            }
        }

        private class CreateGroupHandler implements EventHandler<ActionEvent> {
            @Override
            public void handle(ActionEvent event) {

            }
        }
    }
}
