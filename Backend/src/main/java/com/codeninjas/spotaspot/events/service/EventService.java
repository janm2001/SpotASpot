package com.codeninjas.spotaspot.events.service;

import com.codeninjas.spotaspot.events.controller.dto.EventAddRequest;
import com.codeninjas.spotaspot.events.controller.dto.EventPutRequest;
import com.codeninjas.spotaspot.events.controller.dto.EventResponse;
import com.codeninjas.spotaspot.events.entity.Event;
import com.codeninjas.spotaspot.events.repository.EventRepository;
import com.codeninjas.spotaspot.events.service.exceptions.EventNotFoundException;
import com.codeninjas.spotaspot.events.service.exceptions.InvalidAddEventException;
import com.codeninjas.spotaspot.events.service.exceptions.InvalidDeleteEventException;
import com.codeninjas.spotaspot.events.service.exceptions.UserNotOwnerException;
import com.codeninjas.spotaspot.users.entity.User;
import com.codeninjas.spotaspot.users.repository.UserRepository;
import com.codeninjas.spotaspot.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final Clock clock;

    public Page<EventResponse> getAllEvents(Pageable pageable) throws Exception {
        return eventRepository.findAll(pageable).map(EventResponse::new);
    }

    public Page<EventResponse> getAllEventsForUser(Pageable pageable, Long userId) throws DataAccessException {
        return eventRepository.findAllByCreatedBy(pageable, userId).map(EventResponse::new);
    }

    public EventResponse getEvent(Long id) throws EventNotFoundException {
        Event event = eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(id));
        return new EventResponse(event);
    }

    public void addEvent(EventAddRequest eventAddRequest) throws Exception {
        User user = UserService.getCurrentUser();
        Event event = eventAddRequest.toEvent(user, LocalDateTime.now(clock));
        try {
            eventRepository.save(event);
        } catch(DataAccessException e) {
            throw new InvalidAddEventException();
        }
    }

    public void deleteEvent(Long id) throws InvalidDeleteEventException {
        try {
            eventRepository.deleteById(id);
        } catch(DataAccessException e) {
            throw new InvalidDeleteEventException();
        }
    }

    public void updateEvent(EventPutRequest request) throws Exception {
        Event targetEvent = eventRepository.findById(request.getId()).orElseThrow(() ->
                new EventNotFoundException(request.getId()));
        User currentUser = UserService.getCurrentUser();
        if (targetEvent.getCreatedBy().getId() != currentUser.getId()) {
            throw new UserNotOwnerException();
        }
        eventRepository.save(request.toEventFill(targetEvent, LocalDateTime.now(clock)));
    }
}
