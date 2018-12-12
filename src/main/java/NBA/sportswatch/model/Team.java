package NBA.sportswatch.model;

import javax.imageio.IIOImage;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
public class Team {
	
	@Id
	private String teamID;
	private String teamName;
	private String teamCity;
	private String teamAbbreviation;

	public Team() {}

	public Team(String ID, String City, String Name, String Abbreviation){
		this.teamID = ID;
		this.teamName = Name;
		this.teamCity = City;
		this.teamAbbreviation = Abbreviation;
	}

	// private List	schedule;
	
	// private List	history;
	
	// private IIOImage teamLogo;

	public String getTeamID() {
		return teamID;
	}
	public String setTeamID(String teamID)
	{
		return this.teamID = teamID;
	}

	public String getTeamName(){
		return teamName;
	}
	public void setTeamName(String teamName){
		this.teamName = teamName;
	}

	public String getTeamCity(){
		return this.teamCity;
	}
	public void setTeamCity(String teamCity){
		this.teamCity = teamCity;
	}

	public String getTeamAbbreviation(){
		return teamAbbreviation;
	}
	public void setTeamAbbreviation(String teamAbbreviation){
		this.teamAbbreviation = teamAbbreviation;
	}

	// public List getSchedule() {
	// 	return schedule;
	// }
	// public List setSchedule(List schedule) {
	// 	return this.schedule = schedule;
	// }
	
	// public List getHistory()
	// {
	// 	return history;
	// }
	// public List setHistory(List history)
	// {
	// 	return this.history = history;
	// }
	
	// public IIOImage getTeamLogo()
	// {
	// 	return teamLogo;

	// }
	// public IIOImage setTeamLogo(IIOImage teamLogo)
	// {
	// 	return this.teamLogo = teamLogo;
	// }

}
