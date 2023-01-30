package com.example.bookunittesting.chapter04;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.bookunittesting.chapter04.renderer.BodyRenderer;
import com.example.bookunittesting.chapter04.renderer.FooterRenderer;
import com.example.bookunittesting.chapter04.renderer.HeaderRenderer;
import com.example.bookunittesting.chapter04.renderer.Renderer;
import java.util.List;
import org.junit.jupiter.api.Test;

class MessageRendererTest {

    @Test
    void message_renderer_uses_correct_sub_renderers() {
        final MessageRenderer sut = new MessageRenderer();

        final List<Renderer> renderers = sut.getSubRenderers();

        assertThat(renderers).hasSize(3);
        assertThat(renderers.get(0)).isInstanceOf(HeaderRenderer.class);
        assertThat(renderers.get(1)).isInstanceOf(BodyRenderer.class);
        assertThat(renderers.get(2)).isInstanceOf(FooterRenderer.class);
    }

    @Test
    void rendering_a_message() {
        final MessageRenderer sut = new MessageRenderer();
        final Message message = new Message("h", "b", "f");

        final String html = sut.render(message);

        assertThat(html).isEqualTo("<h1>h</h1><b>b</b><i>f</i>");
    }
}