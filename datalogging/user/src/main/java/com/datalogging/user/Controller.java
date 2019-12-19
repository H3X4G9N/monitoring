package com.datalogging.user;

import com.datalogging.user.model.GroupAuthentication;
import com.datalogging.user.model.User;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class Controller {
    public static Scene scene = new Scene(new VBox());
    public static ComponentMap componentMap = new ComponentMap();
    public static User user = null;
    public static Map<Long, GroupAuthentication> groupAuthenticationMap = new HashMap<>();
}
