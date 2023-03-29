package com.codeninjas.spotaspot.events.controller.dto;

import com.codeninjas.spotaspot.events.entity.Event;
import com.codeninjas.spotaspot.events.entity.EventCategory;
import com.codeninjas.spotaspot.users.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {

    private long id;
    private String name;
    private String description;
    private EventCategory category;
    private String city;
    private String location;
    private LocalDateTime dateTime;
    private Boolean isAvailable;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime lastChange;

    public EventResponse(Event event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.category = event.getCategory();
        this.city = event.getCity();
        this.location = event.getLocation();
        this.dateTime = event.getDateTime();
        this.isAvailable = event.getIsAvailable();
        this.createdBy = event.getCreatedBy().getUsername();
        this.createdAt = event.getCreatedAt();
        this.lastChange = event.getLastChange();
    }
}
