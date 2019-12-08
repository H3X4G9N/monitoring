package com.datacollection.client;

import com.datacollection.client.model.*;
import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.datacollection.client.components.*;


import java.util.List;
import java.util.SortedSet;

public class Client extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new VBox());

        System.out.println(User.register("user", "user@gmail.com", "password"));

        System.out.println(User.register("user2", "user2@gmail.com", "password2"));

        User user = User.authorize("user", "password");
        System.out.println(user);

        System.out.println(User.authenticate(user));

        DCGroup dcGroup = DCGroup.create(user, new DCGroup("group", "description", true, "password", false));
        System.out.println(dcGroup);

        DCGroup dcGroup2 = DCGroup.create(user, new DCGroup("group2", "description2", true, "password2", false));
        System.out.println(dcGroup);

        DCGroup dcGroup3 = DCGroup.create(user, new DCGroup("group3", "description3", false, "password3", true));
        System.out.println(dcGroup);

        List<DCGroup> dcGroups = DCGroup.getAllFromUser(user);
        System.out.println(dcGroups);

        System.out.println(DCGroup.update(user, dcGroups.get(2).getName(), new DCGroup("name2", "description2", false, "password2", true)));

        System.out.println(DCGroup.delete(user, dcGroups.get(2).getName()));

        DC dc = DC.activate(user, dcGroups.get(0).getName(), new DC("name", "description", "activation-key"));
        System.out.println(dc);

        List<DC> allDCsFromUser = DC.getAllFromUser(user);
        System.out.println(allDCsFromUser);

        List<DC> allDCsFromGroup = DC.getAllFromDCGroup(user, dcGroups.get(0).getName());
        System.out.println(allDCsFromGroup);

        //System.out.println(DC.deactivate(user, allDCsFromUser.get(0).getId()));

        System.out.println(Permission.create(user, new Permission("user2", dcGroups.get(0).getName())));

        System.out.println(Permission.getAllFromDCGroup(user, dcGroups.get(0).getName()));

        System.out.println(Permission.update(user, "user2", dcGroups.get(0).getName(), new Permission("user", dcGroups.get(0).getName())));

        System.out.println(Permission.delete(user, "user", dcGroups.get(0).getName()));

        List<DCGroup> visibleDCGroups = DCGroup.getAllVisible();
        System.out.println(visibleDCGroups);

        DCGroupAuthentication dcGroupAuthentication = DCGroup.authorize(visibleDCGroups.get(0).getName(), "password");
        System.out.println(dcGroupAuthentication);

        List<DC> dcs = DC.getAllFromDCGroup(dcGroupAuthentication.getDcGroup(), dcGroupAuthentication.getToken());
        System.out.println(dcs);

        List<DCData> dcData = DCData.getAllFromDC(dcs.get(0).getId(), dcGroupAuthentication.getToken());
        System.out.println(dcData);

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
        //stage.show();
    }
}