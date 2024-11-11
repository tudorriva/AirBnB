public class CancellationPolicy {
    private int policyID;
    private String description;

    public CancellationPolicy(int policyID, String description) {
        this.policyID = policyID;
        this.description = description;
    }

    public int getPolicyID() {
        return policyID;
    }

    public void setPolicyID(int policyID) {
        this.policyID = policyID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void applyPolicy() {

    }
}
