package Entities;

public abstract class User implements HasId{
    protected int userID;
    protected String name;
    protected String email;
    protected String phone;

    public User(int userID, String name, String email, String phone) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}