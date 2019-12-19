module com.datalogging {
    requires javafx.controls;
    requires com.google.api.client;
    requires com.google.api.client.json.jackson2;
    requires com.google.gson;
    requires jackson.core;
    requires jackson.databind;
    requires jackson.annotations;
    exports com.datalogging;
    exports com.datalogging.user.components;
    exports com.datalogging.user.model;
    exports com.datalogging.user;
}
