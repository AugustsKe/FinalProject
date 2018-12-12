package NBA.sportswatch.dataloader;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import NBA.sportswatch.repository.AdminRepository;
import NBA.sportswatch.repository.TeamRepository;
import NBA.sportswatch.model.*;


@Component
public class Loader implements CommandLineRunner{
    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private TeamRepository teamRepo;

    @Override
    public void run(String... strings) throws Exception{
        // loadAdmins();
        // loadTeams();
    }

    private void loadAdmins(){
        Admin admin1 = new Admin("admin1", "password1");
        Admin admin2 = new Admin("admin2", "password2");
        adminRepo.save(admin1);
        adminRepo.save(admin2);
    }

    private void loadTeams(){
        ArrayList<Team> allTeams = new ArrayList<>();
        allTeams.add(new Team("81", "Toronto", "Raptors", "TOR"));
        allTeams.add(new Team("99", "Denver", "Nuggets", "DEN"));
        allTeams.add(new Team("90", "Milwaukee", "Bucks", "MIL"));
        allTeams.add(new Team("101", "Golden State", "Warriors", "GSW"));
        allTeams.add(new Team("96", "Oklahoma City", "Thunder", "OKL"));

        // more teams needs to be added

        for(Team t : allTeams){
            teamRepo.save(t);
        }
    }
}

