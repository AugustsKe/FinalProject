package NBA.sportswatch.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
// import antlr.collections.List;
import javax.persistence.Entity;


@Entity
//@Table(name = "admin")
public class Admin {
	
	// @GeneratedValue(strategy = GenerationType.AUTO)
	private String adminId;

	
//	@Column(name = "adminName")
	@Id
	private String adminName;

	private String adminPassword;
	
//	@Column(name = "blockList")
	private ArrayList<User> blockList;
	
	public Admin(){}

	public Admin(String adminName, String adminPassword){
		this.adminName = adminName;
		this.adminPassword = adminPassword;
	}

	// public String getId() {
	// 	return adminId;
	// }
	// public void setId(String adminId)
	// {
	// 	this.adminId = adminId;
	// }
	
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword(){
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword){
		this.adminPassword = adminPassword;
	}
	
	public ArrayList<User> getBlockList() {
		return blockList;
	}
	public void setBlockList(ArrayList<User> blockList) {
		this.blockList = blockList;
	}
	
}
