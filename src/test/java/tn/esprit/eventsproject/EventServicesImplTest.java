package tn.esprit.eventsproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.entities.Tache;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.LogisticsRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;
import tn.esprit.eventsproject.services.EventServicesImpl;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServicesImplTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private LogisticsRepository logisticsRepository;

    @InjectMocks
    private EventServicesImpl eventServices;

    private Event event;
    private Participant participant;
    private Logistics logistics;

    @BeforeEach
    void setUp() {
        event = new Event();
        event.setIdEvent(1);
        event.setDescription("Test Event");
        event.setDateDebut(LocalDate.of(2024, 6, 1));
        event.setDateFin(LocalDate.of(2024, 6, 5));
        event.setCout(500.0f);

        participant = new Participant();
        participant.setIdPart(1);
        participant.setNom("John");
        participant.setPrenom("Doe");
        participant.setTache(Tache.INVITE);

        logistics = new Logistics();
        logistics.setIdLog(1);
        logistics.setDescription("Test Logistics");
        logistics.setPrixUnit(50.0f);
        logistics.setQuantite(10);
    }

    @Test
    void addParticipantTest() {
        when(participantRepository.save(any(Participant.class))).thenReturn(participant);

        Participant savedParticipant = eventServices.addParticipant(participant);

        assertThat(savedParticipant).isNotNull();
        verify(participantRepository).save(any(Participant.class));
    }



    @Test
    void addAffectLogTest() {
        when(eventRepository.findByDescription(anyString())).thenReturn(event);
        when(logisticsRepository.save(any(Logistics.class))).thenReturn(logistics);

        Logistics savedLogistics = eventServices.addAffectLog(logistics, "Test Event");

        assertThat(savedLogistics).isNotNull();
        verify(logisticsRepository).save(any(Logistics.class));
    }




}
