package net.javaguides.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.javaguides.springboot.model.Event;
import net.javaguides.springboot.repository.EventRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;
   
   
    
    @Override
    public Page<Event> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return eventRepository.findAll(pageable);
    }

    @Override
    public void saveEvent(Event event) {
        eventRepository.save(event);
    }

    @Override
    public Event getEventById(int id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        return optionalEvent.orElse(null);
    }

    @Override
    public void deleteEventById(int id) {
        eventRepository.deleteById(id);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    public List<Object[]> findEventForCSEDepartment() {
        return eventRepository.findEventForCSEDepartment();
    }
    @Override
    public long countEventsOnDate(String eventDate) {
        return eventRepository.countEventsOnDate(eventDate);
    }
    @Override
    public List<Object[]> findStudentsByDepartmentAndEventName(String department, String registeredEventName) {
        return eventRepository.findStudentsByDepartmentAndEventName(department, registeredEventName);
    }
    @Override
    public long countStudentsByDepartmentAndEventName(String department, String registeredEventName) {
        return eventRepository.countStudentsByDepartmentAndEventName(department, registeredEventName);
    }
    @Override
    public List<Object[]> findEventNamesAndDeadlinesByDate(String eventDate) {
        return eventRepository.findEventNamesAndDeadlinesByDate(eventDate);
    }
    @Override
    public List<Object[]> findEventNameWithStudentCountByDepartment(String department) {
        return eventRepository.findEventNameWithStudentCountByDepartment(department);
    } 
    @Override
    public List<Object[]> findDepartmentWithMaxStudents() {
        return eventRepository.findDepartmentWithMaxStudents();
    }
    @Override
    public List<Object[]> findEventWithMaxStudentsSubquery() {
        return eventRepository.findEventWithMaxStudentsSubquery();
    }
    @Override
    public List<Object[]> findDepartmentWithMaxParticipants() {
        return eventRepository.findDepartmentWithMaxParticipants();
    }
    @Override
    public long countEventsByStatus(String eventStatus) {
        return eventRepository.countEventsByStatus(eventStatus);
    }
    @Override
    public List<Object[]> findStudentsByRegisteredDateAndEventName(String registeredDate, String registeredEventName) {
        return eventRepository.findStudentsByRegisteredDateAndEventName(registeredDate, registeredEventName);
    }
    @Transactional
    @Modifying
    @Override
    public boolean bookTickets(int eventId, int numberOfTickets) {
        int rowsUpdated = eventRepository.bookTickets(eventId, numberOfTickets);
        return rowsUpdated > 0;
    }
    @Override
    public List<Object[]> findEventsWithDaysLeftForRegistration() {
        return eventRepository.findEventsWithDaysLeftForRegistration();
    } 
    @Override
    public List<Object[]> findStudentsWithMultipleRegistrations() {
        return eventRepository.findStudentsWithMultipleRegistrations();
    }
    @Override
    public List<Object[]> findEventWithParticipantsLessThan50(
            @Param("department") String department,
            @Param("registeredEventName") String registeredEventName
    ) {
        return eventRepository.findEventWithParticipantsLessThan50(department, registeredEventName);
    }
    @Override
    public List<Object[]>findStudentsWithRegistrationStatusNotRegistered() {
        return eventRepository.findStudentsWithRegistrationStatusNotRegistered();
    }
    @Override
    public List<Object[]> findStudentsRegisteredForEvent(String eventName) {
        return eventRepository.findStudentsRegisteredForEvent(eventName);
    }  
    @Override
    public List<Object[]> findStudentsRegisteredBeforeDeadline() {
        return eventRepository.findStudentsRegisteredBeforeDeadline();
    }
    @Override
    public List<Object[]> findEventTicketsAvailability() {
        return eventRepository.findEventTicketsAvailability();
    }
    @Override
    public List<Object[]> findEventsByDateRange(String startDate, String endDate) {
        return eventRepository.findEventsByDateRange(startDate, endDate);
    }
    @Override
    public List<Object[]> findStudentsByRegistrationDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate) {
        return eventRepository.findStudentsByRegistrationDateRange(startDate, endDate);
    }
    @Override
    public List<Object[]>findEventNamesWithTicketPrices() {
        return eventRepository.findEventNamesWithTicketPrices();
    }
}