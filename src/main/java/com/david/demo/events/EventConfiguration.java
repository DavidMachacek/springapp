package com.david.demo.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConfiguration {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Bean
    public ApplicationEventListener eventListener(EventRepository eventRepository) {
        return new ApplicationEventListener(eventRepository);
    }

    @Bean
    public EventPublisher eventPublisher() {
        return new EventPublisher();
    }
}
