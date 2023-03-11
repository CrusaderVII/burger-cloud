package burgers.data;

import org.springframework.data.repository.CrudRepository;

import burgers.CreditCard;

public interface CreditCardRepository extends 
	CrudRepository<CreditCard, Long>{

}
