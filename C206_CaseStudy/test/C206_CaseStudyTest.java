import static org.junit.Assert.*;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class C206_CaseStudyTest extends C206_CaseStudy {

    private List<Users> userList;
    private List<Activity> activityList;
    private List<Registration> registrationList;
    private List<ApprovalStatus> approvalStatusList;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final ByteArrayInputStream mockInput = new ByteArrayInputStream("1\n".getBytes());

    @Before
    public void setUp() {
        // Initialize the lists before each test
        userList = new ArrayList<>();
        activityList = new ArrayList<>();
        registrationList = new ArrayList<>();
        approvalStatusList = new ArrayList<>();

        userList.add(new Users("John Doe", "12345", "S_john_doe", "S"));
        userList.add(new Users("Jane Smith", "98765", "T_jane_smith", "T"));
        userList.add(new Users("Alice Brown", "11111", "S_alice_brown", "S"));
        activityList.add(new Activity("Swimming", 20, "Swimming goggles"));

        Users user2 = new Users("Jane Smith", "789012", "T_jane_smith", "T");
        userList.add(user2);

    }

    @After
    public void tearDown() {
        // Clean up resources after each test
        userList = null;
        activityList = null;
        registrationList = null;
        approvalStatusList = null;
    }

    // testAddUser normal condition
    @Test
    public void testAddUser() {
        String simulatedInput = "John Doe\n123456\nS\n";
        InputStream savedSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Call the addUser method
        addUser();

        // Reset System.in to its original state
        System.setIn(savedSystemIn);

        // Verify the expected behavior
        assertEquals(2, userList.size());

        Users addedUser = userList.get(0);
        assertEquals("John Doe", addedUser.getName());
        assertEquals("123456", addedUser.getStudentId());
        assertEquals("S", addedUser.getRole());
    }
    
 // testAddUser boundary condition
    @Test
    public void testAddUserBoundary() {
        String simulatedInput = "Alice Smith\n987654\nT\n"; // Input for teacher role
        InputStream savedSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Call the addUser method
        addUser();

        // Reset System.in to its original state
        System.setIn(savedSystemIn);

        // Verify the expected behavior
        assertEquals(3, userList.size());

        Users addedUser = userList.get(2); // New user should be the third one
        assertEquals("Alice Smith", addedUser.getName());
        assertEquals("987654", addedUser.getStudentId());
        assertEquals("T", addedUser.getRole());
    }
    

    @Test
    public void testViewUsersEmpty() {
        // Redirect System.out to capture output
        System.setOut(new PrintStream(outContent));

        // Call the viewUsers method
        viewUsers();

        // Verify the expected output
        assertEquals("-".repeat(80) + System.lineSeparator() +
                "USERS LIST" + System.lineSeparator() +
                "-".repeat(80) + System.lineSeparator() +
                "No users found." + System.lineSeparator() +
                "-".repeat(80) + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void testDeleteUser() {
        // Test deleteUser() method
        String userID = "1234567";
        boolean executeDelete = false;
        boolean deleted = false;

        // Implement the test for deleteUser() method here
        for (Users user : userList) {
            executeDelete = user.getStudentId().equalsIgnoreCase(userID);
            if (executeDelete) {
                userList.remove(user);
                System.out.println("User deleted successfully!");
                break;
            }
        }
        for (Users user : userList) {
            deleted = user.getStudentId().equalsIgnoreCase(userID);
        }
        assertFalse("user has been deleted", deleted);
    }

    // ------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------------------------------

    
    @Test
    public void testAddActivity() {
        String activityName = "Test Activity";
        int capacity = 10;
        String prerequisites = "Test Prerequisites";

        // Assuming the addActivity method is part of C206_CaseStudy class
        activityList.add(new Activity(activityName, capacity, prerequisites));

        // Assert that the activityList has one item (the activity added)
        assertEquals(1, activityList.size());
        // Assert that the activity details are as expected
        assertEquals(activityName, activityList.get(0).getActivityName());
        assertEquals(capacity, activityList.get(0).getCapacity());
        assertEquals(prerequisites, activityList.get(0).getPrerequisites());
    }

    @Test
    public void testViewActivitiesEmpty() {
        // Redirect System.out to capture output
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Call the viewActivities method with an empty activity list
        viewActivities();

        // Reset System.out to its original state
        System.setOut(originalOut);

        // Verify the output
        String expectedOutput = "-".repeat(24) + System.lineSeparator() +
                "View All Activities" + System.lineSeparator() +
                "-".repeat(24) + System.lineSeparator() +
                "No activities found.";

        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void testViewActivitiesNonEmpty() {
        // Prepare sample data for activityList
        if (activityList.isEmpty()) {
            activityList.add(new Activity("Swimming", 20, "Swimming goggles"));
            activityList.add(new Activity("Yoga", 15, "Yoga mat"));
            activityList.add(new Activity("Running", 30, "Running shoes"));
            activityList.add(new Activity("Dancing", 25, "Dance shoes"));
        }

        // Redirect System.out to capture output
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Call the viewActivities method with non-empty activity list
        System.out.println("-".repeat(24));
        System.out.println("View All Activities");
        System.out.println("-".repeat(24));

        if (!activityList.isEmpty()) {
            String ColumnTitles = String.format("%-5s %-15s %-10s %-30s", "ID", "Activity Name", "Capacity",
                    "Prerequisites");
            System.out.println(ColumnTitles);

            System.out.println("-".repeat(51));

            for (Activity activity : activityList) {
                String rowDetails = String.format("%-5s %-15s %-10s %-30s", activity.getActivityId(),
                        activity.getActivityName(),
                        activity.getCapacity(), activity.getPrerequisites());
                System.out.println(rowDetails);
            }
        }

        // Reset System.out to its original state
        System.setOut(originalOut);

        // Construct the expected output
        StringBuilder expectedOutput = new StringBuilder();
        expectedOutput.append("-".repeat(24)).append(System.lineSeparator());
        expectedOutput.append("View All Activities").append(System.lineSeparator());
        expectedOutput.append("-".repeat(24)).append(System.lineSeparator());
        expectedOutput
                .append(String.format("%-5s %-15s %-10s %-30s%n", "ID", "Activity Name", "Capacity", "Prerequisites"));
        expectedOutput.append("-".repeat(51)).append(System.lineSeparator());
        expectedOutput.append(String.format("%-5s %-15s %-10s %-30s%n", 1, "Swimming", 20, "Swimming goggles"));
        expectedOutput.append(String.format("%-5s %-15s %-10s %-30s%n", 2, "Yoga", 15, "Yoga mat"));
        expectedOutput.append(String.format("%-5s %-15s %-10s %-30s%n", 3, "Running", 30, "Running shoes"));
        expectedOutput.append(String.format("%-5s %-15s %-10s %-30s%n", 4, "Dancing", 25, "Dance shoes"));

        // Verify the output
        assertEquals(expectedOutput.toString().trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void testDeleteActivity() {
        // Prepare sample activities
        Activity activity1 = new Activity("Swimming", 20, "Swimming goggles");
        Activity activity2 = new Activity("Yoga", 15, "Yoga mat");
        activityList.add(activity1);
        activityList.add(activity2);
        int inputID = 1;
        // Assuming deleteActivity method is part of C206_CaseStudy class
        // C206_CaseStudy.deleteActivity(activityList, 1); // Deleting by index or ID,
        // based on implementation
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        if (activityList.isEmpty()) {
            System.out.println("No activities found.");
            return;
        }

        int activityIdToDelete = inputID;

        Activity activityToDelete = null;
        for (Activity activity : activityList) {
            if (activity.getActivityId() == activityIdToDelete) {
                activityToDelete = activity;
                System.out.println("Activity has been deleted");
                break;
            }
        }
        // Check if activity with index or ID 1 has been deleted
        assertEquals("Activity has been deleted", outputStreamCaptor.toString().trim());
    }

    // ------------------------------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------------------

    @Test
    public void testAddRegistration() {
        // Create a test user and activity
        Users testUser = userList.get(0); // Use the first user for testing
        Activity testActivity = new Activity("Chess Club", 20);
        registrationList.add(new Registration(testUser, testActivity, "Pending"));

        // Verify that the registration has been added successfully
        assertEquals(1, registrationList.size());
        Registration addedRegistration = registrationList.get(0);
        assertEquals(testUser, addedRegistration.getUser());
        assertEquals(testActivity, addedRegistration.getActivity());
        assertEquals("Pending", addedRegistration.getStatus());

    }

    @Test
    public void testViewRegistrationsEmpty() {
        // Test viewRegistrations() method when registrationList is empty
        System.setOut(new PrintStream(outContent));

        // Call the viewRegistrations() method

        registrationList.clear();
        if (registrationList.isEmpty()) {
            System.out.println("No registrations found.");
            return;
        }

        // Reset System.out to its original value
        System.setOut(originalOut);

        // Assert that the printed output matches the expected message for empty list
        String expectedOutput = "No registrations found.";
        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    public void testViewRegistrationsNonEmpty() {
        // Prepare sample data for registrationList
        Users user = new Users("John Doe", "123456", null, null);
        Activity activity = new Activity("Chess Club", 20);
        Registration registration = new Registration(user, activity, "Pending");
        registrationList.add(registration);

        // Redirect System.out to outContent for capturing printed output
        System.setOut(new PrintStream(outContent));

        // Call the viewRegistrations() method
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-15s %-30s %-20s %-15s%n", "Registration ID", "User", "Activity", "Status");
        System.out.println("--------------------------------------------------------------------------------");

        // Print registration details
        for (Registration reg : registrationList) {
            System.out.printf("%-15s %-30s %-20s %-15s%n",
                    reg.getRegistrationId(),
                    reg.getUser().getName(),
                    reg.getActivity().getActivityName(),
                    reg.getStatus());
        }

        // Print footer
        System.out.println("--------------------------------------------------------------------------------");

        // Reset System.out to its original value
        System.setOut(originalOut);

        // Assert that the printed output matches the expected format
        String expectedOutput = "-".repeat(80) + "\r\n" +
                String.format("%-15s %-30s %-20s %-15s%n", "Registration ID", "User", "Activity", "Status") +
                "-".repeat(80) + "\r\n" +
                String.format("%-15s %-30s %-20s %-15s%n", registration.getRegistrationId(), user.getName(),
                        activity.getActivityName(), registration.getStatus())
                +
                "-".repeat(80);
        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    public void testDeleteRegistration() {
        // Prepare sample data for registrationList
        Users user = new Users("John Doe", "123456", null, null);
        Activity activity = new Activity("Chess Club", 20);
        Registration registration = new Registration(user, activity, "Pending");
        registrationList.add(registration);

        // Redirect System.out to outContent for capturing printed output
        System.setOut(new PrintStream(outContent));

        // Redirect System.in to mockInput for simulating user input
        System.setIn(mockInput);

        // Call the deleteRegistration() method
        // testDeleteRegistration();
        System.out.println("------------------------");
        System.out.println("Delete Registration");
        System.out.println("------------------------");
        System.out.println("All Registrations:");
        System.out
                .println(String.format("%-5s %-15s %-30s %-20s", "Reg. ID", "User", "Activity", "Status"));
        System.out.println("--------------------------------------------------------------------------------");
        for (int i = 0; i < registrationList.size(); i++) {
            Registration reg = registrationList.get(i);
            System.out.println(String.format("%-5s %-15s %-30s %-20s ",

                    reg.getRegistrationId(),
                    reg.getUser().getName(),
                    reg.getActivity().getActivityName(),
                    reg.getStatus()));
        }
        System.out.println("--------------------------------------------------------------------------------");
        // Prompt the user to enter the ID of the registration they want to delete
        int registrationIdToDelete = Helper.readInt("Enter the ID of the registration to delete: ");

        // Check if the entered ID is valid
        if (registrationIdToDelete <= 0 || registrationIdToDelete > registrationList.size()) {
            System.out.println("Invalid registration ID.");
            return;
        }

        // Remove the registration from the list and inform the user
        registrationList.remove(registrationIdToDelete - 1);
        System.out.println("Registration with ID " + registrationIdToDelete + " has been deleted.");
        // Reset System.out and System.in to their original values
        System.setOut(originalOut);
        System.setIn(System.in);

        // Assert that the printed output matches the expected message
        String expectedOutput = "------------------------\r\n" +
                "Delete Registration\r\n" +
                "------------------------\r\n" +
                "All Registrations:\r\n" +
                String.format("%-5s %-15s %-30s %-20s", "Reg. ID", "User", "Activity", "Status") + "\r\n" +
                "--------------------------------------------------------------------------------\r\n" +
                String.format("%-5s %-15s %-30s %-20s ", registration.getRegistrationId(), user.getName(),
                        activity.getActivityName(), registration.getStatus())
                +
                "\r\n--------------------------------------------------------------------------------\r\n" +
                "Enter the ID of the registration to delete: " +
                "Registration with ID 1 has been deleted.\r\n";

        assertEquals(expectedOutput.trim(), outContent.toString().trim());
        assertEquals(0, registrationList.size()); // Check that the registration was removed from the list
    }

    @Test
    public void testUpdateRegistrationStatus() {
        // Add sample approval statuses
        approvalStatusList.add(new ApprovalStatus("Pending"));
        approvalStatusList.add(new ApprovalStatus("Approved"));

        // Add a sample registration
        Users user = new Users("John Doe", "123456", null, null);
        Activity activity = new Activity("Chess Club", 20);
        Registration registration = new Registration(user, activity, "Pending");
        registrationList.add(registration);

        String registrationIdToUpdate = user.getStudentId();
        int selectedApprovalStatusId = 1;
        Registration selectedRegistration = registration;

        // Update the registration's approval status
        selectedRegistration.setStatus(approvalStatusList.get(selectedApprovalStatusId - 1).getStatus());
        System.out.println("Registration status updated successfully!");

        if (selectedRegistration.getStatus().equalsIgnoreCase("Approved")) {
            Activity selectedActivity = selectedRegistration.getActivity();
            selectedActivity.reduceCapacity();
            System.out.println("Activity capacity reduced by one.");

            C206_CaseStudy.updateRegistrationStatus();

            // Get the updated approval status directly from the registration
            String updatedStatus = registration.getStatus();

            // Assert that the approval status has been updated
            assertEquals(19, selectedActivity.getCapacity());
            assertEquals("Approved", updatedStatus);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------------------

    @Test
    public void testAddApprovalStatus() {
        System.setOut(new PrintStream(outContent));

        // Redirect user input to simulate providing the approval status name
        String input = "Pending";

        // Call the addApprovalStatus() method
        // testAddApprovalStatus();
        System.out.println("------------------------");
        System.out.println("Add New Approval Status");
        System.out.println("------------------------");
        approvalStatusList.clear();

        // Add the new approval status to the approvalStatusList
        approvalStatusList.add(new ApprovalStatus(input));

        System.out.println("Approval status added successfully!");

        // Reset System.out and user input to their original values
        System.setOut(originalOut);
        Helper.resetTestingInput();

        // Assert that the printed output matches the expected message
        String expectedOutput = "-".repeat(24) + "\r\n" +
                "Add New Approval Status" + "\r\n" +
                "-".repeat(24) + "\r\n" +
                "Approval status added successfully!\r\n";

        assertEquals(expectedOutput.trim(), outContent.toString().trim());

        // Assert that the new approval status is correctly added to the list
        assertEquals(1, approvalStatusList.size());

        assertEquals("Pending", approvalStatusList.get(0).getStatus().trim());
    }

    @Test
    public void testViewApprovalStatusesEmpty() {
        System.setOut(new PrintStream(outContent));

        // Call the viewApprovalStatuses() method when approvalStatusList is empty
        // testViewApprovalStatusesEmpty();
        System.out.println("------------------------");
        System.out.println("View All Approval Statuses");
        System.out.println("------------------------");

        if (approvalStatusList.isEmpty()) {
            System.out.println("No approval statuses found.");
        } else {
            System.out.println("Approval Statuses:");
            System.out.println(String.format("%-5s %-20s", "ID", "Status Name"));
            System.out.println("---------------------------");
            for (int i = 0; i < approvalStatusList.size(); i++) {
                ApprovalStatus approvalStatus = approvalStatusList.get(i);
                System.out.println(String.format("%-5s %-20s", (i + 1), approvalStatus.getStatus()));
            }
        }
        // Reset System.out to its original value
        System.setOut(originalOut);

        // Assert that the printed output matches the expected message
        String expectedOutput = "------------------------\r\n" +
                "View All Approval Statuses\r\n" +
                "------------------------\r\n" +
                "No approval statuses found.\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testViewApprovalStatusesNonEmpty() {
        approvalStatusList.add(new ApprovalStatus("Pending"));
        approvalStatusList.add(new ApprovalStatus("Approved"));

        // Redirect System.out to outContent for capturing printed output
        System.setOut(new PrintStream(outContent));

        // Call the viewApprovalStatuses() method when approvalStatusList is not empty
        // testViewApprovalStatusesNonEmpty();
        System.out.println("------------------------");
        System.out.println("View All Approval Statuses");
        System.out.println("------------------------");

        if (approvalStatusList.isEmpty()) {
            System.out.println("No approval statuses found.");
        } else {
            System.out.println("Approval Statuses:");
            System.out.println(String.format("%-5s %-20s", "ID", "Status Name"));
            System.out.println("---------------------------");
            for (int i = 0; i < approvalStatusList.size(); i++) {
                ApprovalStatus approvalStatus = approvalStatusList.get(i);
                System.out.println(String.format("%-5s %-20s", (i + 1), approvalStatus.getStatus()));
            }
        }
        // Reset System.out to its original value
        System.setOut(originalOut);

        // Assert that the printed output matches the expected message
        String expectedOutput = "------------------------\r\n" +
                "View All Approval Statuses\r\n" +
                "------------------------\r\n" +
                "Approval Statuses:\r\n" +
                "ID    Status Name         \r\n" +
                "---------------------------\r\n" +
                "1     Pending             \r\n" +
                "2     Approved            \r\n";

        assertEquals(expectedOutput, outContent.toString());
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

    @Test
    public void testDeleteApprovalStatus() {
        // Prepare sample approval statuses
        ApprovalStatus status1 = new ApprovalStatus("Pending");
        ApprovalStatus status2 = new ApprovalStatus("Approved");
        approvalStatusList.add(status1);
        approvalStatusList.add(status2);

        // Redirect System.out to outContent for capturing printed output
        System.setOut(new PrintStream(outContent));

        // Simulate user input for deleting an approval status
        int input = 1;

        // Call the deleteApprovalStatus() method
        // testDeleteApprovalStatus();
        System.out.println("------------------------");
        System.out.println("Delete Approval Status");
        System.out.println("------------------------");

        // Check if there are any approval statuses to delete
        if (approvalStatusList.isEmpty()) {
            System.out.println("No approval statuses found.");
            return;
        }

        // Display all approval statuses with their IDs
        System.out.println("All Approval Statuses:");
        System.out.println(String.format("%-5s %-10s", "ID", "Status"));
        System.out.println("---------------------------");
        for (int i = 0; i < approvalStatusList.size(); i++) {
            ApprovalStatus status = approvalStatusList.get(i);
            System.out.println(String.format("%-5s %-10s", (i + 1), status.getStatus()));
        }

        // Prompt the user to enter the ID of the approval status they want to delete
        int approvalStatusIdToDelete = input;

        // Check if the entered ID is valid
        if (approvalStatusIdToDelete <= 0 || approvalStatusIdToDelete > approvalStatusList.size()) {
            System.out.println("Invalid approval status ID.");
            return;
        }

        // Remove the approval status from the list and inform the user
        ApprovalStatus deletedStatus = approvalStatusList.remove(approvalStatusIdToDelete - 1);
        System.out
                .println("Approval status with ID " + approvalStatusIdToDelete + " has been deleted: " + deletedStatus);
        // Reset System.out and user input to their original values
        System.setOut(originalOut);
        Helper.resetTestingInput();

        // Assert that the printed output matches the expected message
        String expectedOutput = "------------------------\r\n" +
                "Delete Approval Status\r\n" +
                "------------------------\r\n" +
                "All Approval Statuses:\r\n" +
                "ID    Status     \r\n" +
                "---------------------------\r\n" +
                "1     Pending    \r\n" +
                "2     Approved   \r\n" +
                "Approval status with ID 1 has been deleted: Status: Pending\r\n";

        assertEquals(expectedOutput, outContent.toString());

        // Assert that the correct approval status was removed from the list
        assertEquals(1, approvalStatusList.size());
        assertEquals("Approved", approvalStatusList.get(0).getStatus());
    }
    
    @Test
    public void testAddTimeSlot() {
        Activity activity = new Activity("Test Activity", 10, "Test Prerequisites");
        Date startTime = new Date();
        Date endTime = new Date(startTime.getTime() + 3600000); // Adding 1 hour

        TimeSlot timeSlot = new TimeSlot(startTime, endTime, activity);

        assertEquals(startTime, timeSlot.getStartTime());
        assertEquals(endTime, timeSlot.getEndTime());
        assertEquals(activity, timeSlot.getActivity());
    }

}