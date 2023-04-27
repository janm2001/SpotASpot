package com.codeninjas.spotaspot.events.repository;

import com.codeninjas.spotaspot.events.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface EventRepository extends CrudRepository<Event, Long>, PagingAndSortingRepository<Event, Long> {
    @Query(value = "SELECT e FROM Event e WHERE e.createdBy.id = :userId")
    Page<Event> findAllByCreatedBy(Pageable pageable, @Param("userId") UUID userId);
}
