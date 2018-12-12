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
public class AdminController {

	// @Autowired
	// private UserService userService;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

	@GetMapping("/")
    public String indexPage(){
        System.out.println("Go to home");
        return "home";
	}
	
	@GetMapping("/signin")
	public String login(Model model){
		return "login";
	}

	@RequestMapping(value={"/signin"}, method=RequestMethod.POST)
	public String toLogin(@RequestParam("adminName") String adminName, @RequestParam("adminPassword") String adminPassword,
	Model model){
		System.out.println(adminName + " "+adminPassword);
		Admin loggedAdmin = adminRepository.findByAdminName(adminName);
		
		if(loggedAdmin==null || !loggedAdmin.getAdminPassword().equals(adminPassword)){
			return "error";
		}

		model.addAttribute("users", userRepository.findAll());

		return "adminboard";
	}

	@RequestMapping(value={"/adminboard"}, method=RequestMethod.GET)
	public String AdminBoard(Model model){
		model.addAttribute("users", userRepository.findAll());
		return "adminboard";
	}

	@RequestMapping(value={"/blockID"}, method=RequestMethod.GET)
	public String blockUser(@RequestParam("uID") String uID){

		System.out.println(uID);
		User blockUser = userRepository.findByUserId(uID);
		System.out.println(blockUser==null);
		blockUser.setStatus("Blocked");
		userRepository.save(blockUser);
		return "redirect:/adminboard";
	}

	@RequestMapping(value={"/unblockID"}, method=RequestMethod.GET)
	public String unblockUser(@RequestParam("uID") String uID){
		User unblockUser = userRepository.findByUserId(uID);
		unblockUser.setStatus("Active");
		userRepository.save(unblockUser);
		return "redirect:/adminboard";
	}


	

	// @RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
	// public ModelAndView login() {
	// 	 ModelAndView model = new ModelAndView();
		 
	// 	 model.setViewName("user/login");
	// 	 return model;
	// }
	
	// @RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
	// public ModelAndView signup() {
	// 	ModelAndView model = new ModelAndView();
	// 	Admin user = new Admin();
	// 	model.addObject("user", user);
	// 	model.setViewName("user/signup");
		
	// 	return model;
	// }
    
    

	// @RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
	// public ModelAndView createUser(@Valid Admin admin, BindResult bindingResult)
	// {
	// ModelAndView model = new ModelAndView();
	// Admin userExists = adminRepository.findbyAdminId(admin.getId());	
	
	// if (userExists != null) {
	// 	bindingResult.rejectValue("Facebook", "error.user","this user already exist");
		
	// }
	
	// if(bindingResult.hasErrors()) {
	// 	   model.setViewName("user/signup");
	// 	  } else {
	// 	   userService.saveAdmin(admin);
	// 	   model.addObject("msg", "User has been registered successfully!");
	// 	   model.addObject("user", new Admin());
	// 	   model.setViewName("user/signup");
	// 	  }
	// return model;    
	}