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
    public EventListener eventListener(EventRepository eventRepository) {
        return new EventListener(eventRepository);
    }

    @Bean

    public EventPublisher eventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        return new EventPublisher(applicationEventPublisher);
    }
}
