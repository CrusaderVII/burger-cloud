package burgers.data;

import org.springframework.data.repository.CrudRepository;

import burgers.Burger;
import burgers.Reference;

public interface ReferenceRepository extends 
	CrudRepository<Reference, Long> {
	

}
