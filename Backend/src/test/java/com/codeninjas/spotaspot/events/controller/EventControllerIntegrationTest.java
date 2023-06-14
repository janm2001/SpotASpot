package com.codeninjas.spotaspot.events.controller;

import com.codeninjas.spotaspot.events.entity.Event;
import com.codeninjas.spotaspot.events.entity.EventCategory;
import com.codeninjas.spotaspot.events.repository.EventRepository;
import com.codeninjas.spotaspot.users.entity.Role;
import com.codeninjas.spotaspot.users.entity.User;
import com.codeninjas.spotaspot.users.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class EventControllerIntegrationTest {

    /*private final static LocalDateTime fixedTime = LocalDateTime.of(1989, 01, 13, 0, 0, 0, 0);
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    User exampleUser = User.builder()
            .firstName("Leonardo")
            .lastName("Markovic")
            .email("Leonardo@gmail.com")
            .username("leca12")
            .password("$2y$10$5B8Xl7ENxP2NzfodzcO9q.r8A5NR1/1uPJCjXFZ1y4q6dW337p/YG")
            .role(Role.ORGANIZER)
            .createdAt(fixedTime)
            .lastLogin(fixedTime)
            .lastChange(fixedTime)
            .build();

    List<Event> exampleEvents = List.of(
            Event.builder()
                    .id(1L)
                    .name("Programiranje java")
                    .description("Radionica za programiranje u javi")
                    .category(EventCategory.RADIONICA)
                    .city("Zagreb")
                    .location("Strojarska 22")
                    .dateTime(fixedTime)
                    .isAvailable(true)
                    .createdBy(exampleUser)
                    .createdAt(fixedTime)
                    .lastChange(fixedTime)
                    .build(),
            Event.builder()
                    .id(2L)
                    .name("Društvene igre online")
                    .description("Amongus na steamu")
                    .category(EventCategory.IGRA)
                    .city(null)
                    .location("Online")
                    .dateTime(fixedTime)
                    .isAvailable(false)
                    .createdBy(exampleUser)
                    .createdAt(fixedTime)
                    .lastChange(fixedTime)
                    .build()
    );

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        eventRepository.deleteAll();

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @AfterEach
    void tearDown() {
        eventRepository.deleteAll();
    }

    @Test
    @WithAnonymousUser
    void getAllEvents_should_return_events_as_page() throws Exception {
        // given
        userRepository.save(exampleUser);
        eventRepository.saveAll(exampleEvents);

        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<EventResponse> expected = new PageImpl<>(exampleEvents.stream().map(EventResponse::new).toList(), pageRequest, 2);
        // then
        mockMvc.perform(get("/api/v1/event/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));

    }
    @Test
    void getAllEventsForUser() {
    }

    @Test
    void getEvent() {
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

    @Test
    void getCategories() {
    }*/
}