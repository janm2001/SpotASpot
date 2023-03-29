package com.codeninjas.spotaspot.events.repository;

import com.codeninjas.spotaspot.events.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
