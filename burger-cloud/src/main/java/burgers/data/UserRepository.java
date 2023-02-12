package burgers.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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
	
	
	
}
