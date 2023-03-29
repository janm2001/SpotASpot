package com.codeninjas.spotaspot.events.controller.dto;

import com.codeninjas.spotaspot.events.entity.Event;
import com.codeninjas.spotaspot.events.entity.EventCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventPutRequest {

    private long id;
    private String name;
    private String description;
    private EventCategory category;
    private String city;
    private String location;
    private LocalDateTime dateTime;
    private Boolean isAvailable;

    public EventPutRequest(Event event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.category = event.getCategory();
        this.city = event.getCity();
        this.location = event.getLocation();
        this.dateTime = event.getDateTime();
        this.isAvailable = event.getIsAvailable();
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
