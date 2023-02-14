package burgers.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import burgers.Order;
import burgers.User;

public interface OrderRepository extends 
	CrudRepository<Order, Long> {
	
	public Order getOrderById(Long id);
	
	
	
}
