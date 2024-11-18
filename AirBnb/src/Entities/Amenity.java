package Entities;

public class Amenity implements HasId {
    private int amenityID;
    private String name;
    private String description;
    private int propertyID;

    @Override
    public String toString() {
        return "Amenity{" +
                "amenityID=" + amenityID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", propertyID=" + propertyID +
                '}';
    }

    public Amenity(int amenityID, String name, String description, int propertyID) {
        this.amenityID = amenityID;
        this.name = name;
        this.description = description;
        this.propertyID = propertyID;
    }

    public String getDescription() {
        return description;
    }

    public int getAmenityID() {
        return amenityID;
    }

    public void setAmenityID(int amenityID) {
        this.amenityID = amenityID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }


    @Override
    public int getId() {
        return amenityID;
    }

    @Override
    public void setId(int id) {
        this.amenityID = id;
    }
}