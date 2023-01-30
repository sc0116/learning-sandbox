package com.example.bookunittesting.chapter04.renderer;

import com.example.bookunittesting.chapter04.Message;

public class HeaderRenderer implements Renderer {

    @Override
    public String render(final Message message) {
        return String.format("<h1>%s</h1>", message.getHeader());
    }
}
