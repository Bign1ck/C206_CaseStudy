import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;    
    
public class AttendanceTest extends C206_CaseStudy{
  private List<Users> userList;
    private List<Activity> activityList;
    private List<Registration> registrationList;
    private List<ApprovalStatus> approvalStatusList;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final ByteArrayInputStream mockInput = new ByteArrayInputStream("1\n".getBytes());
    
    @Before
    public void setup(){
                // Initialize the lists before each test
                userList = new ArrayList<>();
                activityList = new ArrayList<>();
                registrationList = new ArrayList<>();
                approvalStatusList = new ArrayList<>();
        
                Users user1 = new Users("John Doe", "123456", "S_john_doe", "S");
                userList.add(user1);
        
                Users user2 = new Users("Jane Smith", "789012", "T_jane_smith", "T");
                userList.add(user2);
    }
        
    @Test
    public void testAddAttendance() {
        int input_userID = 12345;
        int input_timeSlotID = 1;

       ArrayList<Attendance> attendanceList = new ArrayList<>();

        
        
       System.out.println("------------------------");
		System.out.println("Add New Attendance");
		System.out.println("------------------------");

		// Get user details
		viewUsers();
		Integer userId = input_userID;
		Users selectedUser = getUserById(userId);
		if (selectedUser == null) {
			System.out.println("Error: User with the entered ID not found. Attendance creation aborted.");
			return;
		}

		// Check if there are any time slots available
		if (timeSlotList.isEmpty()) {
			System.out.println("Error: No time slots found. Attendance creation aborted.");
			return;
		}

		// Get time slot details
		viewTimeSlots();
		int timeSlotId = Helper.readInt("Enter the ID of the time slot: ");
		TimeSlot selectedTimeSlot = getTimeSlotById(timeSlotId);
		if (selectedTimeSlot == null) {
			System.out.println("Error: Time slot with the entered ID not found. Attendance creation aborted.");
			return;
		}

		// Set check-in time to the current time
		Date checkInTime = new Date();

		// Check if an attendance already exists for the same user and time slot
		Attendance existingAttendance = null;
		for (Attendance attendance : attendanceList) {
			if (attendance.getUser().equals(selectedUser) && attendance.getTimeSlot().equals(selectedTimeSlot)) {
				existingAttendance = attendance;
				break;
			}
		}

		// If existing attendance is found, update the check-in time and status
		if (existingAttendance != null) {
			existingAttendance.setCheckInTime(checkInTime);
			existingAttendance.setAttendanceStatus(existingAttendance.calculateAttendanceStatus());
			System.out.println("Attendance updated successfully!");
		} else {
			// Create a new Attendance object
			Attendance newAttendance = new Attendance(selectedUser, selectedTimeSlot, checkInTime);

			// Add the new attendance record to the attendanceList
			attendanceList.add(newAttendance);
			System.out.println("Attendance added successfully!");

        assertEquals(input_checkInTime, input_timeSlotID, input_userID, input_timeSlotID);
    }
}

@Test
public void testViewAttendanceEmpty() {
    // Redirect System.out to capture output
    System.setOut(new PrintStream(outContent));

    // Call the viewAttendance method
    viewAttendance();

    String expected = "------------------------" + System.lineSeparator() +
            "View All Attendance" + System.lineSeparator() +
            "------------------------" + System.lineSeparator() +
            "No attendance records found." + System.lineSeparator();
    // Verify the expected output
    assertEquals(expected, outContent.toString());
}


@Test
public void testDeleteAttendance() {
    ArrayList<Users> userList = new ArrayList<>();
    ArrayList<TimeSlot> timeSlotList = new ArrayList<>();
    ArrayList<Activity> activityList = new ArrayList<>(); // Populate this list with actual activities

    activityList.add(new Activity("Swimming", 20, "Swimming goggles"));
    userList.add(new Users("Jane Smith", "98765", "T_jane_smith", "T"));
    timeSlotList.add(new TimeSlot(new Date(), new Date(), activityList.get(0)));
    ArrayList<Attendance> attendanceList = new ArrayList<>();
    attendanceList.add(new Attendance(userList.get(0), timeSlotList.get(0), new Date()));

    int IdToDelete = 1;
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    System.setOut(new PrintStream(output));

    // Check if the entered ID is valid
		if (IdToDelete <= 0 || IdToDelete > attendanceList.size()) {
			System.out.println("Invalid attendance record ID.");
			return;
		}

		// Remove the attendance record from the list and inform the user
		attendanceList.remove(IdToDelete - 1);
		System.out.println("Attendance record with ID " + IdToDelete + " has been deleted.");

    String expected = "Attendance record with ID 1 has been deleted.";
    assertEquals(expected, output.toString().trim());
	}
}
    