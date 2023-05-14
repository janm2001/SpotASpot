package com.codeninjas.spotaspot.events.controller.dto;

import com.codeninjas.spotaspot.events.entity.Event;
import com.codeninjas.spotaspot.events.entity.EventCategory;
import com.codeninjas.spotaspot.users.entity.User;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.time.LocalDateTime;

@JsonSerialize
public record EventAddRequest(
        @NonNull String name,
        String description,
        @NonNull EventCategory category,
        String city,
        @NonNull String location,
        @NonNull LocalDateTime dateTime,
        @NonNull Boolean isAvailable
) {

    public EventAddRequest(Event event) {
        this(
                event.getName(),
                event.getDescription(),
                event.getCategory(),
                event.getCity(),
                event.getLocation(),
                event.getDateTime(),
                event.getIsAvailable()
        );
    }

    public Event toEvent(User createdBy, LocalDateTime localDateTimeNow) {
        return Event.builder()
                .name(this.name)
                .description(this.description)
                .category(this.category)
                .city(this.city)
                .location(this.location)
                .dateTime(this.dateTime)
                .isAvailable(this.isAvailable)
                .createdBy(createdBy)
                .createdAt(localDateTimeNow)
                .lastChange(localDateTimeNow)
                .build();
    }
}
