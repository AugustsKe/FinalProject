package NBA.sportswatch;

import javax.validation.Valid;
import NBA.sportswatch.model.*;
import NBA.sportswatch.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

// import NBA.sportswatch.model.*;
// import NBA.sportswatch.repository.*;
// import NBA.sportswatch.service.*;


@Controller
@RequestMapping(path="/db")
public class DBController {

	@Autowired
	private UserRepository userRepo;
    
    @Autowired
    private TeamRepository teamRepo;

    @Autowired
    private AdminRepository adminRepo;

    @RequestMapping(value={"/allusers"}, method=RequestMethod.GET)
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepo.findAll();
    }

    @RequestMapping(value="/allteams", method=RequestMethod.GET)
    public @ResponseBody Iterable<Team> getAllTeams() {
        return teamRepo.findAll();
    }

    @RequestMapping(value="/alladmins", method=RequestMethod.GET)
    public @ResponseBody Iterable<Admin> getAllAdmins() {
        return adminRepo.findAll();
    }
    
    @RequestMapping(value={"/addAdmin"}, method=RequestMethod.GET)
	public @ResponseBody String addAdmin(@RequestParam String adminName, @RequestParam String adminPassword){
		Admin admin = new Admin();
		admin.setAdminName(adminName);
		admin.setAdminPassword(adminPassword);
		adminRepo.save(admin);
		return "saved";
	}

}