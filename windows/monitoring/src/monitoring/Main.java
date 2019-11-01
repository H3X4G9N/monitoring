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
import javafx.scene.control.TextField;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

        VBox dashboardRoot = new VBox();

        Scene scene = new Scene(signInRoot);

        {
            VBox container = new VBox();
            container.setMinWidth(320);
            container.setMinHeight(180);
            container.setPrefWidth(320);
            container.setPrefHeight(180);
            signInRoot.getChildren().add(new Group(container));

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

            Button signInButton = new Button("Sign in");
            signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scene.setRoot(dashboardRoot);
            }
            });
            container.getChildren().add(signInButton);

            Separator separator = new Separator();
            separator.setMinHeight(16);
            container.getChildren().add(separator);

            Hyperlink signUpLink = new Hyperlink("Sign up");
            signUpLink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scene.setRoot(signUpRoot);
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
                scene.setRoot(dashboardRoot);
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

        {
            Image accountImage = new Image(getClass().getResource("/res/img/account.png").toExternalForm());
            Image dataCollectorsImage = new Image(getClass().getResource("/res/img/data_collectors.png").toExternalForm());
            Image dashboardImage = new Image(getClass().getResource("/res/img/dashboard.png").toExternalForm());
            Image logoImage = new Image(getClass().getResource("/res/img/logo.png").toExternalForm());
            Image serverImage = new Image(getClass().getResource("/res/img/server.png").toExternalForm());
            Image settingsImage = new Image(getClass().getResource("/res/img/settings.png").toExternalForm());

            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(50);

            ColumnConstraints column2 = new ColumnConstraints();
            column2.setPercentWidth(50);
            column2.setHalignment(HPos.RIGHT);;

            GridPane topBar = new GridPane();
            topBar.setStyle("-fx-background-color:#f0f0f0");
            topBar.setPadding(new Insets(8, 8, 8, 8));
            topBar.getColumnConstraints().addAll(column, column2);
            dashboardRoot.getChildren().add(topBar);

            HBox section = new HBox();
            section.setStyle("-fx-background-color:#e3e3e3");
            section.setPadding(new Insets(8, 8, 8, 8));
            dashboardRoot.getChildren().add(section);
            VBox.setVgrow(section, Priority.ALWAYS);

            VBox sideBar = new VBox();
            section.getChildren().add(sideBar);
            
            ScrollPane content = new ScrollPane();
            HBox.setHgrow(content, Priority.ALWAYS);
            HBox.setMargin(content, new Insets(0, 0, 0, 8));
            content.setStyle("-fx-background-color:#f0f0f0");
            section.getChildren().add(content);

            ImageView logoIcon = new ImageView();
            logoIcon.setImage(logoImage);
            logoIcon.setFitWidth(32);
            logoIcon.setFitHeight(32);

            Label appName = new Label("Monitoring");
            appName.setGraphic(logoIcon);
            topBar.add(appName, 0, 0);

            ImageView accountIcon = new ImageView();
            accountIcon.setImage(accountImage);
            accountIcon.setFitWidth(32);
            accountIcon.setFitHeight(32);

            MenuButton userNameLabel = new MenuButton("Deniss Strombergs");
            userNameLabel.setGraphic(accountIcon);
            topBar.add(userNameLabel, 1, 0);

            Button dashboardButton = new Button("Dashboard");
            dashboardButton.setMinWidth(320);
            dashboardButton.setAlignment(Pos.BASELINE_LEFT);
            sideBar.getChildren().add(dashboardButton);

            ImageView dashboardIcon = new ImageView();
            dashboardIcon.setImage(dashboardImage);
            dashboardIcon.setFitWidth(32);
            dashboardIcon.setFitHeight(32);
            dashboardButton.setGraphic(dashboardIcon);

            Button serverButton = new Button("Server");
            serverButton.setMinWidth(320);
            serverButton.setAlignment(Pos.BASELINE_LEFT);
            sideBar.getChildren().add(serverButton);

            ImageView serverIcon = new ImageView();
            serverIcon.setImage(serverImage);
            serverIcon.setFitWidth(32);
            serverIcon.setFitHeight(32);
            serverButton.setGraphic(serverIcon);

            Button dataCollectorsButton = new Button("Data collectors");
            dataCollectorsButton.setMinWidth(320);
            dataCollectorsButton.setAlignment(Pos.BASELINE_LEFT);
            sideBar.getChildren().add(dataCollectorsButton);

            ImageView dataCollectorsIcon = new ImageView();
            dataCollectorsIcon.setImage(dataCollectorsImage);
            dataCollectorsIcon.setFitWidth(32);
            dataCollectorsIcon.setFitHeight(32);
            dataCollectorsButton.setGraphic(dataCollectorsIcon);

            Button settingsButton = new Button("Settings");
            settingsButton.setMinWidth(320);
            settingsButton.setAlignment(Pos.BASELINE_LEFT);
            sideBar.getChildren().add(settingsButton);

            ImageView settingsIcon = new ImageView();
            settingsIcon.setImage(settingsImage);
            settingsIcon.setFitWidth(32);
            settingsIcon.setFitHeight(32);
            settingsButton.setGraphic(settingsIcon);
        }

        stage.setTitle("Monitoring");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
