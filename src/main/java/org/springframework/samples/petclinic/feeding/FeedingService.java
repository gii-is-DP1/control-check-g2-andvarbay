package org.springframework.samples.petclinic.feeding;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeedingService {
	
	@Autowired
	private FeedingRepository feedingRepository;
	
    public List<Feeding> getAll(){
    	List<Feeding> result = feedingRepository.findAll();
        return result;
    }

    public List<FeedingType> getAllFeedingTypes(){
    	List<FeedingType> result = feedingRepository.findAllFeedingTypes();
        return result;
    }

    public FeedingType getFeedingType(String typeName) {
    	FeedingType result = feedingRepository.findFeedingTypesByName(typeName);
        return result;
    }

    @Transactional(rollbackFor = UnfeasibleFeedingException.class)
    public Feeding save(Feeding p) throws UnfeasibleFeedingException {
    	PetType tipoMascotaAlimentacion = p.getPet().getType();
    	PetType tipoMascotaAsociadaAUnaAlimentacion  =p.getFeedingType().getPetType();
    	if (tipoMascotaAlimentacion.equals(tipoMascotaAsociadaAUnaAlimentacion)) {
    		return p;
    	} else {
    		throw new UnfeasibleFeedingException();
    	}
    }

    
}
