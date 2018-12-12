package NBA.sportswatch.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import NBA.sportswatch.model.*;

@Repository
public interface AdminRepository extends CrudRepository<Admin, String>{
	// public Admin findByAdminId(String adminId);
	public Admin findByAdminName(String adminName);
}
