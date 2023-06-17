package com.codeninjas.spotaspot.events.service;

import com.codeninjas.spotaspot.auth.service.JwtService;
import com.codeninjas.spotaspot.events.controller.dto.EventDTO;
import com.codeninjas.spotaspot.events.entity.Event;
import com.codeninjas.spotaspot.events.entity.Going;
import com.codeninjas.spotaspot.events.repository.EventRepository;
import com.codeninjas.spotaspot.exception.EventAlreadyGoingToException;
import com.codeninjas.spotaspot.exception.EventNotFoundException;
import com.codeninjas.spotaspot.exception.EventNotGoingToException;
import com.codeninjas.spotaspot.exception.UserNotFoundException;
import com.codeninjas.spotaspot.users.controller.dto.UserDTO;
import com.codeninjas.spotaspot.users.entity.User;
import com.codeninjas.spotaspot.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GoingService {

    private final EventRepository eventRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public Page<EventDTO> getAllEventsGoingForUser(Pageable pageable, UUID userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        return eventRepository
                .findAllEventsGoingToForUser(pageable, userId).map(EventDTO::new);
    }

    public Page<UserDTO> getAllUsersGoingToEvent(Pageable pageable, Long eventId) throws EventNotFoundException {
        // TODO: create query using pageable
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));
        return new PageImpl<>(event.getGoings().stream().map(Going::getUser).map(UserDTO::new).toList());
    }

    public void goingToEvent(Long eventId) throws EventNotFoundException, UserNotFoundException, EventAlreadyGoingToException {
        User currentUser = jwtService.getCurrentUser();
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));
        Set<Event> events = eventRepository
                .findAllEventsGoingToForUser(Pageable.unpaged(), currentUser.getId()).toSet();
        if (events.contains(event)) {
            throw new EventAlreadyGoingToException(eventId);
        }
        event.getGoings().add(new Going(currentUser, event));
        eventRepository.save(event);
    }

    public void removeGoingToEvent(Long eventId) throws EventNotFoundException, EventNotGoingToException {
        User currentUser = jwtService.getCurrentUser();
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));

        Set<Event> events = eventRepository
                .findAllEventsGoingToForUser(Pageable.unpaged(), currentUser.getId()).toSet();
        if (!events.contains(event)) {
            throw new EventNotGoingToException(eventId);
        }

        event.getGoings().remove(new Going(currentUser, event));
        eventRepository.save(event);
    }

}
