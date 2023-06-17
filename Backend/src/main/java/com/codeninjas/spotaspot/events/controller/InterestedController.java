package com.codeninjas.spotaspot.events.controller;

import com.codeninjas.spotaspot.events.service.InterestedService;
import com.codeninjas.spotaspot.exception.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/event/")
@RequiredArgsConstructor
public class InterestedController {

    private final InterestedService interestedService;
    Logger logger = LoggerFactory.getLogger(InterestedController.class);

    @GetMapping("all-events-interested-for-user/{userId}")
    public ResponseEntity<?> getEventsInterestedForUser(@ParameterObject Pageable pageable, @PathVariable UUID userId) throws UserNotFoundException {
        return ResponseEntity.ok(interestedService.getAllEventsInterestedForUser(pageable, userId));
    }

    @GetMapping("all-users-interested-to-event/{eventId}")
    public ResponseEntity<?> getUsersInterestedToEvent(@ParameterObject Pageable pageable, @PathVariable Long eventId) throws EventNotFoundException {
        return ResponseEntity.ok(interestedService.getAllUsersInterestedToEvent(pageable, eventId));
    }

    @PostMapping("interested/{eventId}")
    public ResponseEntity<?> interestedToEvent(@PathVariable Long eventId) throws EventNotFoundException, EventAlreadyInterestedToException {
        interestedService.interestedToEvent(eventId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("remove-interested/{eventId}")
    public ResponseEntity<?> removeInterestedToEvent(@PathVariable Long eventId)
            throws EventNotFoundException, EventNotInterestedToException {
        interestedService.removeInterestedToEvent(eventId);
        return ResponseEntity.ok().build();
    }

}
