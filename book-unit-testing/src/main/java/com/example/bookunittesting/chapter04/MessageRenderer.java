package com.example.bookunittesting.chapter04;

import com.example.bookunittesting.chapter04.renderer.BodyRenderer;
import com.example.bookunittesting.chapter04.renderer.FooterRenderer;
import com.example.bookunittesting.chapter04.renderer.HeaderRenderer;
import com.example.bookunittesting.chapter04.renderer.Renderer;
import java.util.List;
import java.util.stream.Collectors;

public class MessageRenderer implements Renderer {

    private final List<Renderer> subRenderers;

    public MessageRenderer() {
        subRenderers = List.of(
                new HeaderRenderer(),
                new BodyRenderer(),
                new FooterRenderer()
        );
    }

    @Override
    public String render(final Message message) {
        return subRenderers.stream()
                .map(renderer -> renderer.render(message))
                .collect(Collectors.joining());
    }

    public List<Renderer> getSubRenderers() {
        return subRenderers;
    }
}
