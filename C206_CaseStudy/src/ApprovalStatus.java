public class ApprovalStatus {
    private String status;

    public ApprovalStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Status: " + status;
    }
}