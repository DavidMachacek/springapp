package com.david.demo.events;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface EventRepository extends CrudRepository<EventEntity, Long> {

    List<EventEntity> findAll();

}
