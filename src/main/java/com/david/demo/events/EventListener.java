package com.david.demo.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EventListener {

    private static final Logger logger = LoggerFactory.getLogger(EventListener.class);

    private EventRepository eventRepository;

    public EventListener(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @org.springframework.context.event.EventListener
    public void onApplicationEvent(ApplicationEvent event) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setMessage(event.getMessage());
        eventRepository.save(eventEntity);
        System.out.println("Received spring custom event - " + event.getMessage());
        logger.debug("Received spring custom event - " + event.getMessage());
    }
}