package com.david.demo.events;

import static org.junit.Assert.*;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.david.demo.PersistenceConfiguration;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        EventConfiguration.class,
        PersistenceConfiguration.class
})
public class EventPublisherTest {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Test
    public void publish() {
        ApplicationEvent event = new ApplicationEvent("hello");
        eventPublisher.publishEvent(event);
        assertTrue(eventRepository.findAll().size()==1);
        assertEquals(eventRepository.findAll().get(0).getMessage(), "hello");
    }
}