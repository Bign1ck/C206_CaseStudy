public class Users {
    private String name;
    private String studentId;

    public Users(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Student ID: " + studentId;
    }
}
