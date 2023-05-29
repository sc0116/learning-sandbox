package learning.springevents.common.event;

import org.springframework.context.ApplicationEventPublisher;

public class Events {

    private static ApplicationEventPublisher publisher;

    static void setPublisher(final ApplicationEventPublisher publisher) {
        Events.publisher = publisher;
    }

    public static void raise(final Object event) {
        if (publisher != null) {
            publisher.publishEvent(event);
        }
    }
}
