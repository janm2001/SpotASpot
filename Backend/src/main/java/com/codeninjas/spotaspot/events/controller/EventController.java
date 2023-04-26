package com.codeninjas.spotaspot.events.controller;

import com.codeninjas.spotaspot.events.controller.dto.EventAddRequest;
import com.codeninjas.spotaspot.events.controller.dto.EventPutRequest;
import com.codeninjas.spotaspot.events.controller.dto.EventResponse;
import com.codeninjas.spotaspot.events.entity.EventCategory;
import com.codeninjas.spotaspot.events.service.EventService;
import com.codeninjas.spotaspot.exception.EventNotFoundException;
import com.codeninjas.spotaspot.events.service.exceptions.InvalidDeleteEventException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully returned events",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\n" +
                                                            "    \"content\": [\n" +
                                                            "        {\n" +
                                                            "            \"id\": 5,\n" +
                                                            "            \"name\": \"Utakmica Dinamo Hajduk\",\n" +
                                                            "            \"description\": \"Utakmica u splitu\",\n" +
                                                            "            \"category\": \"SPORT\",\n" +
                                                            "            \"city\": \"Split\",\n" +
                                                            "            \"location\": \"8 Mediteranskih Igara 2\",\n" +
                                                            "            \"dateTime\": \"2023-05-05T18:00:00\",\n" +
                                                            "            \"isAvailable\": true,\n" +
                                                            "            \"createdBy\": \"IHorvat\",\n" +
                                                            "            \"createdAt\": \"2023-04-14T17:23:56.370193\",\n" +
                                                            "            \"lastChange\": \"2023-04-14T17:23:56.370193\"\n" +
                                                            "        },\n" +
                                                            "        {\n" +
                                                            "            \"id\": 8,\n" +
                                                            "            \"name\": \"Standup komičar Dino Merlin\",\n" +
                                                            "            \"description\": \"Dođite i gledajte Dinu Merlina na prvom standupu\",\n" +
                                                            "            \"category\": \"STANDUP\",\n" +
                                                            "            \"city\": \"Zagreb\",\n" +
                                                            "            \"location\": \"Ul. Vuke Vukova 69\",\n" +
                                                            "            \"dateTime\": \"2023-04-12T20:00:00\",\n" +
                                                            "            \"isAvailable\": false,\n" +
                                                            "            \"createdBy\": \"IHorvat\",\n" +
                                                            "            \"createdAt\": \"2023-04-14T17:23:56.370193\",\n" +
                                                            "            \"lastChange\": \"2023-04-14T17:23:56.370193\"\n" +
                                                            "        },\n" +
                                                            "        {\n" +
                                                            "            \"id\": 4,\n" +
                                                            "            \"name\": \"Pub kviz u vintagu\",\n" +
                                                            "            \"description\": \"Pub kniz u vintagu\",\n" +
                                                            "            \"category\": \"KVIZ\",\n" +
                                                            "            \"city\": \"Zagreb\",\n" +
                                                            "            \"location\": \"Savska cesta 160\",\n" +
                                                            "            \"dateTime\": \"2023-06-05T13:00:00\",\n" +
                                                            "            \"isAvailable\": true,\n" +
                                                            "            \"createdBy\": \"IHorvat\",\n" +
                                                            "            \"createdAt\": \"2023-04-14T17:23:56.370193\",\n" +
                                                            "            \"lastChange\": \"2023-04-14T17:23:56.370193\"\n" +
                                                            "        },\n" +
                                                            "        {\n" +
                                                            "            \"id\": 10,\n" +
                                                            "            \"name\": \"Programiranje java\",\n" +
                                                            "            \"description\": \"Radionica za programiranje u javi\",\n" +
                                                            "            \"category\": \"RADIONICA\",\n" +
                                                            "            \"city\": \"Zagreb\",\n" +
                                                            "            \"location\": \"Strojarska\",\n" +
                                                            "            \"dateTime\": \"2023-03-30T16:00:00\",\n" +
                                                            "            \"isAvailable\": true,\n" +
                                                            "            \"createdBy\": \"LeoOrg\",\n" +
                                                            "            \"createdAt\": \"2023-04-14T17:28:01.705542\",\n" +
                                                            "            \"lastChange\": \"2023-04-14T17:28:01.705542\"\n" +
                                                            "        },\n" +
                                                            "        {\n" +
                                                            "            \"id\": 2,\n" +
                                                            "            \"name\": \"Predavanje o održivosti proizvoda\",\n" +
                                                            "            \"description\": \"Predavanje o održivosti proizvoda na feru, vodi profesor Blabla Blabal.\",\n" +
                                                            "            \"category\": \"PREDAVANJE\",\n" +
                                                            "            \"city\": \"Zagreb\",\n" +
                                                            "            \"location\": \"Unska ul. 3\",\n" +
                                                            "            \"dateTime\": \"2023-05-30T16:00:00\",\n" +
                                                            "            \"isAvailable\": true,\n" +
                                                            "            \"createdBy\": \"IHorvat\",\n" +
                                                            "            \"createdAt\": \"2023-04-14T17:23:56.370193\",\n" +
                                                            "            \"lastChange\": \"2023-04-14T17:23:56.370193\"\n" +
                                                            "        }\n" +
                                                            "    ],\n" +
                                                            "    \"pageable\": {\n" +
                                                            "        \"sort\": {\n" +
                                                            "            \"empty\": false,\n" +
                                                            "            \"sorted\": true,\n" +
                                                            "            \"unsorted\": false\n" +
                                                            "        },\n" +
                                                            "        \"offset\": 0,\n" +
                                                            "        \"pageNumber\": 0,\n" +
                                                            "        \"pageSize\": 5,\n" +
                                                            "        \"paged\": true,\n" +
                                                            "        \"unpaged\": false\n" +
                                                            "    },\n" +
                                                            "    \"totalPages\": 2,\n" +
                                                            "    \"totalElements\": 9,\n" +
                                                            "    \"last\": false,\n" +
                                                            "    \"size\": 5,\n" +
                                                            "    \"number\": 0,\n" +
                                                            "    \"sort\": {\n" +
                                                            "        \"empty\": false,\n" +
                                                            "        \"sorted\": true,\n" +
                                                            "        \"unsorted\": false\n" +
                                                            "    },\n" +
                                                            "    \"first\": true,\n" +
                                                            "    \"numberOfElements\": 5,\n" +
                                                            "    \"empty\": false\n" +
                                                            "}"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = ""
                                            )
                                    }
                            )
                    )
            }

    )
    @GetMapping("/all")
    public ResponseEntity<?> getAllEvents(
            @ParameterObject Pageable pageable) {
        Page<EventResponse> response;

        logger.debug("Debugging log");
        logger.info("Info log");
        logger.warn("Hey, This is a warning!");
        logger.error("Oops! We have an Error. OK");
        logger.trace("TRACE");

        try {
            response = eventService.getAllEvents(pageable);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Get all events created by user paginated",
            description = "Get all events created by user paginated",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully returned events",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\n" +
                                                            "    \"content\": [\n" +
                                                            "        {\n" +
                                                            "            \"id\": 10,\n" +
                                                            "            \"name\": \"Programiranje java\",\n" +
                                                            "            \"description\": \"Radionica za programiranje u javi\",\n" +
                                                            "            \"category\": \"RADIONICA\",\n" +
                                                            "            \"city\": \"Zagreb\",\n" +
                                                            "            \"location\": \"Strojarska\",\n" +
                                                            "            \"dateTime\": \"2023-03-30T16:00:00\",\n" +
                                                            "            \"isAvailable\": true,\n" +
                                                            "            \"createdBy\": \"LeoOrg\",\n" +
                                                            "            \"createdAt\": \"2023-04-14T17:28:01.705542\",\n" +
                                                            "            \"lastChange\": \"2023-04-14T17:28:01.705542\"\n" +
                                                            "        }\n" +
                                                            "    ],\n" +
                                                            "    \"pageable\": {\n" +
                                                            "        \"sort\": {\n" +
                                                            "            \"empty\": true,\n" +
                                                            "            \"sorted\": false,\n" +
                                                            "            \"unsorted\": true\n" +
                                                            "        },\n" +
                                                            "        \"offset\": 0,\n" +
                                                            "        \"pageNumber\": 0,\n" +
                                                            "        \"pageSize\": 20,\n" +
                                                            "        \"paged\": true,\n" +
                                                            "        \"unpaged\": false\n" +
                                                            "    },\n" +
                                                            "    \"totalPages\": 1,\n" +
                                                            "    \"totalElements\": 1,\n" +
                                                            "    \"last\": true,\n" +
                                                            "    \"size\": 20,\n" +
                                                            "    \"number\": 0,\n" +
                                                            "    \"sort\": {\n" +
                                                            "        \"empty\": true,\n" +
                                                            "        \"sorted\": false,\n" +
                                                            "        \"unsorted\": true\n" +
                                                            "    },\n" +
                                                            "    \"numberOfElements\": 1,\n" +
                                                            "    \"first\": true,\n" +
                                                            "    \"empty\": false\n" +
                                                            "}"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = ""
                                            )
                                    }
                            )
                    )
            }

    )
    @GetMapping("/for-user/{userId}")
    public ResponseEntity<?> getAllEventsForUser(@ParameterObject Pageable pageable, @PathVariable UUID userId) {
        Page<EventResponse> response;
        try {
            response = eventService.getAllEventsForUser(pageable, userId);
        } catch (DataAccessException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Get event by id",
            description = "Get event by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully returned event",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\n" +
                                                            "    \"id\": 2,\n" +
                                                            "    \"name\": \"Predavanje o održivosti proizvoda\",\n" +
                                                            "    \"description\": \"Predavanje o održivosti proizvoda na feru, vodi profesor Blabla Blabal.\",\n" +
                                                            "    \"category\": \"PREDAVANJE\",\n" +
                                                            "    \"city\": \"Zagreb\",\n" +
                                                            "    \"location\": \"Unska ul. 3\",\n" +
                                                            "    \"dateTime\": \"2023-05-30T16:00:00\",\n" +
                                                            "    \"isAvailable\": true,\n" +
                                                            "    \"createdBy\": \"IHorvat\",\n" +
                                                            "    \"createdAt\": \"2023-04-14T17:23:56.370193\",\n" +
                                                            "    \"lastChange\": \"2023-04-14T17:23:56.370193\"\n" +
                                                            "}"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "Event 11 not found"
                                            )
                                    }
                            )
                    )
            }

    )
    @GetMapping("/get/{id}")
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

    @Operation(
            summary = "[ORGANIZER] create event",
            description = "Create event (Role_ORGANIZER required), created by will be current logged in user",
            security = @SecurityRequirement(name = "token"),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully created event",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\n" +
                                                            "    \"id\": 2,\n" +
                                                            "    \"name\": \"Predavanje o održivosti proizvoda\",\n" +
                                                            "    \"description\": \"Predavanje o održivosti proizvoda na feru, vodi profesor Blabla Blabal.\",\n" +
                                                            "    \"category\": \"PREDAVANJE\",\n" +
                                                            "    \"city\": \"Zagreb\",\n" +
                                                            "    \"location\": \"Unska ul. 3\",\n" +
                                                            "    \"dateTime\": \"2023-05-30T16:00:00\",\n" +
                                                            "    \"isAvailable\": true,\n" +
                                                            "    \"createdBy\": \"IHorvat\",\n" +
                                                            "    \"createdAt\": \"2023-04-14T17:23:56.370193\",\n" +
                                                            "    \"lastChange\": \"2023-04-14T17:23:56.370193\"\n" +
                                                            "}"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = ""
                                            )
                                    }
                            )
                    )
            }

    )
    @PostMapping("/add")
    public ResponseEntity<?> addEvent(
            @Validated
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            value = "{\n" +
                                                    "    \"name\": \"Programiranje java\",\n" +
                                                    "    \"description\": \"Radionica za programiranje u javi\",\n" +
                                                    "    \"category\": \"RADIONICA\",\n" +
                                                    "    \"city\": \"Zagreb\",\n" +
                                                    "    \"location\": \"Strojarska\",\n" +
                                                    "    \"dateTime\": \"2023-03-30T16:00:00\",\n" +
                                                    "    \"isAvailable\": true\n" +
                                                    "}"
                                    )
                            }
                    )
            )
            EventAddRequest request) {
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

    @Operation(
            summary = "[ORGANIZER] delete event",
            description = "delete event (Role_ORGANIZER required), created by should be current logged in user",
            security = @SecurityRequirement(name = "token"),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully deleted event",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = ""
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\n" +
                                                            "    \"timestamp\": \"2023-04-24T20:23:02.925+00:00\",\n" +
                                                            "    \"status\": 403,\n" +
                                                            "    \"error\": \"Forbidden\",\n" +
                                                            "    \"message\": \"Forbidden\",\n" +
                                                            "    \"path\": \"/api/v1/event/delete/10\"\n" +
                                                            "}"
                                            )
                                    }
                            )
                    )
            }

    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        try {
            System.out.println("Koji kurac");
            eventService.deleteEvent(id);
        } catch(InvalidDeleteEventException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "[ORGANIZER] update event",
            description = "update event (authority ORGANIZER required), created by should be current logged in user",
            security = @SecurityRequirement(name = "token"),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated event",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = ""
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\n" +
                                                            "    \"timestamp\": \"2023-04-24T20:23:02.925+00:00\",\n" +
                                                            "    \"status\": 403,\n" +
                                                            "    \"error\": \"Forbidden\",\n" +
                                                            "    \"message\": \"Forbidden\",\n" +
                                                            "    \"path\": \"/api/v1/event/update\"\n" +
                                                            "}"
                                            )
                                    }
                            )
                    )
            }

    )
    @PutMapping("/update")
    public ResponseEntity<?> updateEvent(
            @Validated
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            value = "{\n" +
                                                    "    \"id\": \"10\",\n" +
                                                    "    \"name\": \"Programiranje java\",\n" +
                                                    "    \"description\": \"Radionica za programiranje u javi\",\n" +
                                                    "    \"category\": \"RADIONICA\",\n" +
                                                    "    \"city\": \"Zagreb\",\n" +
                                                    "    \"location\": \"Strojarska\",\n" +
                                                    "    \"dateTime\": \"2023-03-30T16:00:00\",\n" +
                                                    "    \"isAvailable\": true\n" +
                                                    "}"
                                    )
                            }
                    )
            )
            EventPutRequest request) {
        try {
            eventService.updateEvent(request);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Get event categories",
            description = "Get all available event categories",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully returned categories",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "[\n" +
                                                            "    \"RADIONICA\",\n" +
                                                            "    \"PREDAVANJE\",\n" +
                                                            "    \"KONCERT\",\n" +
                                                            "    \"KVIZ\",\n" +
                                                            "    \"SPORT\",\n" +
                                                            "    \"IGRA\",\n" +
                                                            "    \"NATJECANJE\",\n" +
                                                            "    \"STANDUP\",\n" +
                                                            "    \"DOBROTVORNI\"\n" +
                                                            "]"
                                            )
                                    }
                            )
                    )
            }
    )
    @GetMapping("/categories")
    public ResponseEntity<?> getCategories() {
        return ResponseEntity.ok(EventCategory.values());
    }
}
