package com.datalogging;

import com.datalogging.datalogger.HTTP;
import com.datalogging.datalogger.model.Data;
import com.datalogging.datalogger.model.DataLogger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.util.Timer;
import java.util.TimerTask;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private static class MyTask extends TimerTask
    {
        DataLogger dataLogger;

        public MyTask(DataLogger dataLogger) {
            this.dataLogger = dataLogger;
        }

        public void run()
        {
            Data data = new Data(random(1000.0, 2000.0), random(30.0, 50.0), random(20.0, 25.0), dataLogger.getID());
            String response = Data.create(dataLogger, data);

            System.out.println(response);

            if (response == null) {
                return;
            }
        }
    }

    private static Double random(Double min, Double max) {
        return Math.random() * (max - min) + min;
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

        VBox root = new VBox();
        Scene scene = new Scene(root);

        Label idKey = new Label();
        idKey.setText("ID");

        TextField idValue = new TextField();
        idValue.setText("1");

        Label passwordKey = new Label();
        passwordKey.setText("Password");

        TextField passwordValue = new TextField();
        passwordValue.setText("1");

        Label frequencyKey = new Label();
        frequencyKey.setText("Frequency (Seconds)");

        TextField frequencyValue = new TextField();
        frequencyValue.setText("60");

        Label statusLabel = new Label();
        statusLabel.setText("Data logger is not running!");

        Button startButton = new Button("Start");

        Timer timer = new Timer();

        startButton.setOnAction(event -> {
            DataLogger dataLogger = DataLogger.authorize(Long.valueOf(idValue.getText()), passwordValue.getText());

            if (dataLogger != null) {
                TimerTask task = new MyTask(dataLogger);

                timer.schedule(task, 0, 1000 * Integer.valueOf(frequencyValue.getText()));
            }
        });

        Button stopButton = new Button("Stop");

        stopButton.setOnAction(event -> {
            timer.cancel();
        });

        root.getChildren().add(idKey);
        root.getChildren().add(idValue);
        root.getChildren().add(passwordKey);
        root.getChildren().add(passwordValue);
        root.getChildren().add(frequencyKey);
        root.getChildren().add(frequencyValue);
        root.getChildren().add(startButton);
        root.getChildren().add(stopButton);
        root.getChildren().add(statusLabel);

        stage.setMaximized(true);
        stage.setTitle("Data Logging");
        stage.setScene(scene);
        stage.show();
    }
}
