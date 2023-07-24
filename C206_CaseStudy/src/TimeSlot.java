import java.util.Date;

public class TimeSlot {
    private static int nextId = 1;

    private int timeSlotId;
    private Date startTime;
    private Date endTime;
    private Activity activity;

    public TimeSlot(Date startTime, Date endTime, Activity activity) {
        this.timeSlotId = nextId++;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
    }

    public int getTimeSlotId() {
        return timeSlotId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "Time Slot ID: " + timeSlotId +
               ", Start Time: " + startTime +
               ", End Time: " + endTime +
               ", Activity: " + activity.getActivityName();
    }
}
