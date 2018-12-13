package NBA.sportswatch.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

// import antlr.collections.List;

import javax.persistence.Id;

@Entity
//@Table(name = "user")
public class User {
	
//	@Column(name = "userId")
	@Id
	private String userId;
	
//	@Column(name = "userName")

	private String userName;
//	@Column(name = "favTeam")
	private ArrayList<Team> favTeam;
	private String FT;

//	@Column(name = "lastLogin")
	private String lastLogin;
	private String status="Active";
	
	
	
	public  String getUserId() {
		return userId;
	}
	
	public String setUserId(String userId) {
		return this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String setUserName(String userName) {
		return this.userName = userName;
	}
	
	public ArrayList<Team> getFavTeam() {
		return favTeam;
	}
	public void setFavTeam(ArrayList<Team> favTeam) {
		this.favTeam = favTeam;
	}

	public String getFT()
	{
		return FT;
	}

	public String setFT(String FT){
		return this.FT = FT;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status = status;
	}
	
	public String getLastLogin() {
		return lastLogin;
	}
	public String setLastLogin(String lastLogin) {
		return this.lastLogin = lastLogin;
	}
	
}

