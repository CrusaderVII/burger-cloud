package burgers.data;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import burgers.Order;
import burgers.User;

public interface UserRepository extends 
	CrudRepository<User, Long> {
	
	@Query(value = "SELECT COUNT(*) FROM users WHERE name = :#{#user.userName}", nativeQuery = true)
	public Integer userIsExisting (@Param("user") User user);
	
	@Query(value = "SELECT COUNT(*) FROM users WHERE name = :userName", nativeQuery = true)
	public Integer userIsExistingByName (@Param("userName") String userName);
	
	@Query(value = "SELECT password FROM users WHERE name = :#{#user.userName}", nativeQuery = true)
	public String getUserPassword (@Param("user") User user);
	
	public User getUserByUserName(String name);
	
	public User getUserById(Long id);
	
	public Iterable<Order> getAllById(int userId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE users SET email_is_verified=true WHERE name=:userName", nativeQuery = true)
	public void emailConfirm(String userName);
}
