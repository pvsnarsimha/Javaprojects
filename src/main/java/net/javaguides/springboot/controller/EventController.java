package net.javaguides.springboot.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import net.javaguides.springboot.model.Event;
import net.javaguides.springboot.service.EventService;


@Controller
public class EventController {
	
	
    @Autowired
    private EventService eventService;
    @GetMapping("/")
    public String viewEventList(Model model) {
        return findPaginated(1, model);
    }
    @GetMapping("/showNewEventForm")
    public String showNewEventForm(Model model) {
        Event event = new Event();
        model.addAttribute("event", event);
        return "addEvent";
    }

    @PostMapping("/saveEvent")
    public String saveEvent(@ModelAttribute("event") Event event) {
        eventService.saveEvent(event);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") int id, Model model) {
        Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        return "editEvent";
    }

    @GetMapping("/deleteEvent/{id}")
    public String deleteEvent(@PathVariable(value = "id") int id) {
        eventService.deleteEventById(id);
        return "redirect:/";
    }
    @GetMapping("/cseEvents")
    public String findCSEDepartmentEvents(Model model) {
        List<Object[]> cseEvents = eventService.findEventForCSEDepartment();
        model.addAttribute("cseEvents", cseEvents);
        return "cseEvent"; // Create a view called "cseEvent" to display CSE department events.
    }
    @GetMapping("/countEventsOnDate")
    public String countEventsOnDateForm(Model model) {
        return "eventCountForm"; // Create a view for inputting the date.
    }

    @PostMapping("/countEventsOnDateResult")
    public String countEventsOnDateResult(
        @RequestParam("eventDate") String eventDate,
        Model model
    ) {
        long eventCount = eventService.countEventsOnDate(eventDate);
        model.addAttribute("eventCount", eventCount);
        model.addAttribute("eventDate", eventDate);
        return "eventCountResult"; // Create a view for displaying the count result.
    }
    @GetMapping("/DisplayStudentsByDepartmentAndEventName")
    public String countStudentsByDepartmentAndEventNameForm(Model model) {
        return "studentDisplayForm"; // Create a view for inputting the department and event name.
    }

    @PostMapping("/DisplayStudentsByDepartmentAndEventNameResult")
    public String countStudentsByDepartmentAndEventNameResult(
        @RequestParam("department") String department,
        @RequestParam("eventName") String eventName,
        Model model
    ) {
        List<Object[]> studentCount = eventService.findStudentsByDepartmentAndEventName(department, eventName);
        model.addAttribute("studentCount", studentCount);
        model.addAttribute("department", department);
        model.addAttribute("eventName", eventName);
        return "studentResult"; // Create a view for displaying the student count result.
    }
   
    @GetMapping("/countStudentsByDepartmentAndEventName")
    public String countStudentsByDepartmentAndEventNameForms(Model model) {
        return "countStudentsForm"; // Create a view for inputting the department and registered event name.
    }

    @PostMapping("/countStudentsByDepartmentAndEventNameResult")
    public String countStudentsByDepartmentAndEventNameResults(
        @RequestParam("department") String department,
        @RequestParam("registeredEventName") String registeredEventName,
        Model model
    ) {
        long studentCount = eventService.countStudentsByDepartmentAndEventName(department, registeredEventName);
        model.addAttribute("studentCount", studentCount);
        model.addAttribute("department", department);
        model.addAttribute("registeredEventName", registeredEventName);
        return "countStudentsResult"; // Create a view for displaying the student count result.
    } 
    @GetMapping("/eventNamesAndDeadlinesByDateForm")
    public String eventNamesAndDeadlinesByDateForm(Model model) {
        return "eventNamesAndDeadlinesForm"; // Create a view for inputting the date.
    }

    @PostMapping("/eventNamesAndDeadlinesByDateResult")
    public String eventNamesAndDeadlinesByDateResult(
        @RequestParam("eventDate") String eventDate,
        Model model
    ) {
        List<Object[]> eventNamesAndDeadlines = eventService.findEventNamesAndDeadlinesByDate(eventDate);
        model.addAttribute("eventNamesAndDeadlines", eventNamesAndDeadlines);
        model.addAttribute("eventDate", eventDate);
        return "eventNamesAndDeadlinesResult"; // Create a view for displaying the result.
    }
    @GetMapping("/findEventNameWithStudentCountByDepartment")
    public String findEventNameWithStudentCountByDepartmentForm(Model model) {
    	return "eventNameWithStudentCountForm"; // Create a view for inputting the department.
    }

    @PostMapping("/findEventNameWithStudentCountByDepartmentResult")
    public String findEventNameWithStudentCountByDepartmentResult(
        @RequestParam("department") String department,
        Model model
    ) {
        List<Object[]> eventCounts = eventService.findEventNameWithStudentCountByDepartment(department);
        model.addAttribute("eventCounts", eventCounts);
        model.addAttribute("department", department);
        model.addAttribute("studentsCount", 42);
        return "eventNameWithStudentCountResult"; // Create a view for displaying the result.
    }
    @GetMapping("/findDepartmentWithMaxStudents")
    public String findDepartmentWithMaxStudents(Model model) {
        List<Object[]> departmentWithMaxStudents = eventService.findDepartmentWithMaxStudents();
        model.addAttribute("departmentWithMaxStudents", departmentWithMaxStudents);
        return "departmentWithMaxStudents"; // Create a view for displaying the result.
    }
    @GetMapping("/findEventWithMaxStudentsSubquery")
    public String findEventWithMaxStudentsSubquery(Model model) {
        List<Object[]> eventWithMaxStudentsSubquery = eventService.findEventWithMaxStudentsSubquery();
        model.addAttribute("eventWithMaxStudentsSubquery", eventWithMaxStudentsSubquery);
        return "eventWithMaxStudentsSubquery"; // Create a view for displaying the result.
    }
    @GetMapping("/findDepartmentWithMaxParticipants")
    public String findDepartmentWithMaxParticipants(Model model) {
        List<Object[]> departmentWithMaxParticipants = eventService.findDepartmentWithMaxParticipants();
        model.addAttribute("departmentWithMaxParticipants", departmentWithMaxParticipants);
        return "departmentWithMaxParticipants"; // Create a view for displaying the result.
    }
    @GetMapping("/countEventsByStatusForm")
    public String countEventsByStatusForm(Model model) {
        return "countEventsByStatusForm"; // Create a view for inputting the status.
    }

    @PostMapping("/countEventsByStatusResult")
    public String countEventsByStatusResult(
        @RequestParam("eventStatus") String eventStatus,
        Model model
    ) {
        long eventCount = eventService.countEventsByStatus(eventStatus);
        model.addAttribute("eventCount", eventCount);
        model.addAttribute("eventStatus", eventStatus);
        return "countEventsByStatusResult"; // Create a view for displaying the count result.
    } 
    @GetMapping("/findStudentsByRegisteredDateAndEventNameForm")
    public String findStudentsByRegisteredDateAndEventNameForm(Model model) {
        return "studentsByRegisteredDateAndEventNameForm"; // Create a view for inputting the registered date and event name.
    }

    @PostMapping("/findStudentsByRegisteredDateAndEventNameResult")
    public String findStudentsByRegisteredDateAndEventNameResult(
            @RequestParam("registeredDate") String registeredDate,
            @RequestParam("registeredEventName") String registeredEventName,
            Model model
    ) {
        List<Object[]> studentsList = eventService.findStudentsByRegisteredDateAndEventName(registeredDate, registeredEventName);
        model.addAttribute("studentsList", studentsList);
        model.addAttribute("registeredDate", registeredDate);
        model.addAttribute("registeredEventName", registeredEventName);
        return "studentsByRegisteredDateAndEventNameResult"; // Create a view for displaying the result.
    }
    @GetMapping("/exit")
    public String exitPage() {
        // Perform any necessary actions before exiting
        // You can add a confirmation message or cleanup logic here

        // Redirect to an external page (e.g., Google's homepage)
        return "redirect:https://www.google.com";
    } 
    @GetMapping("/bookTicketsForm/{eventId}")
    public String showBookTicketsForm(@PathVariable(value = "eventId") int eventId, Model model) {
        model.addAttribute("eventId", eventId);
        return "bookTicketsForm"; // Create a view for inputting the number of tickets to book.
    }

    @PostMapping("/bookTickets/{eventId}")
    public String bookTickets(
            @PathVariable(value = "eventId") int eventId,
            @RequestParam("numberOfTickets") int numberOfTickets,
            Model model
    ) {
        boolean bookingResult = eventService.bookTickets(eventId, numberOfTickets);
        model.addAttribute("bookingResult", bookingResult);
        return "bookTicketsResult"; // Create a view for displaying the booking result.
    }
    @GetMapping("/findEventsWithDaysLeftForRegistration")
    public String findEventsWithDaysLeftForRegistration(Model model) {
        List<Object[]> eventsWithDaysLeft = eventService.findEventsWithDaysLeftForRegistration();
        model.addAttribute("eventsWithDaysLeft", eventsWithDaysLeft);
        return "eventsWithDaysLeft"; // Create a view for displaying events with days left for registration.
    }
    @GetMapping("/findStudentsWithMultipleRegistrations")
    public String findStudentsWithMultipleRegistrations(Model model) {
        List<Object[]> studentsWithMultipleRegistrations = eventService.findStudentsWithMultipleRegistrations();
        model.addAttribute("studentsWithMultipleRegistrations", studentsWithMultipleRegistrations);
        return "studentsWithMultipleRegistrations"; // Create a view for displaying the result.
    }
    @GetMapping("/findEventWithParticipantsLessThan50Form")
    public String findEventWithParticipantsLessThan50Form(Model model) {
        return "eventWithParticipantsLessThan50Form"; // Create a view for inputting department and registered event name.
    }

    @PostMapping("/findEventWithParticipantsLessThan50Result")
    public String findEventWithParticipantsLessThan50Result(
            @RequestParam("department") String department,
            @RequestParam("registeredEventName") String registeredEventName,
            Model model
    ) {
        List<Object[]> eventsWithParticipantsLessThan50 = eventService.findEventWithParticipantsLessThan50(department, registeredEventName);
        model.addAttribute("eventsWithParticipantsLessThan50", eventsWithParticipantsLessThan50);
        model.addAttribute("department", department);
        model.addAttribute("registeredEventName", registeredEventName);
        return "eventWithParticipantsLessThan50Result"; // Create a view for displaying the result.
    }
    @GetMapping("/studentsWithRegistrationStatusNotRegistered")
    public String showStudentsWithRegistrationStatusNotRegistered(Model model) {
        List<Object[]> notRegisteredStudents = eventService.findStudentsWithRegistrationStatusNotRegistered();
        model.addAttribute("notRegisteredStudents", notRegisteredStudents);
        return "studentsWithRegistrationStatusNotRegistered";
    }
    @GetMapping("/findStudentsRegisteredForEventForm")
    public String findStudentsRegisteredForEventForm(Model model) {
        return "studentsRegisteredForEventForm"; // Create a view for inputting the event name.
    }

    @PostMapping("/findStudentsRegisteredForEventResult")
    public String findStudentsRegisteredForEventResult(
            @RequestParam("eventName") String eventName,
            Model model
    ) {
        List<Object[]> registeredStudents = eventService.findStudentsRegisteredForEvent(eventName);
        model.addAttribute("registeredStudents", registeredStudents);
        model.addAttribute("eventName", eventName);
        return "studentsRegisteredForEventResult"; // Create a view for displaying the result.
    }
    @GetMapping("/findStudentsRegisteredBeforeDeadline")
    public String findStudentsRegisteredBeforeDeadline(Model model) {
        List<Object[]> studentsBeforeDeadline = eventService.findStudentsRegisteredBeforeDeadline();
        model.addAttribute("studentsBeforeDeadline", studentsBeforeDeadline);
        return "studentsBeforeDeadline"; // Create a view for displaying the result.
    }
    @GetMapping("/findEventTicketsAvailability")
    public String findEventTicketsAvailability(Model model) {
        List<Object[]> eventTicketsAvailability = eventService.findEventTicketsAvailability();
        model.addAttribute("eventTicketsAvailability", eventTicketsAvailability);
        return "eventTicketsAvailability"; // Create a view for displaying event tickets availability.
    }
    @GetMapping("/findEventsByDateRangeForm")
    public String findEventsByDateRangeForm(Model model) {
        return "findEventsByDateRangeForm"; // Create a view for inputting the date range.
    }

    @PostMapping("/findEventsByDateRangeResult")
    public String findEventsByDateRangeResult(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            Model model
    ) {
        List<Object[]> events = eventService.findEventsByDateRange(startDate, endDate);
        model.addAttribute("events", events);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        return "findEventsByDateRangeResult"; // Create a view for displaying the result.
    }
    @GetMapping("/findStudentsByRegistrationDateRangeForm")
    public String findStudentsByRegistrationDateRangeForm(Model model) {
        return "studentsByRegistrationDateRangeForm"; // Create a view for inputting the date range.
    }

    @PostMapping("/findStudentsByRegistrationDateRangeResult")
    public String findStudentsByRegistrationDateRangeResult(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            Model model
    ) {
        List<Object[]> studentsByRegistrationDateRange = eventService.findStudentsByRegistrationDateRange(startDate, endDate);
        model.addAttribute("studentsByRegistrationDateRange", studentsByRegistrationDateRange);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        return "studentsByRegistrationDateRangeResult"; // Create a view for displaying the result.
    }
    @GetMapping("/event-names-with-ticket-prices")
    public String findEventNamesWithTicketPrices(Model model) {
        List<Object[]> eventNamesWithTicketPrices = eventService.findEventNamesWithTicketPrices();
        model.addAttribute("eventNamesWithTicketPrices", eventNamesWithTicketPrices);
        return "eventNamesWithTicketPrices"; // Create a view for displaying event names with ticket prices.
    }
    
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;
        Page<Event> page = eventService.findPaginated(pageNo, pageSize);
        List<Event> listEvents = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("listEvents", listEvents);
        return "index";
    }
}
