package com.codeninjas.spotaspot.events.controller.dto;

import com.codeninjas.spotaspot.events.entity.Event;
import com.codeninjas.spotaspot.events.entity.EventCategory;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.time.LocalDateTime;

@JsonSerialize
public record EventPutRequest(
        @NonNull Long id,
        @NonNull String name,
        String description,
        @NonNull EventCategory category,
        String city,
        @NonNull String location,
        @NonNull LocalDateTime dateTime,
        @NonNull Boolean isAvailable
) {



    public EventPutRequest(Event event) {
        this(
                event.getId(),
                event.getName(),
                event.getDescription(),
                event.getCategory(),
                event.getCity(),
                event.getLocation(),
                event.getDateTime(),
                event.getIsAvailable()
        );
    }

    public Event toEventFill(Event event, LocalDateTime localDateTimeNow) {
        return Event.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .category(this.category)
                .city(this.city)
                .location(this.location)
                .dateTime(this.dateTime)
                .isAvailable(this.isAvailable)
                .createdBy(event.getCreatedBy())
                .createdAt(event.getCreatedAt())
                .lastChange(localDateTimeNow)
                .build();
    }
}
