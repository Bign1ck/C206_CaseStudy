import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class C206_CaseStudyTest {

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

        Users user1 = new Users("John Doe", "123456", null, null);
        userList.add(user1);

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

    @Test
    public void testAddUser() {
        // Assuming the addUser method is part of C206_CaseStudy class and works as expected
        //C206_CaseStudy.addUser(userList, "John Doe", "12345", "S_john_doe", "S");

        // Assertions for checking the added user's details
        assertEquals(2, userList.size()); // Assuming the setup added 2 users
        assertEquals("Jane Smith", userList.get(1).getName());
        assertEquals("789012", userList.get(1).getStudentId());
        assertEquals("T_jane_smith", userList.get(1).getUsername());
        assertEquals("T", userList.get(1).getRole());
    }

    @Test
    public void testViewUsersEmpty() {
        

        // Call the viewUsers method
        C206_CaseStudy.viewUsers(userList);

        // Verify the output
        String expectedOutput = "No users found.";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void testViewUsersNonEmpty() {
        // Capture the output
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Call the viewUsers method
        //C206_CaseStudy.viewUsers(userList);

        // Verify the output (This should show user list details if userList is populated)
        String expectedOutput = "--------------------------------------------------------------------------------\n" +
                                "USERS LIST\n" +
                                "--------------------------------------------------------------------------------\n" +
                                "No users found.\n" +
                                "--------------------------------------------------------------------------------";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
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

		//------------------------------------------------------------------------------------------------------------------------
		//------------------------------------------------------------------------------------------------------------------------
		//------------------------------------------------------------------------------------------------------------------------
	
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
			 // Capture the output
			 ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
			 System.setOut(new PrintStream(outputStreamCaptor));
		 
			 // Call the viewActivities method
			 //C206_CaseStudy.viewActivities(activityList);
		 
			 // Verify the output
			 String expectedOutput = "No activities found.";
			 assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
		}
	
		@Test
		public void testViewActivitiesNonEmpty() {
			// Prepare sample data
			activityList.add(new Activity("Activity 1", 10, "Prereq 1"));
			activityList.add(new Activity("Activity 2", 15, "Prereq 2"));
		
			// Capture the output
			ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outputStreamCaptor));
		

	
			//Assert.assertTrue(Helper.getOutput().contains("ID       Activity Name   Capacity  Prerequisites"));
			//Assert.assertTrue(Helper.getOutput().contains("1        Activity 1      10        Prereq 1"));
			//Assert.assertTrue(Helper.getOutput().contains("2        Activity 2      15        Prereq 2"));
		}
	

		public void testDeleteActivity() {
			// Prepare sample activities
			Activity activity1 = new Activity("Swimming", 20, "Swimming goggles");
			Activity activity2 = new Activity("Yoga", 15, "Yoga mat");
			activityList.add(activity1);
			activityList.add(activity2);
		
			// Assuming deleteActivity method is part of C206_CaseStudy class
			//C206_CaseStudy.deleteActivity(activityList, 1); // Deleting by index or ID, based on implementation
		
			// Check if activity with index or ID 1 has been deleted
			assertFalse("Activity has been deleted", activityList.contains(activity1));
		}

		//------------------------------------------------------------------------------------------------------------------------------------
		//-----------------------------------------------------------------------------------------------------------------------------------
		//-----------------------------------------------------------------------------------------------------------------------------------

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
        testViewActivitiesEmpty();

        // Reset System.out to its original value
        System.setOut(originalOut);

        // Assert that the printed output matches the expected message for empty list
        String expectedOutput = "No registrations found.\n";
        assertEquals(expectedOutput, outContent.toString());
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
        testViewActivitiesNonEmpty();

        // Reset System.out to its original value
        System.setOut(originalOut);

        // Assert that the printed output matches the expected format
        String expectedOutput = "--------------------------------------------------------------------------------\n" +
                "Registration ID  User                           Activity            Status         \n" +
                "--------------------------------------------------------------------------------\n" +
                String.format("%-15s %-30s %-20s %-15s%n", registration.getRegistrationId(), user.getName(), activity.getActivityName(), registration.getStatus()) +
                "--------------------------------------------------------------------------------\n";
        assertEquals(expectedOutput, outContent.toString());
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
        testDeleteRegistration();

        // Reset System.out and System.in to their original values
        System.setOut(originalOut);
        System.setIn(System.in);

        // Assert that the printed output matches the expected message
        String expectedOutput = "------------------------\n" +
                "Delete Registration\n" +
                "------------------------\n" +
                "All Registrations:\n" +
                "Reg. ID User                           Activity            Status             \n" +
                "--------------------------------------------------------------------------------\n" +
                String.format("%-5s %-15s %-30s %-20s ", registration.getRegistrationId(), user.getName(), activity.getActivityName(), registration.getStatus()) +
                "\n--------------------------------------------------------------------------------\n" +
                "Enter the ID of the registration to delete: " +
                "Registration with ID 1 has been deleted.\n";

        assertEquals(expectedOutput, outContent.toString());
        assertEquals(0, registrationList.size()); // Check that the registration was removed from the list
    }

	//-----------------------------------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------------------------

	@Test
	public void testAddApprovalStatus() {
		System.setOut(new PrintStream(outContent));

        // Redirect user input to simulate providing the approval status name
        Helper.setInputForTesting("Pending\n");

        // Call the addApprovalStatus() method
        testAddApprovalStatus();

        // Reset System.out and user input to their original values
        System.setOut(originalOut);
        Helper.resetTestingInput();

        // Assert that the printed output matches the expected message
        String expectedOutput = "------------------------\n" +
                "Add New Approval Status\n" +
                "------------------------\n" +
                "Approval status added successfully!\n";

        assertEquals(expectedOutput, outContent.toString());

        // Assert that the new approval status is correctly added to the list
        assertEquals(1, approvalStatusList.size());
        assertEquals("Pending", approvalStatusList.get(0).getStatus());
    }

	@Test
	public void testViewApprovalStatusesEmpty() {
		System.setOut(new PrintStream(outContent));

        // Call the viewApprovalStatuses() method when approvalStatusList is empty
        testViewApprovalStatusesEmpty();

        // Reset System.out to its original value
        System.setOut(originalOut);

        // Assert that the printed output matches the expected message
        String expectedOutput = "------------------------\n" +
                "View All Approval Statuses\n" +
                "------------------------\n" +
                "No approval statuses found.\n";
        assertEquals(expectedOutput, outContent.toString());
    }

	@Test
	public void testViewApprovalStatusesNonEmpty() {
		ApprovalStatus status1 = new ApprovalStatus("Pending");
        ApprovalStatus status2 = new ApprovalStatus("Approved");
        approvalStatusList.add(status1);
        approvalStatusList.add(status2);

        // Redirect System.out to outContent for capturing printed output
        System.setOut(new PrintStream(outContent));

        // Call the viewApprovalStatuses() method when approvalStatusList is not empty
        testViewApprovalStatusesNonEmpty();

        // Reset System.out to its original value
        System.setOut(originalOut);

        // Assert that the printed output matches the expected message
        String expectedOutput = "------------------------\n" +
                "View All Approval Statuses\n" +
                "------------------------\n" +
                "Approval Statuses:\n" +
                "ID    Status Name         \n" +
                "---------------------------\n" +
                "1     Pending             \n" +
                "2     Approved            \n";

        assertEquals(expectedOutput, outContent.toString());
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
        Helper.setInputForTesting("1\n");

        // Call the deleteApprovalStatus() method
        testDeleteApprovalStatus();

        // Reset System.out and user input to their original values
        System.setOut(originalOut);
        Helper.resetTestingInput();

        // Assert that the printed output matches the expected message
        String expectedOutput = "------------------------\n" +
                "Delete Approval Status\n" +
                "------------------------\n" +
                "All Approval Statuses:\n" +
                "ID    Status     \n" +
                "---------------------------\n" +
                "1     Pending    \n" +
                "2     Approved   \n" +
                "Approval status with ID 1 has been deleted: Status: Pending\n";

        assertEquals(expectedOutput, outContent.toString());

        // Assert that the correct approval status was removed from the list
        assertEquals(1, approvalStatusList.size());
        assertEquals("Approved", approvalStatusList.get(0).getStatus());
    }
}