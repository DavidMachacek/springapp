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
/*
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        EventConfiguration.class,
        PersistenceConfiguration.class
})*/
public class EventPublisherTest {
/*
    EventRepository eventRepository;
    EventPublisher eventPublisher;

    @Test
    public void publish() {
        eventPublisher.publish("hello");
        assertTrue(eventRepository.findAll().size()==0);
    }
*/
}