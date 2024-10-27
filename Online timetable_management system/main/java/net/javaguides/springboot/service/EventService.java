package net.javaguides.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import net.javaguides.springboot.model.Event;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface EventService {
	


    Page<Event> findPaginated(int pageNo, int pageSize);
    void saveEvent(Event event);
    Event getEventById(int id);
    void deleteEventById(int id);
    List<Event> getAllEvents();
    List<Object[]> findEventForCSEDepartment();
    long countEventsOnDate(String eventDate);
    List<Object[]> findStudentsByDepartmentAndEventName(
            @Param("department") String department,
            @Param("registeredEventName") String registeredEventName
        );
    long countStudentsByDepartmentAndEventName(String department, String registeredEventName);
    List<Object[]> findEventNamesAndDeadlinesByDate(@Param("eventDate") String eventDate);
    List<Object[]> findEventNameWithStudentCountByDepartment(@Param("department") String department);
    List<Object[]> findDepartmentWithMaxStudents();
    List<Object[]> findEventWithMaxStudentsSubquery();
    List<Object[]> findDepartmentWithMaxParticipants();
    long countEventsByStatus(String eventStatus);
    List<Object[]> findStudentsByRegisteredDateAndEventName(String registeredDate, String registeredEventName);
    boolean bookTickets(int eventId, int numberOfTickets);
    List<Object[]> findEventsWithDaysLeftForRegistration();
    List<Object[]> findStudentsWithMultipleRegistrations();
    List<Object[]> findEventWithParticipantsLessThan50(
            @Param("department") String department,
            @Param("registeredEventName") String registeredEventName
    );
    List<Object[]> findStudentsWithRegistrationStatusNotRegistered();
    List<Object[]> findStudentsRegisteredForEvent(@Param("eventName") String eventName);
    List<Object[]> findStudentsRegisteredBeforeDeadline();
    List<Object[]> findEventTicketsAvailability();
    List<Object[]> findEventsByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);
    List<Object[]> findStudentsByRegistrationDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);
    List<Object[]> findEventNamesWithTicketPrices();
    }