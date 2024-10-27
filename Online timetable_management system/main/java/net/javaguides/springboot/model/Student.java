package net.javaguides.springboot.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name ="Students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String firstName;
    private String lastName;

    // Change dateOfBirth and registeredDate to String fields
    private String dateOfBirth;
    private String registeredDate;

    private String address;
    private String email;
    private String phoneNumber;
    private String registeredEventName;
    private String department;

    // Additional fields
    private BigDecimal registrationFee;
    private String registrationStatus;
    private BigDecimal feePaid;
    private String participation;
    // Constructors, getters, and setters

    public Student() {
    }

    public Student(String firstName, String lastName, String dateOfBirth, String address, String email, String phoneNumber, String registeredEventName, String department,
                   String registeredDate, BigDecimal registrationFee, String registrationStatus,BigDecimal feePaid, String participation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.registeredEventName = registeredEventName;
        this.department = department;
        this.registeredDate = registeredDate;
        this.registrationFee = registrationFee;
        this.registrationStatus = registrationStatus;
        this.feePaid = feePaid;
        this.participation = participation;
    }

    // Getters and setters for each field

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRegisteredEventName() {
        return registeredEventName;
    }

    public void setRegisteredEventName(String registeredEventName) {
        this.registeredEventName = registeredEventName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public BigDecimal getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(BigDecimal registrationFee) {
        this.registrationFee = registrationFee;
    }

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public BigDecimal getFeePaid() {
        return feePaid;
    }

    public void setFeePaid(BigDecimal feePaid) {
        this.feePaid = feePaid;
    }
    public String getParticipation() {
        return participation;
    }

    public void setParticipation(String participation) {
        this.participation = participation;
    }
    // Custom methods to handle date formatting and parsing
    public Date getDateOfBirthAsDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(this.dateOfBirth);
    }

    public void setDateOfBirthAsDate(Date dateOfBirth) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.dateOfBirth = sdf.format(dateOfBirth);
    }

    public Date getRegisteredDateAsDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(this.registeredDate);
    }

    public void setRegisteredDateAsDate(Date registeredDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.registeredDate = sdf.format(registeredDate);
    }
}