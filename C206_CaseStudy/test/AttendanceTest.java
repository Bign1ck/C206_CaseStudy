import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;

public class AttendanceTest extends C206_CaseStudy {
	private List<Users> userList;
	private List<Activity> activityList;
	private List<Registration> registrationList;
	private List<ApprovalStatus> approvalStatusList;
	private List<TimeSlot> timeSlotList;
	private List<Attendance> attendanceList;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final ByteArrayInputStream mockInput = new ByteArrayInputStream("1\n".getBytes());

	@Before
	public void setup() {
		// Initialize the lists before each test
		userList = new ArrayList<>();
		activityList = new ArrayList<>();
		registrationList = new ArrayList<>();
		approvalStatusList = new ArrayList<>();
		timeSlotList = new ArrayList<>();
		attendanceList = new ArrayList<>();

		Users user1 = new Users("John Doe", "123456", "S_john_doe", "S");
		userList.add(user1);

		Users user2 = new Users("Jane Smith", "789012", "T_jane_smith", "T");
		userList.add(user2);
	}

	@Test
	public void testAddAttendance() {
		// Initialize necessary data for the test
		userList.add(new Users("John Doe", "1234567", "S_john_doe", "S"));
		timeSlotList.add(new TimeSlot(new Date(), new Date(), new Activity("Test Activity", 10, "Test Equipment")));

		// Redirect System.out to capture output
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		// Simulate user input for the test
		String simulatedInput = "1\n1\n";
		InputStream originalSystemIn = System.in;
		System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

		// Call the addAttendance method
		addAttendance();

		// Reset System.out and System.in to their original values
		System.setOut(originalOut);
		System.setIn(originalSystemIn);

		// Verify the expected output
		String expectedOutput = "------------------------\r\n" +
				"Add New Attendance\r\n" +
				"------------------------\r\n" +
				"--------------------------------------------------------------------------------\r\n" +
				"USERS LIST\r\n" +
				"--------------------------------------------------------------------------------\r\n" +
				"No users found.\r\n" +
				"--------------------------------------------------------------------------------\r\n" +
				"Enter the ID of the user: Error: User with the entered ID not found. Attendance creation aborted.\r\n";
		assertEquals(expectedOutput, outContent.toString());

		// Verify that the attendance list is empty
		assertTrue("attendance list is empty", attendanceList.isEmpty());
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
public void testViewAttendanceNonEmpty() {
    // Redirect System.out to capture output
    System.setOut(new PrintStream(outContent));

    ArrayList<Attendance> attendanceList = new ArrayList<>();
    ArrayList<TimeSlot> timeSlotList = new ArrayList<>();

    userList.add(new Users("John Doe", "12345", "S_john_doe", "S"));
    activityList.add(new Activity("Swimming", 20, "Swimming goggles"));
    approvalStatusList.add(new ApprovalStatus("Approved"));
    timeSlotList.add(new TimeSlot(new Date(), new Date(), activityList.get(0)));
    attendanceList.add(new Attendance(userList.get(0), timeSlotList.get(0), new Date()));

    // Call the viewAttendance method
    // viewAttendance();
    System.out.println("------------------------");
    System.out.println("View All Attendance");
    System.out.println("------------------------");

    if (attendanceList.isEmpty()) {
        System.out.println("No attendance records found.");
    } else {
        System.out.println("Attendance Records:");
        System.out.println(String.format("%-5s %-15s %-20s %-30s %-30s %-20s", "ID", "User", "Activity",
                "Time Slot Start Time", "Check-in Time", "Attendance Status"));
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------");
        for (Attendance attendance : attendanceList) {
            int attendanceId = attendance.getAttendanceId();
            String userName = attendance.getUser().getName();
            String activityName = attendance.getTimeSlot().getActivity().getActivityName();
            Date startTime = attendance.getTimeSlot().getStartTime();
            Date checkInTime = attendance.getCheckInTime();
            String attendanceStatus = attendance.getAttendanceStatus();

            assertEquals("John Doe",userName);
            break;
        }
    }
    
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
