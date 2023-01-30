package com.example.bookunittesting.chapter04;

public class Message {

    private String header;
    private String body;
    private String footer;

    public Message(final String header, final String body, final String footer) {
        this.header = header;
        this.body = body;
        this.footer = footer;
    }

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }

    public String getFooter() {
        return footer;
    }
}
