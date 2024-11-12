package Entities;

import Repository.HasId;

public class CancellationPolicy implements HasId {
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

    @Override
    public int getId() {
        return policyID;
    }

    @Override
    public String toString() {
        return "CancellationPolicy{" +
                "policyID=" + policyID +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public void setId(int id) {
        this.policyID = id;
    }
}
