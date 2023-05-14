package com.codeninjas.spotaspot.events.controller.dto;

import com.codeninjas.spotaspot.events.entity.Event;
import com.codeninjas.spotaspot.events.entity.EventCategory;
import com.codeninjas.spotaspot.users.entity.User;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonSerialize
public record EventDTO(
    @NonNull Long id,
    @NonNull String name,
    String description,
    @NonNull EventCategory category,
    String city,
    @NonNull String location,
    String imageId,
    @NonNull LocalDateTime dateTime,
    @NonNull Boolean isAvailable,
    @NonNull UUID createdBy,
    @NonNull LocalDateTime createdAt,
    @NonNull LocalDateTime lastChange
) {

    public EventDTO(Event event) {
        this(
                event.getId(),
                event.getName(),
                event.getDescription(),
                event.getCategory(),
                event.getCity(),
                event.getLocation(),
                event.getImageId(),
                event.getDateTime(),
                event.getIsAvailable(),
                event.getCreatedBy().getId(),
                event.getCreatedAt(),
                event.getLastChange()
        );
    }

    public Event toEvent(User createdBy, LocalDateTime localDateTimeNow) {
        return Event.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .category(this.category)
                .city(this.city)
                .location(this.location)
                .imageId(this.imageId)
                .dateTime(this.dateTime)
                .isAvailable(this.isAvailable)
                .createdBy(createdBy)
                .createdAt(localDateTimeNow)
                .lastChange(localDateTimeNow)
                .build();
    }
}
