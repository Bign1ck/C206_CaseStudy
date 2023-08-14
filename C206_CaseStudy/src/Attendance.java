import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Attendance {
    private static int nextId = 1;

    private int attendanceId;
    private Users user;
    private TimeSlot timeSlot;
    private Date checkInTime;
    private Date checkOutTime;
    private String attendanceStatus;

    public Attendance(Users user, TimeSlot timeSlot, Date checkInTime) {
        this.attendanceId = nextId++;
        this.user = user;
        this.timeSlot = timeSlot;
        this.checkInTime = checkInTime;
        this.attendanceStatus = calculateAttendanceStatus();
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public String calculateAttendanceStatus() {
        if (checkInTime == null) {
            return "Absent";
        }

        long minutesLate = calculateMinutesLate();

        if (minutesLate >= 30) {
            return "Partial";
        } else if (checkInTime.after(timeSlot.getStartTime())) {
            return "Late";
        } else if (checkInTime.before(timeSlot.getStartTime())) {
            return "Present";
        }

        return "Unknown";
    }

    private long calculateMinutesLate() {
        long checkInMillis = checkInTime.getTime();
        long startTimeMillis = timeSlot.getStartTime().getTime();
        long diffMillis = checkInMillis - startTimeMillis;
        return TimeUnit.MILLISECONDS.toMinutes(diffMillis);
    }

    @Override
    public String toString() {
        return "Attendance ID: " + attendanceId +
                ", User: " + user.getName() +
                ", Time Slot: " + timeSlot.getTimeSlotId() +
                ", Check-in Time: " + checkInTime +
                ", Check-out Time: " + checkOutTime +
                ", Status: " + attendanceStatus;
    }
}
