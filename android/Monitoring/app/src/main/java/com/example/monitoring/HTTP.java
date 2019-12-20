package com.example.monitoring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

public class HTTP {
    public static final HttpRequestFactory httpRequestFactory = new NetHttpTransport().createRequestFactory();
    public static final JsonFactory jsonFactory = new JacksonFactory();
    public static String apiURL ="";
    public static final ObjectMapper objectMapper = new ObjectMapper();
}
