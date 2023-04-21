package com.codeninjas.spotaspot.events.entity;

import com.codeninjas.spotaspot.users.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_generator")
    @SequenceGenerator(name = "event_generator", sequenceName = "event_id_seq", allocationSize = 1)
    private long id;
    @Column(nullable = false)
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventCategory category;
    private String city;
    @Column(nullable = false)
    private String location;
    @Column (nullable = false)
    private LocalDateTime dateTime;
    @Column (nullable = false)
    private Boolean isAvailable;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
    @Column (nullable = false)
    private LocalDateTime createdAt;
    @Column (nullable = false)
    private LocalDateTime lastChange;
}
