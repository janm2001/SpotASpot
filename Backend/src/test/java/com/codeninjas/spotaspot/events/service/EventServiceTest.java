package com.codeninjas.spotaspot.events.service;

import com.codeninjas.spotaspot.config.JwtService;
import com.codeninjas.spotaspot.events.controller.dto.EventAddRequest;
import com.codeninjas.spotaspot.events.controller.dto.EventPutRequest;
import com.codeninjas.spotaspot.events.controller.dto.EventResponse;
import com.codeninjas.spotaspot.events.entity.Event;
import com.codeninjas.spotaspot.events.entity.EventCategory;
import com.codeninjas.spotaspot.events.repository.EventRepository;
import com.codeninjas.spotaspot.events.service.exceptions.EventNotFoundException;
import com.codeninjas.spotaspot.events.service.exceptions.InvalidDeleteEventException;
import com.codeninjas.spotaspot.users.entity.Role;
import com.codeninjas.spotaspot.users.entity.User;
import com.codeninjas.spotaspot.users.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class EventServiceTest {
    private final static LocalDate LOCAL_DATE = LocalDate.of(1989, 01, 13);
    private AutoCloseable autoCloseable;
    @Mock
    private EventRepository eventRepository;
    private EventService eventService;
    @Mock
    private JwtService jwtService;
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
                    .id(1)
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
                    .id(2)
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
        eventService = new EventService(eventRepository, jwtService, clock);
        doReturn(exampleUser).when(jwtService).getCurrentUser();


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
    void getEventShouldReturnEventById() throws EventNotFoundException {
        // given
        Long id = 1L;

        doReturn(Optional.of(exampleEvents.get(0))).when(eventRepository).findById(id);
        // when
        EventResponse response = eventService.getEvent(id);

        // then
        assertThat(response).isEqualTo(new EventResponse(exampleEvents.get(0)));
    }

    User exUser = User.builder()
            .firstName("Leonardo")
            .lastName("Markovic")
            .email("Leonardo@gmail.com")
            .username("leca12")
            .password("$2y$10$5B8Xl7ENxP2NzfodzcO9q.r8A5NR1/1uPJCjXFZ1y4q6dW337p/YG")
            .role(Role.USER)
            .createdAt(timeExample)
            .lastLogin(timeExample)
            .build();
    @Test
    void addEventShouldAddSpecifiedEvent() throws Exception {
        // given
        EventAddRequest request = EventAddRequest
                .builder()
                .name("Radionica Programiranja u Javi")
                .description("Programiranje u Javi")
                .category(EventCategory.RADIONICA)
                .city(null)
                .location("Online")
                .dateTime(timeExample)
                .isAvailable(true)
                .build();




        // when
        eventService.addEvent(request);


        // then
        ArgumentCaptor<Event> eventArgumentCaptor = ArgumentCaptor.forClass(Event.class);
        verify(eventRepository).save(eventArgumentCaptor.capture());
        Event capturedEvent = eventArgumentCaptor.getValue();
        assertThat(capturedEvent).isEqualTo(request.toEvent(exampleUser, LocalDateTime.now(clock)));
    }

    @Test
    void deleteEventShouldRemoveEventById() throws InvalidDeleteEventException {
        // given
        Long givenId = 2L;
        eventRepository.saveAll(exampleEvents);

        // when
        eventService.deleteEvent(givenId);

        // then
        verify(eventRepository).deleteById(givenId);
    }

    @Test
    void updateEventShouldChangeEvent() throws Exception {
        // given
        Event target = exampleEvents.get(1);

        eventRepository.saveAll(exampleEvents);
        EventPutRequest request = new EventPutRequest(target);
        request.setName("New name");
        request.setCity(null);
        request.setDescription("New description");

        doReturn(Optional.of(target)).when(eventRepository).findById(2L);
        // when
        eventService.updateEvent(request);
        // then
        ArgumentCaptor<Event> eventArgumentCaptor = ArgumentCaptor.forClass(Event.class);
        verify(eventRepository).save(eventArgumentCaptor.capture());
        Event capturedEvent = eventArgumentCaptor.getValue();
        assertThat(capturedEvent).isEqualTo(request.toEventFill(target, LocalDateTime.now(clock)));
    }
}