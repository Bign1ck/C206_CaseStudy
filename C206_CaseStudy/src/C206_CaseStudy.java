import java.util.ArrayList;
import java.util.Date;

public class C206_CaseStudy {

	private static final int OPTION_ADD_USER = 1;
	private static final int OPTION_VIEW_USERS = 2;
	private static final int OPTION_DELETE_USER = 3;
	private static final int OPTION_ADD_ACTIVITY = 4;
	private static final int OPTION_VIEW_ACTIVITIES = 5;
	private static final int OPTION_DELETE_ACTIVITY = 6;
	private static final int OPTION_ADD_REGISTRATION = 7;
	private static final int OPTION_VIEW_REGISTRATIONS = 8;
	private static final int OPTION_DELETE_REGISTRATION = 9;
	private static final int OPTION_ADD_APPROVAL_STATUS = 10;
	private static final int OPTION_VIEW_APPROVAL_STATUSES = 11;
	private static final int OPTION_DELETE_APPROVAL_STATUS = 12;
	private static final int OPTION_ADD_TIME_SLOT = 13;
	private static final int OPTION_VIEW_TIME_SLOTS = 14;
	private static final int OPTION_DELETE_TIME_SLOT = 15;
	private static final int OPTION_ADD_ATTENDANCE = 16;
	private static final int OPTION_VIEW_ATTENDANCE = 17;
	private static final int OPTION_DELETE_ATTENDANCE = 18;
	private static final int OPTION_QUIT = 19;

	private static ArrayList<Users> userList = new ArrayList<>();
	private static ArrayList<Activity> activityList = new ArrayList<>();
	private static ArrayList<Registration> registrationList = new ArrayList<>();
	private static ArrayList<ApprovalStatus> approvalStatusList = new ArrayList<>();
	private static ArrayList<TimeSlot> timeSlotList = new ArrayList<>();
	private static ArrayList<Attendance> attendanceList = new ArrayList<>();

	public static void main(String[] args) {

		Users user1 = new Users("John Doe", "123456");
		userList.add(user1);

		Users user2 = new Users("Jane Smith", "789012");
		userList.add(user2);

		Activity activity1 = new Activity("Swimming", 20, "Swimming goggles");
		activityList.add(activity1);

		Activity activity2 = new Activity("Yoga", 15, "Yoga mat");
		activityList.add(activity2);

		int option = 0;

		while (option != OPTION_QUIT) {
			menu();
			option = Helper.readInt("Enter an option > ");

			switch (option) {
				case OPTION_ADD_USER:
					addUser();
					break;
				case OPTION_VIEW_USERS:
					viewUsers();
					break;
				case OPTION_DELETE_USER:
					deleteUser();
					break;
				case OPTION_ADD_ACTIVITY:
					addActivity();
					break;
				case OPTION_VIEW_ACTIVITIES:
					viewActivities();
					break;
				case OPTION_DELETE_ACTIVITY:
					deleteActivity();
					break;
				case OPTION_ADD_REGISTRATION:
					addRegistration();
					break;
				case OPTION_VIEW_REGISTRATIONS:
					viewRegistrations();
					break;
				case OPTION_DELETE_REGISTRATION:
					deleteRegistration();
					break;
				case OPTION_ADD_APPROVAL_STATUS:
					addApprovalStatus();
					break;
				case OPTION_VIEW_APPROVAL_STATUSES:
					viewApprovalStatuses();
					break;
				case OPTION_DELETE_APPROVAL_STATUS:
					deleteApprovalStatus();
					break;
				case OPTION_ADD_TIME_SLOT:
					addTimeSlot();
					break;
				case OPTION_VIEW_TIME_SLOTS:
					viewTimeSlots();
					break;
				case OPTION_DELETE_TIME_SLOT:
					deleteTimeSlot();
					break;
				case OPTION_ADD_ATTENDANCE:
					addAttendance();
					break;
				case OPTION_VIEW_ATTENDANCE:
					viewAttendance();
					break;
				case OPTION_DELETE_ATTENDANCE:
					deleteAttendance();
					break;
				case OPTION_QUIT:
					System.out.println("Bye!");
					break;
				default:
					System.out.println("Invalid option");
					break;
			}
		}
	}

	private static void menu() {

		System.out.println("------------------------");
		System.out.println("Menu Options");
		System.out.println("------------------------");
		System.out.println("1. Add a new user");
		System.out.println("2. View all users");
		System.out.println("3. Delete an existing user");
		System.out.println("4. Add a new activity");
		System.out.println("5. View all activities");
		System.out.println("6. Delete an existing activity");
		System.out.println("7. Add a new registration");
		System.out.println("8. View all registrations");
		System.out.println("9. Delete an existing registration");
		System.out.println("10. Add a new approval status");
		System.out.println("11. View all approval statuses");
		System.out.println("12. Delete an existing approval status");
		System.out.println("13. Add a new time slot");
		System.out.println("14. View all time slots");
		System.out.println("15. Delete an existing time slot");
		System.out.println("16. Add a new attendance");
		System.out.println("17. View all attendance");
		System.out.println("18. Delete an existing attendance");
		System.out.println("19. Quit");
	}

	private static void addUser() {
		System.out.println("ADD NEW USER");
		String name = Helper.readString("Enter user's name: ");
		String userId = Helper.readString("Enter user's ID: ");

		// Check if the student ID is already taken
		for (Users user : userList) {
			if (user.getStudentId().equalsIgnoreCase(userId)) {
				System.out.println("Error: Student ID is already taken. Please enter a different ID.");
				return;
			}
		}

		// Create a new User object
		Users newUser = new Users(name, userId);

		// Add the new user to the userList
		userList.add(newUser);

		System.out.println("User added successfully.");
	}

	private static void viewUsers() {
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("USERS LIST");
		System.out.println("--------------------------------------------------------------------------------");
		if (userList.isEmpty()) {
			System.out.println("No users found.");
			System.out.println("--------------------------------------------------------------------------------");
		} else {
			System.out.printf("%-20s %-20s\n", "NAME", "STUDENT ID");
			for (Users user : userList) {
				System.out.printf("%-20s %-20s\n", user.getName(), user.getStudentId());
			}
			System.out.println("--------------------------------------------------------------------------------");
		}
	}

	private static void deleteUser() {
		viewUsers();
		String studentIdToDelete = Helper.readString("Enter the student ID of the user to delete: ");
		Users userToDelete = null;
		for (Users user : userList) {
			if (user.getStudentId().equalsIgnoreCase(studentIdToDelete)) {
				userToDelete = user;
				break;
			}
		}
		if (userToDelete != null) {
			userList.remove(userToDelete);
			System.out.println("User deleted successfully!");
		} else {
			System.out.println("User with the given student ID not found.");
		}
	}

	private static void addActivity() {
		System.out.println("------------------------");
		System.out.println("Add New Activity");
		System.out.println("------------------------");

		// Gather information from the user
		String name = Helper.readString("Enter activity name: ");
		int capacity = Helper.readInt("Enter activity capacity: ");
		String prerequisites = Helper.readString("Enter prerequisites (optional): ");

		// Create a new Activity object
		Activity activity = new Activity(name, capacity, prerequisites);

		// Add the new activity to the activityList
		activityList.add(activity);

		System.out.println("Activity added successfully!");
	}

	private static void viewActivities() {
		System.out.println("------------------------");
		System.out.println("View All Activities");
		System.out.println("------------------------");

		if (activityList.isEmpty()) {
			System.out.println("No activities found.");
		} else {
			System.out.println(
					String.format("%-5s %-15s %-10s %-30s", "ID", "Activity Name", "Capacity", "Prerequisites"));
			System.out.println("---------------------------------------------------");
			for (Activity activity : activityList) {
				System.out.println(String.format("%-5s %-15s %-10s %-30s", activity.getActivityId(),
						activity.getActivityName(), activity.getCapacity(), activity.getPrerequisites()));
			}
		}
	}

	private static void deleteActivity() {
		System.out.println("------------------------");
		System.out.println("Delete Activity");
		System.out.println("------------------------");

		// Check if there are any activities to delete
		if (activityList.isEmpty()) {
			System.out.println("No activities found.");
			return;
		}

		// Display all activities with their IDs
		System.out.println("All Activities:");
		System.out.println(String.format("%-5s %-30s", "ID", "Name"));
		System.out.println("---------------------------");
		for (Activity activity : activityList) {
			System.out.println(String.format("%-5s %-30s", activity.getActivityId(), activity.getActivityName()));
		}

		// Prompt the user to enter the ID of the activity they want to delete
		int activityIdToDelete = Helper.readInt("Enter the ID of the activity to delete: ");

		// Find the activity with the entered activityId
		Activity deletedActivity = null;
		for (Activity activity : activityList) {
			if (activity.getActivityId() == activityIdToDelete) {
				deletedActivity = activity;
				break;
			}
		}

		// Check if the entered ID is valid
		if (deletedActivity == null) {
			System.out.println("Invalid activity ID.");
			return;
		}

		// Remove the activity from the list and inform the user
		activityList.remove(deletedActivity);
		System.out.println("Activity with ID " + activityIdToDelete + " has been deleted.");
	}

	private static void addRegistration() {
		System.out.println("------------------------");
		System.out.println("Add New Registration");
		System.out.println("------------------------");

		if (userList.isEmpty() || activityList.isEmpty()) {
			System.out.println("Error: No users or activities found. Please add users and activities first.");
			return;
		}

		viewUsers();
		String userId = Helper.readString("Enter the student's ID for registration: ");
		Users selectedUser = null;

		// Find the user with the entered student ID
		for (Users user : userList) {
			if (user.getStudentId().equalsIgnoreCase(userId)) {
				selectedUser = user;
				break;
			}
		}

		if (selectedUser == null) {
			System.out.println("User with the entered student ID not found. Registration aborted.");
			return;
		}

		viewActivities();
		int activityId = Helper.readInt("Enter the ID of the activity for registration: ");
		Activity selectedActivity = null;

		// Find the activity with the entered activityId
		for (Activity activity : activityList) {
			if (activity.getActivityId() == activityId) {
				selectedActivity = activity;
				break;
			}
		}

		if (selectedActivity == null) {
			System.out.println("Activity with the entered ID not found. Registration aborted.");
			return;
		}

		// Check if the user is already registered for the selected activity
		for (Registration registration : registrationList) {
			if (registration.getUser().equals(selectedUser) && registration.getActivity().equals(selectedActivity)) {
				System.out.println("User " + selectedUser.getName() + " is already registered for activity "
						+ selectedActivity.getActivityName() + ".");
				return;
			}
		}

		Registration registration = new Registration(selectedUser, selectedActivity, "Pending");
		registrationList.add(registration);

		System.out.println("Registration added successfully.");
	}

	private static void viewRegistrations() {
		// Check if there are any registrations in the list
		if (registrationList.isEmpty()) {
			System.out.println("No registrations found.");
			return;
		}

		// Print header
		System.out.println("--------------------------------------------------------------------------------");
		System.out.printf("%-15s %-30s %-20s %-15s%n", "Registration ID", "User", "Activity", "Status");
		System.out.println("--------------------------------------------------------------------------------");

		// Print registration details
		for (Registration registration : registrationList) {
			System.out.printf("%-15s %-30s %-20s %-15s%n",
					registration.getRegistrationId(),
					registration.getUser().getName(),
					registration.getActivity().getActivityName(),
					registration.getStatus());
		}

		// Print footer
		System.out.println("--------------------------------------------------------------------------------");
	}

	private static void deleteRegistration() {
		System.out.println("------------------------");
		System.out.println("Delete Registration");
		System.out.println("------------------------");

		// Check if there are any registrations to delete
		if (registrationList.isEmpty()) {
			System.out.println("------------------------");
			System.out.println("No registrations found.");
			System.out.println("------------------------");
			return;
		}

		// Display all registrations with their IDs
		System.out.println("All Registrations:");
		System.out
				.println(String.format("%-5s %-15s %-30s %-20s", "Reg. ID", "User", "Activity", "Status"));
		System.out.println("--------------------------------------------------------------------------------");
		for (int i = 0; i < registrationList.size(); i++) {
			Registration registration = registrationList.get(i);
			System.out.println(String.format("%-5s %-15s %-30s %-20s ",

					registration.getRegistrationId(),
					registration.getUser().getName(),
					registration.getActivity().getActivityName(),
					registration.getStatus()));
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
	}

	private static void addApprovalStatus() {
		System.out.println("------------------------");
		System.out.println("Add New Approval Status");
		System.out.println("------------------------");

		// Gather information from the user
		String statusName = Helper.readString("Enter approval status name: ");

		// Create a new ApprovalStatus object
		ApprovalStatus newApprovalStatus = new ApprovalStatus(statusName);

		// Add the new approval status to the approvalStatusList
		approvalStatusList.add(newApprovalStatus);

		System.out.println("Approval status added successfully!");
	}

	private static void viewApprovalStatuses() {
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
	}

	private static void deleteApprovalStatus() {
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
		int approvalStatusIdToDelete = Helper.readInt("Enter the ID of the approval status to delete: ");

		// Check if the entered ID is valid
		if (approvalStatusIdToDelete <= 0 || approvalStatusIdToDelete > approvalStatusList.size()) {
			System.out.println("Invalid approval status ID.");
			return;
		}

		// Remove the approval status from the list and inform the user
		ApprovalStatus deletedStatus = approvalStatusList.remove(approvalStatusIdToDelete - 1);
		System.out
				.println("Approval status with ID " + approvalStatusIdToDelete + " has been deleted: " + deletedStatus);
	}

	private static Activity getActivityById(int activityId) {
		for (Activity activity : activityList) {
			if (activity.getActivityId() == activityId) {
				return activity;
			}
		}
		return null; // Return null if the activity with the specified ID is not found
	}

	private static void addTimeSlot() {
		System.out.println("------------------------");
		System.out.println("Add New Time Slot");
		System.out.println("------------------------");

		// Gather information from the user
		Date startTime = Helper.readDate("Enter start time (dd/MM/yyyy HH:mm): ");
		Date endTime = Helper.readDate("Enter end time (dd/MM/yyyy HH:mm): ");

		// Ensure that the end time is after the start time
		if (endTime.before(startTime)) {
			System.out.println("Error: End time cannot be before the start time. Time slot creation aborted.");
			return;
		}

		viewActivities();
		int activityId = Helper.readInt("Enter the ID of the activity for the time slot: ");
		Activity selectedActivity = getActivityById(activityId);

		if (selectedActivity == null) {
			System.out.println("Error: Activity with the entered ID not found. Time slot creation aborted.");
			return;
		}

		// Check if the time slot conflicts with existing time slots for the selected
		// activity
		if (hasTimeSlotConflict(startTime, endTime, selectedActivity)) {
			System.out.println(
					"Error: There is a time slot conflict with the selected activity. Time slot creation aborted.");
			return;
		}

		// Create a new TimeSlot object
		TimeSlot timeSlot = new TimeSlot(startTime, endTime, selectedActivity);

		// Add the new time slot to the timeSlotList
		timeSlotList.add(timeSlot);

		System.out.println("Time slot added successfully!");
	}

	// Helper method to check if there is a time slot conflict with the selected
	// activity
	private static boolean hasTimeSlotConflict(Date startTime, Date endTime, Activity activity) {
		for (TimeSlot existingTimeSlot : timeSlotList) {
			if (existingTimeSlot.getActivity().equals(activity)) {
				// Check if the new time slot's start or end time falls within the existing time
				// slot
				if ((startTime.after(existingTimeSlot.getStartTime())
						&& startTime.before(existingTimeSlot.getEndTime()))
						|| (endTime.after(existingTimeSlot.getStartTime())
								&& endTime.before(existingTimeSlot.getEndTime()))) {
					return true; // There is a time slot conflict
				}
			}
		}
		return false; // No time slot conflict
	}

	private static void viewTimeSlots() {
		System.out.println("------------------------");
		System.out.println("View All Time Slots");
		System.out.println("------------------------");

		if (timeSlotList.isEmpty()) {
			System.out.println("No time slots found.");
		} else {
			System.out.println("Time Slots:");
			System.out.println(String.format("%-5s %-15s %-10s", "ID", "Day", "Time"));
			System.out.println("---------------------------");
			for (int i = 0; i < timeSlotList.size(); i++) {
				TimeSlot timeSlot = timeSlotList.get(i);
				System.out.println(String.format("%-5s %-15s %-10s", (i + 1), timeSlot.getDay(),
						timeSlot.getStartTime() + " - " + timeSlot.getEndTime()));
			}
		}
	}

	private static void deleteTimeSlot() {
		System.out.println("------------------------");
		System.out.println("Delete Time Slot");
		System.out.println("------------------------");

		// Check if there are any time slots to delete
		if (timeSlotList.isEmpty()) {
			System.out.println("No time slots found.");
			return;
		}

		// Display all time slots with their IDs
		System.out.println("All Time Slots:");
		System.out.println(String.format("%-5s %-15s %-10s", "ID", "Day", "Time"));
		System.out.println("---------------------------");
		for (int i = 0; i < timeSlotList.size(); i++) {
			TimeSlot timeSlot = timeSlotList.get(i);
			System.out.println(String.format("%-5s %-15s %-10s", (i + 1), timeSlot.getDay(),
					timeSlot.getStartTime() + " - " + timeSlot.getEndTime()));
		}

		// Prompt the user to enter the ID of the time slot they want to delete
		int timeSlotIdToDelete = Helper.readInt("Enter the ID of the time slot to delete: ");

		// Check if the entered ID is valid
		if (timeSlotIdToDelete <= 0 || timeSlotIdToDelete > timeSlotList.size()) {
			System.out.println("Invalid time slot ID.");
			return;
		}

		// Remove the time slot from the list and inform the user
		timeSlotList.remove(timeSlotIdToDelete - 1);
		System.out.println("Time slot with ID " + timeSlotIdToDelete + " has been deleted.");
	}

	private static void addAttendance() {
		// Implement code to add a new attendance to the attendanceList
	}

	private static void viewAttendance() {
		// Implement code to view all attendance in the attendanceList
	}

	private static void deleteAttendance() {
		// Implement code to delete an existing attendance from the attendanceList
	}

	public static String viewUsersForTesting() {
		return null;
	}

	// Helper methods and classes here...
}
