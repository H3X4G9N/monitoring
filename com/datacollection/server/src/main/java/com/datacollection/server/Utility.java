package com.datacollection.server;

public class Utility {
    public static String generateToken() {
        String token = "";
        String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_";

        for (int a = 0; a < 64; a++) {
            token += characters.charAt((int) Math.floor(Math.random() * characters.length()));
        }

        return token;
    }
}
