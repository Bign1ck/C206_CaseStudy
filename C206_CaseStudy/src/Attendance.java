import java.util.Date;

public class Attendance {
    private static int nextId = 1;

    private int attendanceId;
    private Users user;
    private TimeSlot timeSlot;
    private Date checkInTime;
    private Date checkOutTime;

    public Attendance(Users user, TimeSlot timeSlot, Date checkInTime) {
        this.attendanceId = nextId++;
        this.user = user;
        this.timeSlot = timeSlot;
        this.checkInTime = checkInTime;
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

    @Override
    public String toString() {
        return "Attendance ID: " + attendanceId +
               ", User: " + user.getName() +
               ", Time Slot: " + timeSlot.getTimeSlotId() +
               ", Check-in Time: " + checkInTime +
               ", Check-out Time: " + checkOutTime;
    }
}
