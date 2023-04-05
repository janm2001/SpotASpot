package com.codeninjas.spotaspot.events.repository;

import com.codeninjas.spotaspot.events.entity.Event;
import com.codeninjas.spotaspot.users.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface EventRepository extends JpaRepository<Event, Long>, PagingAndSortingRepository<Event, Long> {
    @Query(value = "SELECT e FROM Event e WHERE e.createdBy = :userId")
    Page<Event> findAllByCreatedBy(Pageable pageable, @Param("userId") Long userId);
}
