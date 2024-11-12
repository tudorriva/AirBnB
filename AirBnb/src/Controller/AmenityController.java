package Controller;

import Entities.Amenity;
import Repository.IRepository;
import Repository.InMemoryRepo;

import java.util.List;

public class AmenityController {
    private final IRepository<Amenity> amenityRepo;

    public AmenityController() {
        this.amenityRepo = new InMemoryRepo<>();
    }

    public void addAmenity(String name, String description) {
        Amenity amenity = new Amenity(0, name, description);  // ID will be assigned by repository
        amenityRepo.create(amenity);
    }

    public List<Amenity> getAllAmenities() {
        return amenityRepo.getAll();
    }

    public void updateAmenity(int id, String name, String description) {
        Amenity amenity = amenityRepo.read(id);
        if (amenity != null) {
            amenity.setName(name);
            amenity.setDescription(description);
            amenityRepo.update(amenity);
        } else {
            throw new IllegalArgumentException("Amenity not found with ID: " + id);
        }
    }

    public void deleteAmenity(int id) {
        amenityRepo.delete(id);
    }
}
