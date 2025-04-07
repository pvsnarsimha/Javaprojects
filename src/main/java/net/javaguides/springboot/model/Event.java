package net.javaguides.springboot.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "KLU_Events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "EventName")
    private String eventName;

    @Column(name = "Date")
    private String date;

    @Column(name = "Location")
    private String location;

    @Column(name = "EventType")
    private String eventType;

    @Column(name = "Organizer")
    private String organizer;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "TicketsAvailable")
    private int ticketsAvailable;

    @Column(name = "TicketPrice")
    private BigDecimal ticketPrice;

    @Column(name = "RegistrationDeadline")
    private String registrationDeadline;

    @Column(name = "Status")
    private String status; // Adding the status field
    
    @Column(name = "Threshold")
    private int threshold; // Adding the threshold field


    public Event() {
        // Default constructor
    }
    public Event(String eventName, String date, String location, String eventType, String organizer, String description, int ticketsAvailable, BigDecimal ticketPrice, String registrationDeadline, String status, int threshold) {
        this.eventName = eventName;
        this.date = date;
        this.location = location;
        this.eventType = eventType;
        this.organizer = organizer;
        this.description = description;
        this.ticketsAvailable = ticketsAvailable;
        this.ticketPrice = ticketPrice;
        this.registrationDeadline = registrationDeadline;
        this.status = status;
        this.threshold = threshold;
    }


    // Getters and setters for the fields including the new "status" field

    public int getThreshold() {
		return threshold;
	}
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTicketsAvailable() {
        return ticketsAvailable;
    }

    public void setTicketsAvailable(int ticketsAvailable) {
        this.ticketsAvailable = ticketsAvailable;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getRegistrationDeadline() {
        return registrationDeadline;
    }

    public void setRegistrationDeadline(String registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
   
    
public boolean bookTickets(int numberOfTickets) {
    if (numberOfTickets > 0 && numberOfTickets <= ticketsAvailable) {
        ticketsAvailable -= numberOfTickets;
        return true; // Booking successful
    } else {
        return false; // Booking failed due to insufficient tickets or invalid number
    }
}
}