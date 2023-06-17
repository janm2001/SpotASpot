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
        name = "interested"
)
public class Interested {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interested that = (Interested) o;
        return Objects.equals(user, that.user) && Objects.equals(event, that.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, event);
    }

    @Override
    public String toString() {
        return "Interested{" +
                "user=" + user +
                ", event=" + event +
                '}';
    }
}
