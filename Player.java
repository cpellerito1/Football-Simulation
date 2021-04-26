
public class Player {
	String name;
	String team;
	String role;
	int skillMin;
	int skillMax;
	int number;
	int skillWins;
	
	Player(){
	}
	
	Player(String name, String team, String role, int skillMin, int skillMax, int number){
		this.name = name;
		this.team = team;
		this.role = role;
		this.skillMin = skillMin;
		this.skillMax = skillMax;
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
	
	int getNumber() {
		return number;
	}	
	
	int getSkillWins() {
		return skillWins;
	}
	
	public String toString() {
		return name;
	}
	
	String description() {
		return "Name: " + name + " Role: " + role + " Skill: " + skillMin + " - " + skillMax + " Number: " + number;
	}
}
