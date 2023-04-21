package com.codeninjas.spotaspot.events.controller;

import com.codeninjas.spotaspot.events.controller.dto.EventAddRequest;
import com.codeninjas.spotaspot.events.controller.dto.EventPutRequest;
import com.codeninjas.spotaspot.events.controller.dto.EventResponse;
import com.codeninjas.spotaspot.events.entity.EventCategory;
import com.codeninjas.spotaspot.events.service.EventService;
import com.codeninjas.spotaspot.events.service.exceptions.EventNotFoundException;
import com.codeninjas.spotaspot.events.service.exceptions.InvalidDeleteEventException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllEvents(Pageable pageable) {
        Page<EventResponse> response;
        try {
            response = eventService.getAllEvents(pageable);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/for-user/{id}")
    public ResponseEntity<?> getAllEventsForUser(Pageable pageable, @PathVariable Long id) {
        Page<EventResponse> response;
        try {
            response = eventService.getAllEventsForUser(pageable, id);
        } catch (DataAccessException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEvent(@PathVariable Long id) {
        EventResponse response;
        try {
            response = eventService.getEvent(id);
        } catch (EventNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEvent(@Validated @RequestBody EventAddRequest request) {
        try {
            eventService.addEvent(request);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        try {
            eventService.deleteEvent(id);
        } catch(InvalidDeleteEventException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEvent(@Validated @RequestBody EventPutRequest request) {
        try {
            eventService.updateEvent(request);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getCategories() {
        return ResponseEntity.ok(EventCategory.values());
    }
}
