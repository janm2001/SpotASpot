package com.codeninjas.spotaspot.events.service;

import com.codeninjas.spotaspot.events.controller.dto.EventResponse;
import com.codeninjas.spotaspot.events.entity.Event;
import com.codeninjas.spotaspot.events.entity.EventCategory;
import com.codeninjas.spotaspot.events.repository.EventRepository;
import com.codeninjas.spotaspot.users.entity.Role;
import com.codeninjas.spotaspot.users.entity.User;
import com.codeninjas.spotaspot.users.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class EventServiceTest {
    private final static LocalDate LOCAL_DATE = LocalDate.of(1989, 01, 13);
    private AutoCloseable autoCloseable;
    @Mock
    private EventRepository eventRepository;
    private EventService eventService;
    @Mock
    private Clock clock;
    @Mock
    private UserService userService;
    private LocalDateTime timeExample = LocalDateTime.of(2000, 10, 10, 10, 10, 10, 0);
    private Clock fixedClock;

    User exampleUser = User.builder()
            .firstName("Leonardo")
            .lastName("Markovic")
            .email("Leonardo@gmail.com")
            .username("leca12")
            .password("$2y$10$5B8Xl7ENxP2NzfodzcO9q.r8A5NR1/1uPJCjXFZ1y4q6dW337p/YG")
            .role(Role.USER)
            .createdAt(timeExample)
            .lastLogin(timeExample)
            .build();

    List<Event> exampleEvents = List.of(
            Event.builder()
                    .name("Programiranje java")
                    .description("Radionica za programiranje u javi")
                    .category(EventCategory.RADIONICA)
                    .city("Zagreb")
                    .location("Strojarska 22")
                    .dateTime(timeExample)
                    .isAvailable(true)
                    .createdBy(exampleUser)
                    .createdAt(timeExample)
                    .lastChange(timeExample)
                    .build(),
            Event.builder()
                    .name("Društvene igre online")
                    .description("Amongus na steamu")
                    .category(EventCategory.IGRA)
                    .city(null)
                    .location("Online")
                    .dateTime(timeExample)
                    .isAvailable(false)
                    .createdBy(exampleUser)
                    .createdAt(timeExample)
                    .lastChange(timeExample)
                    .build()
    );

    @BeforeEach
    void setUp() throws Exception {


        autoCloseable = MockitoAnnotations.openMocks(this);
        eventService = new EventService(eventRepository, clock);

        /*try (MockedStatic<UserService> utilities = Mockito.mockStatic(UserService.class)) {
            utilities.when(UserService::getCurrentUser).thenReturn(exampleUser);
        }*/

        doReturn(new PageImpl<Event>(exampleEvents)).when(eventRepository).findAll(any(Pageable.class));

        fixedClock = Clock.fixed(LOCAL_DATE.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        doReturn(fixedClock.instant()).when(clock).instant();
        doReturn(fixedClock.getZone()).when(clock).getZone();
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    @WithAnonymousUser
    void getAllEventsShouldReturnAllEventPaged() throws Exception {
        // given
        Pageable pageable = PageRequest.of(0, 10);

        // when
        Page<EventResponse> response = eventService.getAllEvents(pageable);
        // then
        assertThat(response).isEqualTo(
                new PageImpl<EventResponse>(exampleEvents.stream().map(EventResponse::new).toList()));
    }

    @Test
    void getEvent() {
        // given


        // when
        // then
    }

    @Test
    void addEvent() {
    }

    @Test
    void deleteEvent() {
    }

    @Test
    void updateEvent() {
    }
}