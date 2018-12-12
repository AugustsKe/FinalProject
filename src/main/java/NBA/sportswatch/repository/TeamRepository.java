package NBA.sportswatch.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import NBA.sportswatch.model.Team;

// @Repository("TeamRepository")
@Repository
public interface TeamRepository extends CrudRepository<Team, String>{
	
	public Team findByTeamID(String teamID);
	
	 

}