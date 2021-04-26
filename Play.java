import java.util.Comparator;

public class Play {
	String name;
	String team;
	String role;
	int skillMin = 50;
	int skillMax = 99;
	int gSkillMin = 50;
	int gSkillMax = 99;
	int number;
	int skillWins;
	
	Play(){
	}
	
	Play(String name, String team, String role, int number){
		this.name = name;
		this.team = team;
		this.role = role;
		this.number = number;
	}
	
	String getName() {
		return name;
	}
	
	void setName(String name){
		this.name = name;
	}
	
	String getTeam() {
		return team;
	}
	
	void setTeam(String team) {
		this.team = team;
	}
	
	String getRole() {
		return role;
	}
	
	void setRole(String role) {
		this.role = role;
	}
	
	int getSkill() {
		return (int) (Math.random() * ((skillMax - skillMin) + 1) + skillMin);
	}
	
	int getSkillWins() {
		return skillWins;
	}
	
	int getGaeSkill() {
		return (int) (Math.random() * ((gSkillMax - gSkillMin) + 1) + gSkillMin);
	}
	
	int getNumber() {
		return number;
	}	
	
	public String toString() {
		return name;
	}
}
