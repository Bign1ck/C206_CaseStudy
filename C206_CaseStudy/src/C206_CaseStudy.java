import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class C206_CaseStudy {
	private enum GlobalOption {
		ADD_USER(1, "Global"),
		VIEW_USERS(2, "Global"),
		DELETE_USER(3, "Global"),
		ADD_ACTIVITY(4, "Global"),
		VIEW_ACTIVITIES(5, "S"),
		DELETE_ACTIVITY(6, "Global"),
		ADD_REGISTRATION(7, "S"),
		VIEW_REGISTRATIONS(8, "S"),
		DELETE_REGISTRATION(9, "Global"),
		ADD_APPROVAL_STATUS(10, "T"),
		VIEW_APPROVAL_STATUSES(11, "T"),
		DELETE_APPROVAL_STATUS(12, "T"),
		UPDATE_APPROVAL_STATUS(12, "T"),
		ADD_TIME_SLOT(13, "T"),
		VIEW_TIME_SLOTS(14, "T"),
		DELETE_TIME_SLOT(15, "T"),
		ADD_ATTENDANCE(16, "T"),
		VIEW_ATTENDANCE(17, "T"),
		DELETE_ATTENDANCE(18, "T"),
		QUIT(19, "Global");

		private final int value;
		private final String[] allowedRoles;

		GlobalOption(int value, String... allowedRoles) {
			this.value = value;
			this.allowedRoles = allowedRoles;
		}

		public int getValue() {
			return value;
		}

		public static GlobalOption fromValue(int value) {
			for (GlobalOption option : GlobalOption.values()) {
				if (option.getValue() == value) {
					return option;
				}
			}
			throw new IllegalArgumentException("Invalid option value: " + value);
		}

		public boolean isAllowedForRole(String userRole) {
			for (String role : allowedRoles) {
				if (role.trim().equalsIgnoreCase(userRole.trim())) {
					return true;
				}
			}
			return false;
		}
	}

	private enum StudentOption {
		VIEW_ACTIVITIES(1),
		ADD_REGISTRATION(2),
		QUIT(3);

		private final int value;

		StudentOption(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public static StudentOption fromValue(int value) {
			for (StudentOption option : StudentOption.values()) {
				if (option.getValue() == value) {
					return option;
				}
			}
			throw new IllegalArgumentException("Invalid option value: " + value);
		}
	}

	private enum TeacherOption {
		ADD_ACTIVITY(1),
		VIEW_ACTIVITY(2),
		DELETE_ACTIVITY(3),
		ADD_REGISTRATION(4),
		VIEW_REGISTRATION(5),
		DELETE_REGISTRATION(6),
		UPDATE_APPROVAL_STATUS(7),
		QUIT(8);

		private final int value;

		TeacherOption(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public static TeacherOption fromValue(int value) {
			for (TeacherOption option : TeacherOption.values()) {
				if (option.getValue() == value) {
					return option;
				}
			}
			throw new IllegalArgumentException("Invalid option value: " + value);
		}
	}

	private enum AdminOption {
		ADD_USER(1),
		VIEW_USERS(2),
		DELETE_USER(3),
		ADD_ACTIVITY(4),
		VIEW_ACTIVITY(5),
		DELETE_ACTIVITY(6),
		QUIT(7);

		private final int value;

		AdminOption(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public static AdminOption fromValue(int value) {
			for (AdminOption option : AdminOption.values()) {
				if (option.getValue() == value) {
					return option;
				}
			}
			throw new IllegalArgumentException("Invalid option value: " + value);
		}
	}

	private static Users login(ArrayList<Users> userList) {
		System.out.println("Welcome to the login page");

		String username = Helper.readString("Username: ");

		for (Users user : userList) {
			if (user.getUsername().equals(username)) {
				System.out.println("Login successful.");
				return user;
			}
		}

		System.out.println("Login failed. Invalid username.");
		return null;
	}

	private static ArrayList<Users> userList = new ArrayList<>();
	private static ArrayList<Activity> activityList = new ArrayList<>();
	private static ArrayList<Registration> registrationList = new ArrayList<>();
	private static ArrayList<ApprovalStatus> approvalStatusList = new ArrayList<>();
	private static ArrayList<TimeSlot> timeSlotList = new ArrayList<>();
	private static ArrayList<Attendance> attendanceList = new ArrayList<>();

	private static void mainMenu(Users loggedInUser) {
		displayMenu(loggedInUser.getRole());

		while (true) {
			int option = Helper.readInt("Enter an option > ");
			try {
				switch (loggedInUser.getRole()) {
					case "S":
						handleStudentOption(loggedInUser, StudentOption.fromValue(option));
						displayStudentMenu();
						break;
					case "T":
						handleTeacherOption(loggedInUser, TeacherOption.fromValue(option));
						displayTeacherMenu();
						break;
					case "A":
						handleAdminOption(loggedInUser, AdminOption.fromValue(option));
						displayAdminMenu();
						break;
					case "Global":
						handleGlobalOption(loggedInUser, GlobalOption.fromValue(option));
						displayGlobalMenu();
						break;
					default:
						System.out.println("Invalid role.");
						return;
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid option. Please enter a valid option number.");
			}
		}
	}

	private static void handleStudentOption(Users loggedInUser, StudentOption selectedOption) {
		switch (selectedOption) {
			case VIEW_ACTIVITIES:
				viewActivities();
				break;
			case ADD_REGISTRATION:
				addRegistration();
				break;
			case QUIT:
				System.out.println("Bye!");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option");
				break;
		}
	}

	private static void handleTeacherOption(Users loggedInUser, TeacherOption selectedOption) {
		switch (selectedOption) {
			case ADD_ACTIVITY:
				addActivity();
				break;
			case VIEW_ACTIVITY:
				viewActivities();
				break;
			case DELETE_ACTIVITY:
				deleteActivity();
				break;
			case ADD_REGISTRATION:
				addRegistration();
				break;
			case VIEW_REGISTRATION:
				viewRegistrations();
				break;
			case DELETE_REGISTRATION:
				deleteRegistration();
				break;
			case UPDATE_APPROVAL_STATUS:
				updateRegistrationStatus();
				break;
			case QUIT:
				System.out.println("Bye!");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option");
				break;
		}
	}

	private static void handleAdminOption(Users loggedInUser, AdminOption selectedOption) {
		switch (selectedOption) {
			case ADD_USER:
				addUser();
				break;
			case VIEW_USERS:
				viewUsers();
				break;
			case DELETE_USER:
				deleteUser();
				break;
			case ADD_ACTIVITY:
				addActivity();
				break;
			case VIEW_ACTIVITY:
				viewActivities();
				break;
			case DELETE_ACTIVITY:
				deleteActivity();
				break;
			case QUIT:
				System.out.println("Bye!");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option");
				break;
		}
	}

	private static void handleGlobalOption(Users loggedInUser, GlobalOption selectedOption) {
		switch (selectedOption) {
			case ADD_USER:
				addUser();
				
				break;
			case VIEW_USERS:
				viewUsers();
				break;
			case DELETE_USER:
				deleteUser();
				break;
			case ADD_ACTIVITY:
				addActivity();
				break;
			case VIEW_ACTIVITIES:
				viewActivities();
				break;
			case DELETE_ACTIVITY:
				deleteActivity();
				break;
			case ADD_REGISTRATION:
				addRegistration();
				break;
			case VIEW_REGISTRATIONS:
				viewRegistrations();
				break;
			case DELETE_REGISTRATION:
				deleteRegistration();
				break;
			case ADD_APPROVAL_STATUS:
				addApprovalStatus();
				break;
			case VIEW_APPROVAL_STATUSES:
				viewApprovalStatuses();
				break;
			case DELETE_APPROVAL_STATUS:
				deleteApprovalStatus();
				break;
			case UPDATE_APPROVAL_STATUS:
				deleteApprovalStatus();
				break;
			case ADD_TIME_SLOT:
				addTimeSlot();
				break;
			case VIEW_TIME_SLOTS:
				viewTimeSlots();
				break;
			case DELETE_TIME_SLOT:
				deleteTimeSlot();
				break;
			case ADD_ATTENDANCE:
				addAttendance();
				break;
			case VIEW_ATTENDANCE:
				viewAttendance();
				break;
			case DELETE_ATTENDANCE:
				deleteAttendance();
				break;
			case QUIT:
				System.out.println("Bye!");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option");
				break;
		}
	}

	public static void main(String[] args) {
		userList.add(new Users("John Doe", "12345", "S_john_doe", "S"));
		userList.add(new Users("Jane Smith", "98765", "T_jane_smith", "T"));
		userList.add(new Users("Admin User", "99999", "A_admin_user", "A"));
		userList.add(new Users("Global Admin User", "34567", "G_admin_user", "Global"));
		userList.add(new Users("Alice Brown", "11111", "S_alice_brown", "S"));
		userList.add(new Users("Bob Johnson", "22222", "T_bob_johnson", "T"));
		userList.add(new Users("Eva Williams", "33333", "A_eva_williams", "A"));

		activityList.add(new Activity("Swimming", 20, "Swimming goggles"));
		activityList.add(new Activity("Yoga", 15, "Yoga mat"));
		activityList.add(new Activity("Running", 30, "Running shoes"));
		activityList.add(new Activity("Dancing", 25, "Dance shoes"));

		timeSlotList.add(new TimeSlot(new Date(), new Date(), activityList.get(0)));
		timeSlotList.add(new TimeSlot(new Date(), new Date(), activityList.get(1)));
		timeSlotList.add(new TimeSlot(new Date(), new Date(), activityList.get(2)));
		timeSlotList.add(new TimeSlot(new Date(), new Date(), activityList.get(3)));

		registrationList.add(new Registration(userList.get(0), activityList.get(0), "Pending"));
		registrationList.add(new Registration(userList.get(1), activityList.get(1), "Approved"));
		registrationList.add(new Registration(userList.get(2), activityList.get(2), "Approved"));

		approvalStatusList.add(new ApprovalStatus("Pending"));
		approvalStatusList.add(new ApprovalStatus("Approved"));
		approvalStatusList.add(new ApprovalStatus("Rejected"));

		attendanceList.add(new Attendance(userList.get(0), timeSlotList.get(0), new Date(System.currentTimeMillis() - 12 * 60 * 1000)));
		attendanceList.add(new Attendance(userList.get(1), timeSlotList.get(1), new Date()));
		attendanceList.add(new Attendance(userList.get(5), timeSlotList.get(2),
				new Date(System.currentTimeMillis() + 12 * 60 * 1000)));
		attendanceList.add(new Attendance(userList.get(4), timeSlotList.get(1),
				new Date(System.currentTimeMillis() + 40 * 60 * 1000))); // Partial for Yoga
		attendanceList.add(new Attendance(userList.get(6), timeSlotList.get(3), null)); // Absent for Dancing

		Users loggedInUser = login(userList);
		if (loggedInUser == null) {
			System.out.println("Login failed. Exiting.");
			return;
		}

		System.out.println("Login successful as " + loggedInUser.getUsername() + " (" + loggedInUser.getRole() + ").");

		mainMenu(loggedInUser);
	}

	private static void displayMenu(String role) {
		switch (role) {
			case "S":
				displayStudentMenu();
				break;
			case "T":
				displayTeacherMenu();
				break;
			case "A":
				displayAdminMenu();
				break;
			case "Global":
				displayGlobalMenu();
				break;
			default:
				System.out.println("Invalid role.");
				break;
		}
	}

	private static void displayStudentMenu() {
		System.out.println("Student -->");
		for (StudentOption studentOption : StudentOption.values()) {
			System.out.println(studentOption.getValue() + ". " + studentOption.name().replace("_", " "));
		}
	}

	private static void displayTeacherMenu() {
		System.out.println("Teacher -->");
		for (TeacherOption teacherOption : TeacherOption.values()) {
			System.out.println(teacherOption.getValue() + ". " + teacherOption.name().replace("_", " "));
		}
	}

	private static void displayAdminMenu() {
		System.out.println("Admin -->");
		for (AdminOption adminOption : AdminOption.values()) {
			System.out.println(adminOption.getValue() + ". " + adminOption.name().replace("_", " "));
		}
	}

	private static void displayGlobalMenu() {
		System.out.println("Global Admin -->");
		for (GlobalOption GlobalOption : GlobalOption.values()) {
			System.out.println(GlobalOption.getValue() + ". " + GlobalOption.name().replace("_", " "));
		}
	}

	// ------------------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------------------------

	public static void addUser() {
		System.out.println("ADD NEW USER");
		String name = Helper.readString("Enter username: ");
		String userId = Helper.readString("Enter userID: ");
		String role = Helper.readString("Enter user Role: ");

		// Check if the student ID is already taken
		boolean check = true;
		for (Users user : userList) {
			if (user.getStudentId().equalsIgnoreCase(userId)) {
				System.out.println("Error: Student ID is already taken. Please enter a different ID.");
				check = false;
				break;
			}
		}

		if (check) {
			Users newUser = new Users(name, userId, userId, role);
			userList.add(newUser);

			System.out.println("User added successfully.");
		}

	}

	public static void viewUsers() {
		System.out.println("-".repeat(80));
		System.out.println("USERS LIST");
		System.out.println("-".repeat(80));
		if (userList.isEmpty()) {
			System.out.println("No users found.");
			System.out.println("-".repeat(80));
		} else {
			System.out.printf("%-20s %-20s\n", "NAME", "STUDENT ID");
			for (Users user : userList) {
				System.out.printf("%-20s %-20s\n", user.getName(), user.getStudentId());
			}
			System.out.println("-".repeat(80));
		}
	}

	public static void deleteUser() {
		viewUsers();
		System.out.println("DELETE USER");
		String studentIdToDelete = Helper.readString("\nEnter student ID: ");

		boolean foundUser = false;

		for (Users user : userList) {
			if (user.getStudentId().equalsIgnoreCase(studentIdToDelete)) {
				foundUser = true;

				// Remove related registrations
				List<Registration> registrationsToRemove = new ArrayList<>();
				for (Registration registration : registrationList) {
					if (registration.getUser().equals(user)) {
						Activity registeredActivity = registration.getActivity();
						registeredActivity.increaseCapacity();
						registrationsToRemove.add(registration);
					}
				}
				registrationList.removeAll(registrationsToRemove);

				// Remove related attendance
				List<Attendance> attendanceToRemove = new ArrayList<>();
				for (Attendance attendance : attendanceList) {
					if (attendance.getUser().equals(user)) {
						attendanceToRemove.add(attendance);
					}
				}
				attendanceList.removeAll(attendanceToRemove);

				// Remove the user
				userList.remove(user);
				System.out.println("User deleted successfully!");
				break;
			}
		}
		if (!foundUser) {
			System.out.println("User with the given student ID not found.");
		}
	}

	private static void addActivity() {
		System.out.println("-".repeat(25));
		System.out.println("ADD NEW ACTIVITY ");
		System.out.println("-".repeat(25));

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

	public static void viewActivities() {
		System.out.println("-".repeat(24));
		System.out.println("View All Activities");
		System.out.println("-".repeat(24));

		if (activityList.isEmpty()) {
			System.out.println("No activities found.");
		}

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
	}


	private static void deleteActivity() {
		System.out.println("-".repeat(24));
		System.out.println("DELETE ACTIVITY");
		System.out.println("-".repeat(24));

		if (activityList.isEmpty()) {
			System.out.println("No activities found.");
			return;
		}

		System.out.println("All Activities:");
		String columnTitles = String.format("%-5s %-30s", "ID", "Name");
		System.out.println(columnTitles);

		System.out.println("-".repeat(27));

		for (Activity activity : activityList) {
			String rowDetails = String.format("%-5s %-30s", activity.getActivityId(), activity.getActivityName());
			System.out.println(rowDetails);
		}

		int activityIdToDelete = Helper.readInt("Delete activity(ID): ");

		Activity activityToDelete = null;
		for (Activity activity : activityList) {
			if (activity.getActivityId() == activityIdToDelete) {
				activityToDelete = activity;
				break;
			}
		}

		if (activityToDelete == null) {
			System.out.println("Invalid activity ID.");
			return;
		}

		final Activity finalActivityToDelete = activityToDelete; // Capture a final reference

		// Remove registrations of the deleted activity
		registrationList.removeIf(registration -> registration.getActivity().equals(finalActivityToDelete));

		// Remove time slots associated with the deleted activity
		timeSlotList.removeIf(timeSlot -> timeSlot.getActivity().equals(finalActivityToDelete));

		// Remove attendance records associated with the deleted activity
		attendanceList.removeIf(attendance -> attendance.getTimeSlot().getActivity().equals(finalActivityToDelete));

		// remove the activity from the list
		activityList.remove(finalActivityToDelete);

		System.out.println("Activity with ID " + activityIdToDelete + " has been deleted.");
	}

	private static void addRegistration() {
		System.out.println("-".repeat(24));
		System.out.println("ADD NEW REGISTRATION");
		System.out.println("-".repeat(24));

		if (userList.isEmpty() || activityList.isEmpty()) {
			System.out.println("Error: No users or activities found. Please add users and activities first.");
			return;
		}

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

	public static void updateRegistrationStatus() {
		System.out.println("------------------------");
		System.out.println("Update Registration Status");
		System.out.println("------------------------");

		// Check if there are any registrations to update
		if (registrationList.isEmpty()) {
			System.out.println("No registrations found.");
			return;
		}

		// Display all registrations with their IDs
		System.out.println("All Registrations:");
		System.out.println(String.format("%-5s %-15s %-30s %-20s", "Reg. ID", "User", "Activity", "Status"));
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

		// Prompt the user to enter the ID of the registration they want to update
		int registrationIdToUpdate = Helper.readInt("Enter the ID of the registration to update: ");

		// Check if the entered ID is valid
		if (registrationIdToUpdate <= 0 || registrationIdToUpdate > registrationList.size()) {
			System.out.println("Invalid registration ID.");
			return;
		}

		// Find the registration with the entered ID
		Registration selectedRegistration = registrationList.get(registrationIdToUpdate - 1);

		// Display available approval statuses
		System.out.println("Available Approval Statuses:");
		for (int i = 0; i < approvalStatusList.size(); i++) {
			ApprovalStatus approvalStatus = approvalStatusList.get(i);
			System.out.println(String.format("%-5s %-20s", (i + 1), approvalStatus.getStatus()));
		}

		// Prompt the user to select an approval status
		int selectedApprovalStatusId = Helper.readInt("Enter the ID of the approval status to set: ");

		// Check if the entered ID is valid
		if (selectedApprovalStatusId <= 0 || selectedApprovalStatusId > approvalStatusList.size()) {
			System.out.println("Invalid approval status ID.");
			return;
		}

		// Update the registration's approval status
		selectedRegistration.setStatus(approvalStatusList.get(selectedApprovalStatusId - 1).getStatus());
		System.out.println("Registration status updated successfully!");

		if (selectedRegistration.getStatus().equalsIgnoreCase("Approved")) {
			Activity selectedActivity = selectedRegistration.getActivity();
			selectedActivity.reduceCapacity();
			System.out.println("Activity capacity reduced by one.");
		}
	}

	private static void addTimeSlot() {
		System.out.println("------------------------");
		System.out.println("Add New Time Slot");
		System.out.println("------------------------");

		// Gather information from the user
		Date startTime = Helper.readDateTime("Enter start time (dd/MM/yyyy HH:mm): ");
		Date endTime = Helper.readDateTime("Enter end time (dd/MM/yyyy HH:mm): ");

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
		System.out.println("-".repeat(100));
		System.out.println("View All Time Slots");
		System.out.println("-".repeat(100));

		if (timeSlotList.isEmpty()) {
			System.out.println("No time slots found.");
		} else {
			System.out.println("Time Slots:");
			System.out.println(
					String.format("%-5s %-15s %-30s %-30s %-30s", "ID", "Day", "Start Time", "End Time", "Activity"));
			System.out.println("-".repeat(100));
			for (int i = 0; i < timeSlotList.size(); i++) {
				TimeSlot timeSlot = timeSlotList.get(i);
				String activityName = timeSlot.getActivity().getActivityName();
				System.out.println(String.format("%-5s %-15s %-30s %-30s %-30s", (i + 1), timeSlot.getDay(),
						timeSlot.getStartTime(), timeSlot.getEndTime(), activityName));
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

	private static Users getUserById(int userId) {
		for (Users user : userList) {
			if (user.getStudentId().equals(String.valueOf(userId))) {
				return user;
			}
		}
		return null; // User with the given ID not found
	}

	// Helper method to get a time slot by ID
	private static TimeSlot getTimeSlotById(int timeSlotId) {
		for (TimeSlot timeSlot : timeSlotList) {
			if (timeSlot.getTimeSlotId() == timeSlotId) {
				return timeSlot;
			}
		}
		return null; // Time slot not found
	}

	public static void addAttendance() {
		System.out.println("------------------------");
		System.out.println("Add New Attendance");
		System.out.println("------------------------");

		// Get user details
		viewUsers();
		Integer userId = Helper.readInt("Enter the ID of the user: ");
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
		}
	}

	public static void viewAttendance(Object object) {
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

				System.out.println(String.format("%-5s %-15s %-20s %-30s %-30s %-20s", attendanceId, userName,
						activityName, startTime, checkInTime, attendanceStatus));
			}
		}
	}

	private static void deleteAttendance() {
		System.out.println("------------------------");
		System.out.println("Delete Attendance");
		System.out.println("------------------------");

		// Check if there are any attendance records to delete
		if (attendanceList.isEmpty()) {
			System.out.println("No attendance records found.");
			return;
		}

		// Display all attendance records with their IDs
		System.out.println("All Attendance Records:");
		System.out.println(String.format("%-5s %-20s %-15s", "ID", "User", "Time Slot"));
		System.out.println("---------------------------------------");
		for (int i = 0; i < attendanceList.size(); i++) {
			Attendance attendance = attendanceList.get(i);
			System.out.println(String.format("%-5s %-20s %-15s", (i + 1), attendance.getUser().getName(),
					attendance.getTimeSlot().getTimeSlotId()));
		}

		// Prompt the user to enter the ID of the attendance record they want to delete
		int attendanceIdToDelete = Helper.readInt("Enter the ID of the attendance record to delete: ");

		// Check if the entered ID is valid
		if (attendanceIdToDelete <= 0 || attendanceIdToDelete > attendanceList.size()) {
			System.out.println("Invalid attendance record ID.");
			return;
		}

		// Remove the attendance record from the list and inform the user
		attendanceList.remove(attendanceIdToDelete - 1);
		System.out.println("Attendance record with ID " + attendanceIdToDelete + " has been deleted.");
	}
}
