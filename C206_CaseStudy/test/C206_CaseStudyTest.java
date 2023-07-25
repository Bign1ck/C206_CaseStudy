import static org.junit.Assert.*;
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

	@Before
	public void setUp() {
		// Initialize the lists before each test
		userList = new ArrayList<>();
		activityList = new ArrayList<>();
		registrationList = new ArrayList<>();
		approvalStatusList = new ArrayList<>();

		Users user1 = new Users("John Doe", "123456");
        userList.add(user1);

        Users user2 = new Users("Jane Smith", "789012");
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
		//test when userID is in database
		String userID = "123456";
		String userName = "Jim";

		boolean Check_If_ID_Is_In_Database = false;
		boolean CheckIfSuccessfullyAdded = false;
		
		for (Users user : userList) {
			Check_If_ID_Is_In_Database = user.getStudentId().equalsIgnoreCase(userID);
			if(Check_If_ID_Is_In_Database){
				assertTrue(Check_If_ID_Is_In_Database);
				assertFalse(CheckIfSuccessfullyAdded);
				break;
			}
		}

		//test when userID id not in database
		userID = "654321";
		userName = "Jim";
		for(Users user : userList){
			Check_If_ID_Is_In_Database = user.getStudentId().equalsIgnoreCase(userID);
			if(Check_If_ID_Is_In_Database){
				assertFalse(Check_If_ID_Is_In_Database);
				break;
			}
		}

			if(!Check_If_ID_Is_In_Database){
				Users newUser = new Users(userName, userID);
				userList.add(newUser);

				for(Users user : userList){
					CheckIfSuccessfullyAdded = user.getStudentId().equalsIgnoreCase(userID)
											   &&user.getName().equalsIgnoreCase(userName);
				}
				assertTrue(CheckIfSuccessfullyAdded);
			}

		
	}

	@Test
	public void testViewUsersEmpty() {
		userList.clear();
		boolean userListIsEmpty = false;

		if(userList.isEmpty()){
			userListIsEmpty= true;
		}

		assertEquals(true, userListIsEmpty);

	}

	@Test
	public void testViewUsersNonEmpty() {
				
		boolean userListIsEmpty = true;

		if(! userList.isEmpty()){
			Users user1 = new Users("John Doe", "123456");
			userList.add(user1);

			Users user2 = new Users("Jane Smith", "789012");
			userList.add(user2);

			userListIsEmpty = false;
		}

		assertEquals(false, userListIsEmpty);

	}

	@Test
	public void testDeleteUser() {
		// Test deleteUser() method
		// Implement the test for deleteUser() method here
	}

	@Test
	public void testAddActivity() {
		// Test addActivity() method
		// Implement the test for addActivity() method here
	}

	@Test
	public void testViewActivitiesEmpty() {
		// Test viewActivities() method when activityList is empty
		// Implement the test for viewActivities() method when activityList is empty
		// here
	}

	@Test
	public void testViewActivitiesNonEmpty() {
		// Test viewActivities() method when activityList is not empty
		// Implement the test for viewActivities() method when activityList is not empty
		// here
	}

	@Test
	public void testDeleteActivity() {
		// Test deleteActivity() method
		// Implement the test for deleteActivity() method here
	}

	@Test
	public void testAddRegistration() {
		// Test addRegistration() method
		// Implement the test for addRegistration() method here
	}

	@Test
	public void testViewRegistrationsEmpty() {
		// Test viewRegistrations() method when registrationList is empty
		// Implement the test for viewRegistrations() method when registrationList is
		// empty here
	}

	@Test
	public void testViewRegistrationsNonEmpty() {
		// Test viewRegistrations() method when registrationList is not empty
		// Implement the test for viewRegistrations() method when registrationList is
		// not empty here
	}

	@Test
	public void testDeleteRegistration() {
		// Test deleteRegistration() method
		// Implement the test for deleteRegistration() method here
	}

	@Test
	public void testAddApprovalStatus() {
		// Test addApprovalStatus() method
		// Implement the test for addApprovalStatus() method here
	}

	@Test
	public void testViewApprovalStatusesEmpty() {
		// Test viewApprovalStatuses() method when approvalStatusList is empty
		// Implement the test for viewApprovalStatuses() method when approvalStatusList
		// is empty here
	}

	@Test
	public void testViewApprovalStatusesNonEmpty() {
		// Test viewApprovalStatuses() method when approvalStatusList is not empty
		// Implement the test for viewApprovalStatuses() method when approvalStatusList
		// is not empty here
	}

	@Test
	public void testDeleteApprovalStatus() {
		// Test deleteApprovalStatus() method
		// Implement the test for deleteApprovalStatus() method here
	}
}