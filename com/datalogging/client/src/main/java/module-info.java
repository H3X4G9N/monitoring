module org.example {
    requires javafx.controls;
    requires com.google.api.client;
    requires com.google.api.client.json.jackson2;
    requires com.google.gson;
    requires jackson.core;
    requires jackson.databind;
    requires jackson.annotations;
    exports com.datalogging.client.components;
    exports com.datalogging.client.model;
    exports com.datalogging.client;
}