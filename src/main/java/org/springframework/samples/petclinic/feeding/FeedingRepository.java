package org.springframework.samples.petclinic.feeding;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface FeedingRepository extends CrudRepository<Feeding, Integer>{
    List<Feeding> findAll();
    
    @Query("SELECT f FROM FeedingType f")
    public List<FeedingType> findAllFeedingTypes();
    
    @Query("SELECT f FROM FeedingType f WHERE f.name LIKE :name")
    public FeedingType findFeedingTypesByName(@Param("name") String name);
    
    Optional<Feeding> findById(int id);
    Feeding save(Feeding p);
}
