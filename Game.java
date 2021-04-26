import java.util.ArrayList;

public class Game extends Player {
	String homeTeam;
	String awayTeam;
	String location;
	String startTime;
	String endTime;
	Player MVP;
	int homePlays;
	int awayPlays;
	ArrayList<Player> homeOffense;
	ArrayList<Player> homeDefense;
	ArrayList<Player> awayOffense;
	ArrayList<Player> awayDefense;
		
	Game(){
	}
	
	Game(String homeTeam, String awayTeam, String location, String startTime){
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.location = location;
		this.startTime = startTime;
	}
	
	String getHomeTeam() {
		return homeTeam;
	}
	
	void setHomeTeam(String team) {
		this.homeTeam = team;
	}
	
	String getAwayTeam() {
		return awayTeam;
	}
	
	void setAwayTeam(String team) {
		this.awayTeam = team;
	}
	
	String getLocation() {
		return location;
	}
	
	void setLocation(String location) {
		this.location = location;
	}
	
	String getStartTime() {
		return startTime;
	}
	
	void setStartTime(String time) {
		this.startTime = time;
	}
	
	String getEndTime() {
		return endTime;
	}
	
	void setEndTime(String time) {
		this.endTime = time;
	}
	
	int getPlays() {
		return homePlays + awayPlays;
	}
	
	Player getMVP() {
		return MVP;
	}
	
	void setMVP(Player name) {
		this.MVP = name;
	}
	
	int getHomePlays() {
		return homePlays;
	}
	
	int getAwayPlays() {
		return awayPlays;
	}
}
