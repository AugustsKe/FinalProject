package NBA.sportswatch.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import NBA.sportswatch.model.User;

// @Repository("UserRepository")
@Repository
public interface UserRepository extends CrudRepository<User, String>{	
	public User findByUserId(String userId);	
}
