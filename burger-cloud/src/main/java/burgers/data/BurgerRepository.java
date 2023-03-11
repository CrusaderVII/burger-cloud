package burgers.data;

import org.springframework.data.repository.CrudRepository;

import burgers.Burger;

public interface BurgerRepository extends 
	CrudRepository<Burger, Long> {
	 
	public Burger getBurgerByCodeName(String codeName);
	
	public String getDescriptionByCodeName(String codeName);

}
