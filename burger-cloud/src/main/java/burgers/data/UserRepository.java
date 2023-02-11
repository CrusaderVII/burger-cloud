package burgers.data;

import org.springframework.data.repository.CrudRepository;

import burgers.User;

public interface UserRepository extends 
	CrudRepository<User, Long> {
}
