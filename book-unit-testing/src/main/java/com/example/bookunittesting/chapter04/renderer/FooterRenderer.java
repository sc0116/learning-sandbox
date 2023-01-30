package com.example.bookunittesting.chapter04.renderer;

import com.example.bookunittesting.chapter04.Message;

public class FooterRenderer implements Renderer {

    @Override
    public String render(final Message message) {
        return String.format("<i>%s</i>", message.getFooter());
    }
}
