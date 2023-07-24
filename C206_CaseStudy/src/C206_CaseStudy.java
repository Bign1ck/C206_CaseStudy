import java.util.ArrayList;

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
		Helper.line(80, "-");
	}

	private static void addUser() {
		System.out.println("ADD NEW USER");
		String name = Helper.readString("Enter user's name: ");
		String userId = Helper.readString("Enter user's ID: ");

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
			System.out.println(String.format("%-15s %-10s %-30s", "Activity Name", "Capacity", "Name"));
			System.out.println("---------------------------------------------------");
			for (Activity activity : activityList) {
				System.out.println(String.format("%-15s %-10s %-30s", activity.getActivityName(), activity.getCapacity(),
						activity.getActivityName()));
			}
		}
	}

	private static void deleteActivity() {
		// Implement code to delete an existing activity from the activityList
	}

	private static void addRegistration() {
		// Implement code to add a new registration to the registrationList
	}

	private static void viewRegistrations() {
		// Implement code to view all registrations in the registrationList
	}

	private static void deleteRegistration() {
		// Implement code to delete an existing registration from the registrationList
	}

	private static void addApprovalStatus() {
		// Implement code to add a new approval status to the approvalStatusList
	}

	private static void viewApprovalStatuses() {
		// Implement code to view all approval statuses in the approvalStatusList
	}

	private static void deleteApprovalStatus() {
		// Implement code to delete an existing approval status from the
		// approvalStatusList
	}

	private static void addTimeSlot() {
		// Implement code to add a new time slot to the timeSlotList
	}

	private static void viewTimeSlots() {
		// Implement code to view all time slots in the timeSlotList
	}

	private static void deleteTimeSlot() {
		// Implement code to delete an existing time slot from the timeSlotList
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

	// Helper methods and classes here...
}
