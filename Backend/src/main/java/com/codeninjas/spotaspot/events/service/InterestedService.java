package com.codeninjas.spotaspot.events.service;

import com.codeninjas.spotaspot.auth.service.JwtService;
import com.codeninjas.spotaspot.events.controller.dto.EventDTO;
import com.codeninjas.spotaspot.events.entity.Event;
import com.codeninjas.spotaspot.events.entity.Going;
import com.codeninjas.spotaspot.events.entity.Interested;
import com.codeninjas.spotaspot.events.repository.EventRepository;
import com.codeninjas.spotaspot.exception.*;
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
public class InterestedService {

    private final EventRepository eventRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public Page<EventDTO> getAllEventsInterestedForUser(Pageable pageable, UUID userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        return eventRepository
                .findAllEventsInterestedToForUser(pageable, userId).map(EventDTO::new);
    }

    public Page<UserDTO> getAllUsersInterestedToEvent(Pageable pageable, Long eventId) throws EventNotFoundException {
        // TODO: create query using pageable
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));
        return new PageImpl<>(event.getInterests().stream().map(Interested::getUser).map(UserDTO::new).toList());
    }

    public void interestedToEvent(Long eventId) throws EventNotFoundException, EventAlreadyInterestedToException {
        User currentUser = jwtService.getCurrentUser();
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));
        Set<Event> events = eventRepository
                .findAllEventsInterestedToForUser(Pageable.unpaged(), currentUser.getId()).toSet();
        if (events.contains(event)) {
            throw new EventAlreadyInterestedToException(eventId);
        }
        event.getInterests().add(new Interested(currentUser, event));
        eventRepository.save(event);
    }

    public void removeInterestedToEvent(Long eventId) throws EventNotFoundException, EventNotInterestedToException {
        User currentUser = jwtService.getCurrentUser();
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));

        Set<Event> events = eventRepository
                .findAllEventsInterestedToForUser(Pageable.unpaged(), currentUser.getId()).toSet();
        if (!events.contains(event)) {
            throw new EventNotInterestedToException(eventId);
        }

        event.getInterests().remove(new Interested(currentUser, event));
        eventRepository.save(event);
    }

}
