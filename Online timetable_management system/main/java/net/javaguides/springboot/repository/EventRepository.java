package net.javaguides.springboot.repository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import net.javaguides.springboot.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
	 @Lazy
	    @Autowired
	    ApplicationContext context = null;

	 @Query(value = "SELECT s.studentID, s.firstName, s.lastName, s.department, e.event_Name, e.date " +
	            "FROM student s " +
	            "LEFT JOIN KLU_events e ON s.registeredEventName = e.Event_Name " +
	            "WHERE s.department = 'EEE' AND e.Event_Name = 'Art Exhibition 2023' order by studentid asc", nativeQuery = true)
	    List<Object[]> findEventForCSEDepartment();
	    @Query(value = "SELECT COUNT(*) FROM klu_events WHERE date = :eventDate", nativeQuery = true)
	    long countEventsOnDate(@Param("eventDate") String eventDate);
	    @Query("SELECT s.studentId, s.firstName, s.lastName,s.registeredEventName FROM Student s " +
	    	       "WHERE s.department = :department AND s.registeredEventName = :registeredEventName")
	    	List<Object[]> findStudentsByDepartmentAndEventName(
	    	    @Param("department") String department,
	    	    @Param("registeredEventName") String registeredEventName);
	    	    @Query("SELECT COUNT(s) FROM Student s " +
	    	    	       "WHERE s.department = :department AND s.registeredEventName = :registeredEventName")
	    	    	Long countStudentsByDepartmentAndEventName(
	    	    	    @Param("department") String department,
	    	    	    @Param("registeredEventName") String registeredEventName);
	    	    @Query(value = "SELECT event_name,Registration_Deadline FROM KLU_events WHERE date = :eventDate", nativeQuery = true)
	    	    List<Object[]> findEventNamesAndDeadlinesByDate(@Param("eventDate") String eventDate);
	    	    @Query(value = "SELECT e.event_name, COUNT(s.student_ID) AS Count_students " +
	    	            "FROM students s " +
	    	            "LEFT JOIN KLU_events e ON s.registered_Event_Name = e.Event_Name " +
	    	            "WHERE s.department = :department " +
	    	            "GROUP BY e.event_name " +
	    	            "ORDER BY Count_students DESC", nativeQuery = true)
	    	    List<Object[]> findEventNameWithStudentCountByDepartment(@Param("department") String department);
	    	    @Query(value = "WITH DepartmentCounts AS (" +
	    	            "    SELECT department, COUNT(student_id) AS student_count " +
	    	            "    FROM Students " +
	    	            "    GROUP BY department" +
	    	            ")" +
	    	            "SELECT department, MAX(student_count) AS max_students " +
	    	            "FROM DepartmentCounts " +
	    	            "GROUP BY department " +
	    	            "ORDER BY max_students DESC " +
	    	            "LIMIT 1", nativeQuery = true)
	    	    List<Object[]> findDepartmentWithMaxStudents();

	    	    @Query(value = "SELECT subquery.event_Name, subquery.maxStudents " +
	    	    	    "FROM (" +
	    	    	    "    SELECT e.event_Name, COUNT(s.student_Id) AS maxStudents " +
	    	    	    "    FROM Klu_Events e " +
	    	    	    "    JOIN Students s ON e.event_Name = s.registered_Event_Name " +
	    	    	    "    GROUP BY e.event_Name " +
	    	    	    "    ORDER BY maxStudents DESC " +
	    	    	    "    LIMIT 1" +
	    	    	    ") subquery", nativeQuery = true)
	    	    	List<Object[]> findEventWithMaxStudentsSubquery();
	    	    	 @Query(value = "SELECT e.department, MAX(No_of_participated_events) AS Max_Participants " +
	    	    	            "FROM (" +
	    	    	            "    SELECT department, COUNT(student_ID) AS No_of_participated_events " +
	    	    	            "    FROM Students s " +
	    	    	            "    GROUP BY s.department" +
	    	    	            ") AS e " +
	    	    	            "GROUP BY e.department", nativeQuery = true)
	    	    	    List<Object[]> findDepartmentWithMaxParticipants();
	    	    	    @Query(value = "SELECT COUNT(*) FROM KLU_events WHERE status = :eventStatus", nativeQuery = true)
	    	    	    long countEventsByStatus(@Param("eventStatus") String eventStatus);
	    	    	    @Query(value = "SELECT s.first_name, s.last_name, s.registered_event_name FROM students s WHERE s.registered_date = :registeredDate AND s.registered_event_name = :registeredEventName", nativeQuery = true)
	    	    	    List<Object[]> findStudentsByRegisteredDateAndEventName(@Param("registeredDate") String registeredDate, @Param("registeredEventName") String registeredEventName);
	    	    // Other repository methods...
	    	    	    @Modifying
	    	    	    @Query("UPDATE Event e SET e.ticketsAvailable = e.ticketsAvailable - :numberOfTickets WHERE e.id = :eventId AND e.ticketsAvailable >= :numberOfTickets")
	    	    	    int bookTickets(@Param("eventId") int eventId, @Param("numberOfTickets") int numberOfTickets);
	    	    	    @Query(value = "SELECT e.event_name, e.date,e.registration_deadline,DATEDIFF(e.registration_deadline, CURRENT_DATE) AS daysLeft " +
	    	    	            "FROM klu_events e " +
	    	    	            "WHERE e.status = 'confirmed' " +
	    	    	            "AND e.registration_deadline > CURRENT_DATE", nativeQuery = true)
	    	    	 List<Object[]> findEventsWithDaysLeftForRegistration();
	    	    	 @Query(value = "SELECT student_id, COUNT(registered_event_name) AS registrationCount " +
	    	    		        "FROM students " +
	    	    		        "GROUP BY student_id " +
	    	    		        "HAVING registrationCount >=1", nativeQuery = true)
	    	    		List<Object[]> findStudentsWithMultipleRegistrations();
	    	    		@Query(value = "SELECT e.event_Name, COUNT(s.student_id) AS participants " +
	    	    		        "FROM KLU_events e " +
	    	    		        "JOIN students s ON e.event_Name = s.registered_Event_Name " +
	    	    		        "WHERE s.department = :department " +
	    	    		        "      AND e.event_Name = :registeredEventName " +
	    	    		        "GROUP BY e.event_Name " +
	    	    		        "HAVING COUNT(s.student_id) < 50", nativeQuery = true)
	    	    		 List<Object[]> findEventWithParticipantsLessThan50(
	    	    		            @Param("department") String department,
	    	    		            @Param("registeredEventName") String registeredEventName
	    	    		    );
	    	    		 @Query(value = "SELECT student_id, first_Name, last_Name FROM Students WHERE registration_status = 'not registered'", nativeQuery = true)
	    	    		    List<Object[]> findStudentsWithRegistrationStatusNotRegistered();
	    	    		    @Query(value = "SELECT s.student_id, s.first_name, s.last_name,s.phone_number,s.email FROM students s WHERE s.registered_event_name = :eventName", nativeQuery = true)
	    	    		    List<Object[]> findStudentsRegisteredForEvent(@Param("eventName") String eventName);
	    	    		    @Query(value = "SELECT s.student_id, s.first_name, s.last_name, s.registered_date, s.registered_event_name,e.date,e.registration_deadline " +
	    	    		            "FROM students s " +
	    	    		            "JOIN klu_events e ON s.registered_Event_Name = e.Event_Name " +
	    	    		            "WHERE s.registered_date < e.registration_deadline", nativeQuery = true)
	    	    		    List<Object[]> findStudentsRegisteredBeforeDeadline();
	    	    		    @Query(value = "SELECT e.id,e.event_name, e.tickets_available,e.threshold, " +
	    	    		            "CASE " +
	    	    		            "   WHEN e.tickets_available > 0 AND e.tickets_available < e.threshold THEN 'Low' " +
	    	    		            "   WHEN e.tickets_available >= e.threshold THEN 'High' " +
	    	    		            "   WHEN e.tickets_available = 0 THEN 'Sold Out' " +
	    	    		            "END AS ticketAvailability " +
	    	    		            "FROM KLU_Events e " +
	    	    		            "WHERE e.tickets_available >= 0", nativeQuery = true)
	    	    		    List<Object[]> findEventTicketsAvailability();
	    	    		    @Query(value = "SELECT id, event_name, date FROM klu_events WHERE date BETWEEN :startDate AND :endDate", nativeQuery = true)
	    	    		    List<Object[]> findEventsByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);
	    	    		    @Query(value = "SELECT s.first_name, s.last_name, s.registered_event_name FROM Students s WHERE s.registered_date >= :startDate AND s.registered_date <= :endDate", nativeQuery = true)
	    	    		    List<Object[]> findStudentsByRegistrationDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);
	    	    		    @Query(value = "SELECT e.eventName, COALESCE(e.ticketPrice, 'Free') AS ticketPrice FROM Event e")
	    	    		    List<Object[]> findEventNamesWithTicketPrices();

	    	    		    

}