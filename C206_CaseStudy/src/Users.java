public class Users {
    private static int nextId = 1;

    private int userId;
    private String name;
    private String studentId;

    public Users(String name, String studentId) {
        this.userId = nextId++;
        this.name = name;
        this.studentId = studentId;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    @Override
    public String toString() {
        return "User ID: " + userId + ", Name: " + name + ", Student ID: " + studentId;
    }
}
