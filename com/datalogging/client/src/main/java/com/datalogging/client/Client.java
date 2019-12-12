package com.datalogging.client;

import com.datalogging.client.components.*;
import com.datalogging.client.components.datalogger.DataLoggerManagerComponent;
import com.datalogging.client.components.group.GroupManagerComponent;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Client extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        Scene scene = new Scene(root);

        Controller controller = new Controller(root, scene);
        controller.addComponent("data-logging", new DataLoggingComponent(controller));
        controller.addComponent("browser", new BrowserComponent(controller));
        controller.addComponent("authorization", new AuthorizationComponent(controller, AuthorizationMode.Authorization));
        controller.addComponent("management", new ManagementComponent(controller));
        controller.addComponent("dashboard", new DashboardComponent(controller));
        controller.addComponent("group-manager", new GroupManagerComponent(controller));
        controller.addComponent("data-logger-manager", new DataLoggerManagerComponent(controller));
        controller.setRoot("data-logging");

        stage.setMaximized(true);
        stage.setTitle("Data Logging");
        stage.setScene(scene);
        stage.show();
    }
}
