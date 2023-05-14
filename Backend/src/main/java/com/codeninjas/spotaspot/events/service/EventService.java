package com.codeninjas.spotaspot.events.service;

import com.codeninjas.spotaspot.auth.service.JwtService;
import com.codeninjas.spotaspot.aws.config.S3Buckets;
import com.codeninjas.spotaspot.aws.service.S3Service;
import com.codeninjas.spotaspot.events.controller.dto.EventAddRequest;
import com.codeninjas.spotaspot.events.controller.dto.EventDTO;
import com.codeninjas.spotaspot.events.controller.dto.EventPutRequest;
import com.codeninjas.spotaspot.events.entity.Event;
import com.codeninjas.spotaspot.events.repository.EventRepository;
import com.codeninjas.spotaspot.exception.EventNotFoundException;
import com.codeninjas.spotaspot.exception.InvalidAddEventException;
import com.codeninjas.spotaspot.exception.InvalidDeleteEventException;
import com.codeninjas.spotaspot.exception.UserNotOwnerException;
import com.codeninjas.spotaspot.users.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final JwtService jwtService;
    private final Clock clock;
    private final S3Service s3Service;
    private final S3Buckets s3Buckets;

    public Page<EventDTO> getAllEvents(Pageable pageable) {
        return eventRepository.findAll(pageable).map(EventDTO::new);
    }

    public Page<EventDTO> getAllEventsForUser(Pageable pageable, UUID userId) throws DataAccessException {
        return eventRepository.findAllByCreatedBy(pageable, userId).map(EventDTO::new);
    }

    public EventDTO getEvent(Long id) throws EventNotFoundException {
        Event event = eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(id));
        return new EventDTO(event);
    }

    public void addEvent(EventAddRequest eventAddRequest) throws Exception {
        User user = jwtService.getCurrentUser();
        Event event = eventAddRequest.toEvent(user, LocalDateTime.now(clock));
        try {
            eventRepository.save(event);
        } catch(DataAccessException e) {
            e.printStackTrace();
            throw new InvalidAddEventException();
        }
    }

    public void deleteEvent(Long id) throws InvalidDeleteEventException {
        try {
            eventRepository.deleteById(id);
        } catch(DataAccessException e) {
            e.printStackTrace();
            throw new InvalidDeleteEventException(id);
        }
    }

    public void updateEvent(EventPutRequest request) throws Exception {
        Event targetEvent = eventRepository.findById(request.id()).orElseThrow(() ->
                new EventNotFoundException(request.id()));
        User currentUser = jwtService.getCurrentUser();
        if (!Objects.equals(targetEvent.getCreatedBy().getId(), currentUser.getId())) {
            throw new UserNotOwnerException();
        }
        eventRepository.save(request.toEventFill(targetEvent, LocalDateTime.now(clock)));
    }

    public void uploadImage(Long eventId, MultipartFile file) {
        checkIfEventExists(eventId);
        String eventImageId = UUID.randomUUID().toString();
        try {
            s3Service.putObject(
                    s3Buckets.getImages(),
                    "event-images/%s/%s".formatted(eventId, eventImageId),
                    file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        eventRepository.updateEventImageId(eventImageId, eventId);
    }

    public byte[] getImage(Long eventId) throws EventNotFoundException {

        EventDTO event = eventRepository
                .findById(eventId)
                .map(EventDTO::new)
                .orElseThrow(() -> new EventNotFoundException(eventId));

        if (event.imageId().isBlank()) {
            throw new ResourceNotFoundException("Event with id [%d] image not found".formatted(eventId));
        }

        byte[] image = s3Service.getObject(
                s3Buckets.getImages(),
                "event-images/%s/%s".formatted(eventId, event.imageId())
        );
        return image;
    }

    private void checkIfEventExists(Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new ResourceNotFoundException(
                    "Event with id [%d] not found".formatted(eventId)
            );
        }
    }
}
