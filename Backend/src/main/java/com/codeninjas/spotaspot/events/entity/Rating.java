package com.codeninjas.spotaspot.events.entity;

import com.codeninjas.spotaspot.users.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "rating"
)
public class Rating {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;
    @Column(nullable = false)
    private Integer stars;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return Objects.equals(user, rating.user) && Objects.equals(event, rating.event) && Objects.equals(stars, rating.stars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, event, stars);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "user=" + user +
                ", event=" + event +
                ", stars=" + stars +
                '}';
    }
}
