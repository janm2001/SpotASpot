package com.codeninjas.spotaspot.events.entity;

import com.codeninjas.spotaspot.users.entity.User;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "event"
        /*uniqueConstraints = {
                @UniqueConstraint(
                        name = "image_id",
                        columnNames = "imageId"
                )
        }*/
)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_generator")
    @SequenceGenerator(name = "event_generator", sequenceName = "event_id_seq", allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventCategory category;
    private String city;
    @Column(nullable = false)
    private String location;
    @Column(unique = true)
    private String imageId;
    @Column (nullable = false)
    private LocalDateTime dateTime;
    @Column (nullable = false)
    private Boolean isAvailable;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;
    @Column (nullable = false)
    private LocalDateTime createdAt;
    @Column (nullable = false)
    private LocalDateTime lastChange;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "likes",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> likedBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) && Objects.equals(name, event.name) && Objects.equals(description, event.description) && category == event.category && Objects.equals(city, event.city) && Objects.equals(location, event.location) && Objects.equals(imageId, event.imageId) && Objects.equals(dateTime, event.dateTime) && Objects.equals(isAvailable, event.isAvailable) && Objects.equals(createdBy, event.createdBy) && Objects.equals(createdAt, event.createdAt) && Objects.equals(lastChange, event.lastChange) && Objects.equals(likedBy, event.likedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, category, city, location, imageId, dateTime, isAvailable, createdBy, createdAt, lastChange, likedBy);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", city='" + city + '\'' +
                ", location='" + location + '\'' +
                ", imageId='" + imageId + '\'' +
                ", dateTime=" + dateTime +
                ", isAvailable=" + isAvailable +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                ", lastChange=" + lastChange +
                ", likedBy=" + likedBy +
                '}';
    }
}
