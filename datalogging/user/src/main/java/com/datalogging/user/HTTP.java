package com.datalogging.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;

public class HTTP {
    public static String apiURL = "https://71f47e61.ngrok.io/api";
    public static HttpRequestFactory httpRequestFactory = new NetHttpTransport().createRequestFactory();
    public static ObjectMapper objectMapper = new ObjectMapper();
}
