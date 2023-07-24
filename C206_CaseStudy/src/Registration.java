public class Registration {
    private static int nextId = 1;

    private int registrationId;
    private Users user;
    private Activity activity;
    private String status;

    public Registration(Users user, Activity activity, String status) {
        this.registrationId = nextId++;
        this.user = user;
        this.activity = activity;
        this.status = status;
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Registration ID: " + registrationId +
               ", User: " + user.getName() +
               ", Activity: " + activity.getActivityName() +
               ", Status: " + status;
    }
}
