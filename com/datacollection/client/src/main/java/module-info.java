module org.example {
    requires javafx.controls;
    requires com.google.api.client;
    requires com.google.api.client.json.jackson2;
    requires com.google.gson;
    requires jackson.core;
    requires jackson.databind;
    requires jackson.annotations;
    exports com.datacollection.client.components;
    exports com.datacollection.client.model;
    exports com.datacollection.client;
}