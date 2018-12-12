package NBA.sportswatch;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import NBA.sportswatch.model.*;
import NBA.sportswatch.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

// import NBA.sportswatch.model.*;
// import NBA.sportswatch.repository.*;
// import NBA.sportswatch.service.*;


@Controller
public class UserController {


    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/userLogin")
    public String login(){
        return "userlogin";
    }

	@PostMapping("/userLogin")
    public String userLogin(@RequestParam("userID") String userID, @RequestParam("userName") String userName,
    HttpSession session){
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println(userID + " "+userName);
        session.setAttribute("currUserID", userID);
        User loginUser = userRepository.findByUserId(userID);       
        if(loginUser==null){
            User newUser = new User();   
            newUser.setUserId(userID);
            newUser.setUserName(userName);
            userRepository.save(newUser);
            return "redirect:/favTeam";
        } 
        if(loginUser.getStatus().equals("Blocked")){
            session.removeAttribute("currUserID");
            return "blocked";
        }  
		return "redirect:/";
    }
    
    @GetMapping("/userLogout")
    public String userLogout(HttpSession session){
        session.removeAttribute("currUserID");
        return "userlogin";
    }

  
	}