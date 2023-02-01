package com.example.bookunittesting.chapter05;

public class User {

    public String name;

    public String normalizeName(final String name) {
        final String result = name.trim();

        if (result.length() > 50) {
            return result.substring(0, 50);
        }

        return result;
    }
}
