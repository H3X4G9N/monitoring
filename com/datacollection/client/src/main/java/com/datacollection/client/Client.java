package com.datacollection.client;

import com.datacollection.client.model.DCGroup;
import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.datacollection.client.components.*;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Client extends Application {
    private RestTemplate restTemplate = new RestTemplate();

    ResponseEntity<?> register(String username, String email, String password) {
        String path = "/user/register?username=" + username + "&email=" + email + "&password=" + password;

        try {
            ResponseEntity<?> response = restTemplate.getForEntity("http://localhost:8080/api" + path, String.class);
            return response;
        } catch (RuntimeException e) {
            return null;
        }
    }

    ResponseEntity<?> authorize(String username, String password) {
        String path = "/user/authorize?username=" + username + "&password=" + password;

        try {
            ResponseEntity<?> response = restTemplate.getForEntity("http://localhost:8080/api" + path, String.class);
            return response;
        } catch (RuntimeException e) {
            return null;
        }
    }

    ResponseEntity<?> authenticate(String username, String token) {
        String path = "/user/authenticate?username=" + username + "&token=" + token;

        try {
            ResponseEntity<?> response = restTemplate.getForEntity("http://localhost:8080/api" + path, String.class);
            return response;
        } catch (RuntimeException e) {
            return null;
        }
    }

    ResponseEntity<?> createDCGroup(String userUsername, DCGroup dcGroup) {
        String path = "/dc-group/" + userUsername + "";

        try {
            ResponseEntity<?> response = restTemplate.postForEntity("http://localhost:8080/api" + path, dcGroup, DCGroup.class);
            return response;
        } catch (RuntimeException e) {
            return null;
        }
    }

    ResponseEntity<?> getAllVisibleDCGroups() {
        String path = "/dc-group";

        try {
            ResponseEntity<?> response = restTemplate.getForEntity("http://localhost:8080/api" + path, String.class);
            return response;
        } catch (RuntimeException e) {
            return null;
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new VBox());

        register("username", "email", "password");
        String token = authorize("username", "password").getBody().toString();
        authenticate("username", token);


        Controller controller = new Controller(scene);
        controller.addElement("start", new Start());
        controller.addElement("data-collector-browser", new DataCollectorBrowser());
        controller.addElement("arpr", new ARRP(ARRPType.Authorization, IDType.Email));
        controller.addElement("data-collector-management", new DataCollectorManagement());
        controller.addElement("account", new Account());
        controller.setElement("start");

        stage.setMaximized(true);
        stage.setTitle("Monitoring");
        stage.setScene(scene);
        stage.show();
    }
}