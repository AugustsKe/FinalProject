package NBA.sportswatch;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import NBA.sportswatch.model.*;
import NBA.sportswatch.repository.*;
import io.netty.handler.codec.base64.Base64;
import io.netty.handler.codec.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;
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
		
		    	//Using PoJo Classes
     return "home";
	}
	// public ModelAndView getTodayGame() {
	// 	ModelAndView showTodayGames = new ModelAndView("/");
	// 	// showTeams.addObject("name", "Human"); 
	// 	ArrayList<HashMap<String, String>> todayGameDetails = new ArrayList<HashMap<String, String>>();
		
	// 	//Endpoint to call
	// 	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	// 	// Date date = new Date();
	// 	// String dataStr = formatter.format(date);
	// 	// String url ="https://api.mysportsfeeds.com/v1.2/pull/nba/2018-2019-regular/daily_game_schedule.json?fordate="+formatter.format(date);
	// 	String url = "https://api.mysportsfeeds.com/v1.2/pull/nba/2018-2019-regular/scoreboard.json?fordate==20181211"; //+ dataStr;
	// 	//Encode Username and Password
    //     String encoding = Base64.getEncoder().encodeToString("0ef69b87-a5cd-447e-8237-855a5f:QfFx6Rjta8KFHyq".getBytes());
    //     // TOKEN:PASS
    //     //Add headers
	// 	HttpHeaders headers = new HttpHeaders();
	// 	headers.setContentType(MediaType.APPLICATION_JSON);
	// 	headers.set("Authorization", "Basic "+encoding);
	// 	HttpEntity<String> request = new HttpEntity<String>();

	// 	//Make the call
	// 	RestTemplate restTemplate = new RestTemplate();
	// 	ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
	// 	String str = response.getBody(); 
	// 	ObjectMapper mapper = new ObjectMapper();
	// 	try {
	// 		JsonNode root = mapper.readTree(str);
	// 		System.out.println(str);
	// 		//JsonNode jsonNode1 = actualObj.get("lastUpdatedOn");
	//         System.out.println(root.get("scoreboard").get("lastUpdatedOn").asText());
	//         // System.out.println(root.get("teamgamelogs").get("gamelogs").getNodeType());
	//         JsonNode gamelogs = root.get("scoreboard").get("gameScore");
	        
	//         if(gamelogs.isArray()) {
	        	
	//         	gamelogs.forEach(gamelog -> {
	//         		HashMap<String,String> gameDetail = new HashMap<String, String>();
	//         		gameDetail.put("id", gamelog.get("game").get("ID").asText());
	//         		gameDetail.put("date", gamelog.get("game").get("date").asText());
    //                 gameDetail.put("time", gamelog.get("game").get("time").asText());
    //                 gameDetail.put("homeTeam", gamelog.get("game").get("homeTeam").get("Abbreviation").asText());
	// 				gameDetail.put("awayTeam", gamelog.get("game").get("awayTeam").get("Abbreviation").asText());
	// 				gameDetail.put("homeScore", gamelog.get("homeScore").asText());
	// 				gameDetail.put("awayScore", gamelog.get("awayScore").asText());
	//         		todayGameDetails.add(gameDetail);
	        		
	//         	});
	//         }
	// 	} catch (IOException e) {
	// 		// TODO Auto-generated catch block
	// 		e.printStackTrace();
	// 	}
	 
	// 	showTodayGames.addObject("todayGameDetails", todayGameDetails);
		        
	// 	return showTodayGames;
	// }

   
	
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