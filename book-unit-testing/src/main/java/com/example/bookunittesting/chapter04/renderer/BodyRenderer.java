package com.example.bookunittesting.chapter04.renderer;

import com.example.bookunittesting.chapter04.Message;

public class BodyRenderer implements Renderer {

    @Override
    public String render(final Message message) {
        return String.format("<b>%s</b>", message.getBody());
    }
}
