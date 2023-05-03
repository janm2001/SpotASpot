package com.codeninjas.spotaspot.events.controller;

import com.codeninjas.spotaspot.events.controller.dto.EventAddRequest;
import com.codeninjas.spotaspot.events.controller.dto.EventPutRequest;
import com.codeninjas.spotaspot.events.controller.dto.EventResponse;
import com.codeninjas.spotaspot.events.entity.EventCategory;
import com.codeninjas.spotaspot.events.service.EventService;
import com.codeninjas.spotaspot.exception.EventNotFoundException;
import com.codeninjas.spotaspot.exception.InvalidDeleteEventException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    Logger logger = LoggerFactory.getLogger(EventController.class);

    @Operation(
            summary = "Get all events paginated",
            description = "Get all events paginated",
            responses = { @ApiResponse(responseCode = "200", ref = "getAllEvents200") })
    @GetMapping("/all")
    public ResponseEntity<?> getAllEvents(
            @ParameterObject Pageable pageable) {

        return ResponseEntity.ok(eventService.getAllEvents(pageable));
    }

    @Operation(
            summary = "Get all events created by user paginated",
            description = "Get all events created by user paginated",
            responses = {
                    @ApiResponse(responseCode = "200", ref = "getAllEventsForUser"),
                    @ApiResponse(responseCode = "404") })
    @GetMapping("/for-user/{userId}")
    public ResponseEntity<?> getAllEventsForUser(@ParameterObject Pageable pageable, @PathVariable UUID userId) {
        return ResponseEntity.ok(eventService.getAllEventsForUser(pageable, userId));
    }

    @Operation(
            summary = "Get event by id",
            description = "Get event by id",
            responses = {
                    @ApiResponse(responseCode = "200", ref = "getEvent"),
                    @ApiResponse(responseCode = "404", ref = "getEventNotFound") })
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getEvent(@PathVariable Long id) throws EventNotFoundException {
        EventResponse response = eventService.getEvent(id);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "[ORGANIZER] create event",
            description = "Create event (Role_ORGANIZER required), created by will be current logged in user",
            security = @SecurityRequirement(name = "token"),
            responses = {
                    @ApiResponse(responseCode = "200", ref = "addEvent"),
                    @ApiResponse(responseCode = "200", ref = "addEvent") })
    @PostMapping("/add")
    public ResponseEntity<?> addEvent(
            @RequestParam("file") MultipartFile file,

            @Validated
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Event to add",
                    content = @Content(schema = @Schema(implementation = EventAddRequest.class)))
            EventAddRequest request) throws Exception {
        eventService.addEvent(request, file);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "[ORGANIZER] delete event",
            description = "delete event (Role_ORGANIZER required), created by should be current logged in user",
            security = @SecurityRequirement(name = "token"),
            responses = {
                    @ApiResponse(responseCode = "200", ref = "deleteEvent"),
                    @ApiResponse(responseCode = "403", ref = "forbidden") })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) throws InvalidDeleteEventException {
        eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "[ORGANIZER] update event",
            description = "update event (authority ORGANIZER required), created by should be current logged in user",
            security = @SecurityRequirement(name = "token"),
            responses = {
                    @ApiResponse(responseCode = "200", ref = "updateEvent200"),
                    @ApiResponse(responseCode = "403", ref = "forbidden") })
    @PutMapping("/update")
    public ResponseEntity<?> updateEvent(
            @Validated
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Event to change",
                    content = @Content(schema = @Schema(implementation = EventPutRequest.class)))
            EventPutRequest request) throws Exception {

        eventService.updateEvent(request);

        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Get event categories listed",
            description = "Get all available event categories listed",
            responses = { @ApiResponse(responseCode = "200", ref = "getCategories200") })
    @GetMapping("/categories")
    public ResponseEntity<?> getCategories() {
        return ResponseEntity.ok(EventCategory.values());
    }
}
