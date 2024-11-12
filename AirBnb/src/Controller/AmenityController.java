package Controller;

import Entities.Amenity;
import java.util.ArrayList;
import java.util.List;

public class AmenityController {
    private List<Amenity> amenities;

    public AmenityController() {
        amenities = new ArrayList<>();
    }

    public void addAmenity(Amenity amenity) {
        amenities.add(amenity);
    }

    public List<Amenity> getAllAmenities() {
        return amenities;
    }

    public void updateAmenity(int id, String name, String description) {
        for (Amenity amenity : amenities) {
            if (amenity.getAmenityID() == id) {
                amenity.setName(name);
                amenity.setDescription(description);
                break;
            }
        }
    }
    /////////////////////////////////////////////////////////

    public void deleteAmenity(int id) {
        amenities.removeIf(amenity -> amenity.getAmenityID() == id);
    }
}
