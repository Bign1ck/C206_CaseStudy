public class Activity {
    private String activityName;
    private int capacity;
    private String prerequisites;

    public Activity(String activityName, int capacity, String prerequisites) {
        this.activityName = activityName;
        this.capacity = capacity;
        this.prerequisites = prerequisites;
    }

    public Activity(String activityName, int capacity) {
        this.activityName = activityName;
        this.capacity = capacity;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    @Override
    public String toString() {
        return "Activity: " + activityName +
                ", Capacity: " + capacity +
                ", Prerequisites: " + prerequisites;
    }
}
