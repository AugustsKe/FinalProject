package NBA.sportswatch;

import java.util.Base64;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import NBA.sportswatch.repository.AdminRepository;
import NBA.sportswatch.repository.UserRepository;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MainController{

    // @Autowired
    // private AdminRepository adminRepository;

    // @Autowired
    // private UserRepository userRepository;



    @GetMapping("/favTeam")
    public String selFavTeams(){
        return "favoriteteam";
    }

    // @GetMapping("/standing")
    // public String goStanding(){
    //     return "standing";
    // } 

    	//Using PoJo Classes
	@GetMapping("/")
	public ModelAndView getTodayGame() {
		ModelAndView showTodayGames = new ModelAndView("/");
		// showTeams.addObject("name", "Human"); 
		ArrayList<HashMap<String, String>> todayGameDetails = new ArrayList<HashMap<String, String>>();
		
		//Endpoint to call
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String dataStr = formatter.format(date);
		// String url ="https://api.mysportsfeeds.com/v1.2/pull/nba/2018-2019-regular/daily_game_schedule.json?fordate="+formatter.format(date);
		String url = "https://api.mysportsfeeds.com/v1.2/pull/nba/2018-2019-regular/scoreboard.json?fordate==20181211"; //+ dataStr;
		//Encode Username and Password
        String encoding = Base64.getEncoder().encodeToString("0ef69b87-a5cd-447e-8237-855a5f:QfFx6Rjta8KFHyq".getBytes());
        // TOKEN:PASS
        //Add headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Basic "+encoding);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		//Make the call
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
		String str = response.getBody(); 
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode root = mapper.readTree(str);
			System.out.println(str);
			//JsonNode jsonNode1 = actualObj.get("lastUpdatedOn");
	        System.out.println(root.get("scoreboard").get("lastUpdatedOn").asText());
	        // System.out.println(root.get("teamgamelogs").get("gamelogs").getNodeType());
	        JsonNode gamelogs = root.get("scoreboard").get("gameScore");
	        
	        if(gamelogs.isArray()) {
	        	
	        	gamelogs.forEach(gamelog -> {
	        		HashMap<String,String> gameDetail = new HashMap<String, String>();
	        		gameDetail.put("id", gamelog.get("game").get("ID").asText());
	        		gameDetail.put("date", gamelog.get("game").get("date").asText());
                    gameDetail.put("time", gamelog.get("game").get("time").asText());
                    gameDetail.put("homeTeam", gamelog.get("game").get("homeTeam").get("Abbreviation").asText());
					gameDetail.put("awayTeam", gamelog.get("game").get("awayTeam").get("Abbreviation").asText());
					gameDetail.put("homeScore", gamelog.get("homeScore").asText());
					gameDetail.put("awayScore", gamelog.get("awayScore").asText());
	        		todayGameDetails.add(gameDetail);
	        		
	        	});
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
		showTodayGames.addObject("todayGameDetails", todayGameDetails);
		        
		return showTodayGames;
	}
	

	@GetMapping("/standing")
	public ModelAndView getTeams() {
		ModelAndView showTeams = new ModelAndView("standing");
		// showTeams.addObject("name", "Human"); 
		
		//Endpoint to call
		String url ="https://api.mysportsfeeds.com/v1.2/pull/nba/2018-2019-regular/overall_team_standings.json";
		//Encode Username and Password
        String encoding = Base64.getEncoder().encodeToString("0ef69b87-a5cd-447e-8237-855a5f:QfFx6Rjta8KFHyq".getBytes());
        // TOKEN:PASS
        //Add headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Basic "+encoding);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		//Make the call
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<NBATeamStanding> response = restTemplate.exchange(url, HttpMethod.GET, request, NBATeamStanding.class);
		NBATeamStanding ts = response.getBody(); 
        System.out.println(ts.toString());
		//Send the object to view
        showTeams.addObject("teamStandingEntries", ts.getOverallteamstandings().getTeamstandingsentries());
        
		return showTeams;
	}
	
    
    //Using objectMapper
	@GetMapping("/team")
	public ModelAndView getTeamInfo(@RequestParam("id") String teamID 
			) {
		ModelAndView teamInfo = new ModelAndView("teamInfo");
		ArrayList<HashMap<String, String>> gameDetails = new ArrayList<HashMap<String, String>>();
		String url = "https://api.mysportsfeeds.com/v1.2/pull/nba/2018-2019-regular/team_gamelogs.json?team=" + teamID;
		String encoding = Base64.getEncoder().encodeToString("0ef69b87-a5cd-447e-8237-855a5f:QfFx6Rjta8KFHyq".getBytes());
        
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Basic "+encoding);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
		String str = response.getBody(); 
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode root = mapper.readTree(str);
			System.out.println(str);
			//JsonNode jsonNode1 = actualObj.get("lastUpdatedOn");
	        System.out.println(root.get("teamgamelogs").get("lastUpdatedOn").asText());
	        System.out.println(root.get("teamgamelogs").get("gamelogs").getNodeType());
	        JsonNode gamelogs = root.get("teamgamelogs").get("gamelogs");
	        
	        if(gamelogs.isArray()) {
	        	
	        	gamelogs.forEach(gamelog -> {
	        		JsonNode game = gamelog.get("game");
	        		HashMap<String,String> gameDetail = new HashMap<String, String>();
	        		gameDetail.put("id", game.get("id").asText());
	        		gameDetail.put("date", game.get("date").asText());
	        		gameDetail.put("time", game.get("time").asText());
	        		gameDetail.put("awayTeam", game.get("awayTeam").get("Abbreviation").asText());
	        		gameDetails.add(gameDetail);
	        		
	        	});
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
		teamInfo.addObject("gameDetails", gameDetails);
		
        
		return teamInfo;
    }
    
    @GetMapping("/schedule")
	public ModelAndView getAllSchedule() {

		ModelAndView teamInfo = new ModelAndView("teamschedule");
		ArrayList<HashMap<String, String>> gameDetails = new ArrayList<HashMap<String, String>>();
		String url = "https://api.mysportsfeeds.com/v1.2/pull/nba/2018-2019-regular/full_game_schedule.json";
		String encoding = Base64.getEncoder().encodeToString("0ef69b87-a5cd-447e-8237-855a5f:QfFx6Rjta8KFHyq".getBytes());
        
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Basic "+encoding);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
		String str = response.getBody(); 
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode root = mapper.readTree(str);
			System.out.println(str);
			//JsonNode jsonNode1 = actualObj.get("lastUpdatedOn");
	        System.out.println(root.get("fullgameschedule").get("lastUpdatedOn").asText());
	        // System.out.println(root.get("teamgamelogs").get("gamelogs").getNodeType());
	        JsonNode gamelogs = root.get("fullgameschedule").get("gameentry");
	        
	        if(gamelogs.isArray()) {
	        	
	        	gamelogs.forEach(gamelog -> {
	        		HashMap<String,String> gameDetail = new HashMap<String, String>();
	        		gameDetail.put("id", gamelog.get("id").asText());
	        		gameDetail.put("date", gamelog.get("date").asText());
                    gameDetail.put("time", gamelog.get("time").asText());
                    gameDetail.put("homeTeam", gamelog.get("homeTeam").get("Abbreviation").asText());
	        		gameDetail.put("awayTeam", gamelog.get("awayTeam").get("Abbreviation").asText());
	        		gameDetails.add(gameDetail);
	        		
	        	});
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
		teamInfo.addObject("gameDetails", gameDetails);
		        
		return teamInfo;
	}
}


