package com.datalogging;

import com.datalogging.user.Controller;
import com.datalogging.user.HTTP;
import com.datalogging.user.components.DataLoggingComponent;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            File file = new File("api-url.txt");
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();

            String str = new String(data, "UTF-8");
            HTTP.apiURL = str;
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }



        Controller controller = new Controller();
        controller.componentMap.dataLoggingComponent = new DataLoggingComponent();
        controller.scene.setRoot(controller.componentMap.dataLoggingComponent.getContainer());

        stage.setMaximized(true);
        stage.setTitle("Data Logging");
        stage.setScene(controller.scene);
        stage.show();
    }
}
