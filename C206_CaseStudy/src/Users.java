public class Users {
    private static int nextId = 1;

    private int userId;
    private String name;
    private String studentId;
    private String username;
     private String role;

     public Users(String name, String studentId, String username, String role) { 
        this.userId = nextId++;
        this.studentId = studentId;
        this.username = username;
        this.name = name;
        this.role = role; 
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
    public String getUsername() {
        return username;
    }

    public String getRole() {  // Add the getter for role
        return role;
    }

    public void setRole(String role) {  // Add the setter for role
        this.role = role;
    }

    @Override
    public String toString() {
        return "User ID: " + userId + ", Name: " + name + ", Student ID: " + studentId + ", Role: " + role;
    }
}
