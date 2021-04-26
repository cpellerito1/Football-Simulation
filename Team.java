
public class Team {
	String homeCoach;
	String awayCoach;
	int homeTouchDowns;
	int awayTouchDowns;
	int homeSkillWins;
	int awaySkillWins;
	
	Team(){
	}
	
	Team(String homeCoach, String awayCoach){
		this.homeCoach = homeCoach;
		this.awayCoach = awayCoach;
	}
	
	String getHomeCoach() {
		return homeCoach;
	}
	
	void setHomeCoach(String name) {
		this.homeCoach = name;
	}
	
	String getAwayCoach() {
		return awayCoach;
	}
	
	void setAwayCoach(String name) {
		this.awayCoach = name;
	}
	
	int getHomeTouchDowns(){
		return homeTouchDowns;
	}
	
	int getAwayTouchDowns() {
		return awayTouchDowns;
	}
	
	int getHomeSkillWins() {
		return homeSkillWins;
	}
	
	int getAwaySkillWins() {
		return awaySkillWins;
	}
	
}
