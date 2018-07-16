package com.david.demo.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventListener {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationEventListener.class);

    private EventRepository eventRepository;

    public ApplicationEventListener(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @EventListener
    public void onApplicationEvent(ApplicationEvent event) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setMessage(event.getMessage());
        eventRepository.save(eventEntity);
        System.out.println("Received spring custom event - " + event.getMessage());
        logger.debug("Received spring custom event - " + event.getMessage());
    }
}