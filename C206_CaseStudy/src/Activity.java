public class Activity {
    private static int nextActivityId = 1;
    private String activityName;
    private int capacity;
    private String prerequisites;
    private int activityId;

    public Activity(String activityName, int capacity, String prerequisites) {
        this.activityId = nextActivityId++;
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

    public int getActivityId() {
        return activityId;
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

    public void increaseCapacity() {
        this.capacity++;
    }

    public void reduceCapacity() {
        if (capacity > 0) {
            capacity--;
        } else {
            System.out.println("Activity capacity is already at its minimum.");
        }
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
