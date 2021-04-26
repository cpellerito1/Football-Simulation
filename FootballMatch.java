import java.util.ArrayList;
import java.util.Arrays;

public class FootballMatch {

	public static void main(String[] args) {
		//Create the game
		Game sim = new Game("Dallas Cowboys", "New York Giants", "Dallas", "4:25 PM");
		Team teams = new Team("Jason Garret", "Pat Shurmur");
		
		//add players
		addPlayers(sim);
		
		//simulate coin toss to see which team starts with the ball
		int coinToss = (int) (Math.random () * ((2-1) + 1)) + 1;
		
		if (coinToss == 1)
			//loop that runs the simulation starting with the home offense
			//for 135 plays which is the avg plays in an NFL game
			while (sim.getPlays() < 135) {
				//runs the head to head skill matchups and tracks the outcomes of the plays
				homeOffense(sim, teams);
				awayOffense(sim, teams);
			}
		else
			//loop that runs the simulation starting with away offense
			while (sim.getPlays() < 135) {
				awayOffense(sim, teams);
				homeOffense(sim, teams);
			}
		
		//checks the score of the game and prints out the winner and stats for the game
		checkWinner(sim, teams);
	}
	
		
	
	
	
	//method that sims the home teams offensive drive
	static void homeOffense(Game sim, Team teams) {
		int stops = 0;
		int yards = 0;
		
		//runs the simulation until the defense wins 5 plays
		while (stops <= 4) {
			int driveWins = 0;
			int driveLoss = 0;
			sim.homePlays += 1;
			
			//runs the head to head skill matchups and checks the results
			for (int i = 0; i < 11; i++) {
				//initialize Players from ArrayList
				Player offense = sim.homeOffense.get(i);
				Player defense = sim.awayDefense.get(i);
			
				int offSkill = offense.getSkill();
				int defSkill = defense.getSkill();
				
				//tracks the amount of wins the offense has
				if (offSkill > defSkill) {
					offense.skillWins += 1;
					teams.homeSkillWins += 1;
					driveWins += 1;
				}
				
				//tracks the amount of wins the defense has
				else if (offSkill < defSkill) {
					defense.skillWins += 1;
					teams.awaySkillWins += 1;
					driveLoss += 1;
				}
				
				//tie breaker if the skill values are equal
				else if (offSkill == defSkill) {
					//flip a coin if its 1, the offense wins
					if ((Math.random() * ((2-1) + 1)) + 1 == 1) {
						offense.skillWins += 1;
						teams.homeSkillWins += 1;
						driveWins += 1;
					}
					
					//if its 2 the defense wins
					else {
						defense.skillWins += 1;
						teams.awaySkillWins += 1;
						driveLoss += 1;
					}
				}
			}
			
			//checks the outcome of the skill battles and updates the progress accordingly
			if (driveWins > driveLoss) {
				if (checkDrive(driveWins, driveLoss) == 2)
					yards += 2;
				else if (checkDrive(driveWins, driveLoss) == 4)
					yards += 4;
				else if (checkDrive(driveWins, driveLoss) == 6)
					yards += 6;
				else if (checkDrive(driveWins, driveLoss) == 8)
					yards += 8;
				else if (checkDrive(driveWins, driveLoss) == 10)
					yards += 10;
				else if (checkDrive(driveWins, driveLoss) == 11)
					yards += 18;
			}
			
			else if (driveWins < driveLoss)
				stops += 1;
			
			//checks if the offense scored and prints an updated score if they did
			if (yards >= 18) {
				teams.homeTouchDowns += 1;
				System.out.println("The " + sim.getHomeTeam() + " scored! The score is now " + sim.getHomeTeam() + 
						": " + (teams.getHomeTouchDowns() * 7) + " " + sim.getAwayTeam() + ": " + (teams.getAwayTouchDowns() * 7));
				break;
			}
		}
	}
	
	//method that simulates away offensive plays
	public static void awayOffense(Game sim, Team teams) {
		int stops = 0;
		int yards = 0;
		
		while (stops <= 4) {
			int driveWins = 0;
			int driveLoss = 0;
			sim.awayPlays += 1;
			
			for (int i = 0; i < 11; i++) {
				Player offense = sim.awayOffense.get(i);
				Player defense = sim.homeDefense.get(i);
				int offSkill = offense.getSkill();
				int defSkill = defense.getSkill();

				if (offSkill > defSkill) {
					offense.skillWins += 1;
					teams.awaySkillWins += 1;
					driveWins += 1;
				}
				
				else if (offSkill < defSkill) {
					defense.skillWins += 1;
					teams.homeSkillWins += 1;
					driveLoss += 1;
				}
				
				else if (offSkill == defSkill) {
					if (((Math.random() + 1) * 2) == 1) {
						offense.skillWins += 1;
						teams.awaySkillWins += 1;
						driveWins += 1;
					}
					
					else {
						defense.skillWins += 1;
						teams.homeSkillWins += 1;
						driveLoss += 1;
					}
				}
			}
			
			if (driveWins > driveLoss) {
				if (checkDrive(driveWins, driveLoss) == 2)
					yards += 2;
				else if (checkDrive(driveWins, driveLoss) == 4)
					yards += 4;
				else if (checkDrive(driveWins, driveLoss) == 6)
					yards += 6;
				else if (checkDrive(driveWins, driveLoss) == 8)
					yards += 8;
				else if (checkDrive(driveWins, driveLoss) == 10)
					yards += 10;
				else if (checkDrive(driveWins, driveLoss) == 11)
					yards += 18;
			}
			
			else if (driveWins < driveLoss)
				stops += 1;
			
			if (yards >= 18) {
				teams.awayTouchDowns += 1;
				System.out.println("The " + sim.getAwayTeam() + " scored! The score is now " + sim.getAwayTeam() + 
						": " + (teams.getAwayTouchDowns() * 7) + " " + sim.getHomeTeam() + ": " + (teams.getHomeTouchDowns() * 7));
				break;
			}
		}
	}
	
	//method that checks the progress of the drive
	public static int checkDrive(int win, int loss) {
		//checks the number of wins the offense had and calculates how many yards they get
		if (win - loss == 1 | win - loss == 2)
			return 2;
		else if (win - loss == 3 | win - loss == 4)
			return 4;
		else if (win - loss == 5 | win - loss == 6)
			return 6;
		else if (win - loss == 7 | win - loss == 8)
			return 8;
		else if (win - loss == 9 | win - loss == 10)
			return 10;
		else if (win - loss == 11)
			return 11;
		else
			return 0;
	}
					
	//method that checks winner and prints stats
	public static void checkWinner(Game sim, Team teams){
		//compares the touchdowns scored by each team and ddecides the winner
		if (teams.getHomeTouchDowns() > teams.getAwayTouchDowns()) {
			System.out.println("The " + sim.getHomeTeam() + " win!");
			System.out.println("Score: " + sim.getHomeTeam() + ":" + (teams.getHomeTouchDowns() * 7) 
					+ " " + sim.getAwayTeam() + ":" + (teams.getAwayTouchDowns() * 7));
			System.out.println(sim.getHomeTeam() + " plays: " + sim.getHomePlays());
			System.out.println(sim.getAwayTeam() + " plays: " + sim.getAwayPlays());
			System.out.println(sim.getHomeTeam() + " skill wins: " + teams.getHomeSkillWins());
			System.out.println(sim.getAwayTeam() + " skill wins: " + teams.getAwaySkillWins());
		}
		else if (teams.getHomeTouchDowns() < teams.getAwayTouchDowns()) {
			System.out.println("The " + sim.getAwayTeam() + " win!");
			System.out.println("Score: " + sim.getAwayTeam() + ":" + (teams.getAwayTouchDowns() * 7)
					+ " " + sim.getHomeTeam() + ":" + (teams.getHomeTouchDowns() *7));
			System.out.println(sim.getHomeTeam() + " plays: " + sim.getHomePlays());
			System.out.println(sim.getAwayTeam() + " plays: " + sim.getAwayPlays());
			System.out.println(sim.getHomeTeam() + " skill wins: " + teams.getHomeSkillWins());
			System.out.println(sim.getAwayTeam() + " skill wins: " + teams.getAwaySkillWins());
		}
		else if (teams.getHomeTouchDowns() == teams.getAwayTouchDowns()) {
			System.out.println("Its a tie!");
			System.out.println("Score: " + sim.getHomeTeam() + ":" + " " + (teams.getHomeTouchDowns() * 7)
					+ " " + sim.getAwayTeam() + ":" + (teams.getAwayTouchDowns() * 7));
			System.out.println(sim.getHomeTeam() + " plays: " + sim.getHomePlays());
			System.out.println(sim.getAwayTeam() + " plays: " + sim.getAwayPlays());
			System.out.println(sim.getHomeTeam() + " skill wins: " + teams.getHomeSkillWins());
			System.out.println(sim.getAwayTeam() + " skill wins: " + teams.getAwaySkillWins());
		}
		
		//prints out the MVP of the game
		System.out.println(checkStats(sim, teams));
	}
	
	//method that cheks and returns stats from the game
	public static String checkStats(Game sim, Team teams) {
		//add all players to a new ArrayList
		ArrayList<Player> list = new ArrayList<Player>();
		list.addAll(sim.homeOffense);
		list.addAll(sim.awayOffense);
		list.addAll(sim.homeDefense);
		list.addAll(sim.awayDefense);
		
		//create a temp array to store skill wins in
		int[] temp = new int[44];
		
		//add skill wins to array
		for (int i = 0; i < 44; i++) 
			temp[i] = list.get(i).getSkillWins();
		
		//sort the array to find most skill wins
		Arrays.sort(temp);
		
		//check to see which player had the most skill wins
		for (int j = 0; j < 44; j++)
			if (list.get(j).getSkillWins() == temp[list.size() - 1]) {
				sim.setMVP(list.get(j));
				break;
			}

		return "The MVP is: " + sim.getMVP() + " with " + sim.getMVP().getSkillWins() + " skill wins";
	}
	
	
	//method that adds players to ArrayLists
	public static void addPlayers(Game sim) {
		ArrayList<Player> homeOffense = new ArrayList<Player>();
		ArrayList<Player> homeDefense = new ArrayList<Player>();
		ArrayList<Player> awayOffense = new ArrayList<Player>();
		ArrayList<Player> awayDefense = new ArrayList<Player>();
		
		//create home offensive players
		Player qb_H = new Player("Dak Prescott", sim.getHomeTeam(), "Offense", 40, 95, 4);
		Player rb_H = new Player("Zeke Elliot", sim.getHomeTeam(), "Offense", 55, 99, 21);	
		Player wr1_H = new Player("Amari Cooper", sim.getHomeTeam(), "Offense", 55, 99, 19);
		Player wr2_H = new Player("Michael Gallup", sim.getHomeTeam(), "Offense", 30, 85, 13);
		Player te_H = new Player("Jason Witten", sim.getHomeTeam(), "Offense", 30, 90, 82);
		Player fb_H = new Player("Rod Smith", sim.getHomeTeam(), "Offense", 20, 70, 45);
		Player lt_H = new Player("Tyron Smith", sim.getHomeTeam(), "Offense", 55, 99, 77);
		Player lg_H = new Player("Connor Williams", sim.getHomeTeam(), "Offense", 30, 80, 52);
		Player c_H = new Player("Travis Frederick", sim.getHomeTeam(), "Offense", 40, 90, 72);
		Player rg_H = new Player("Zack Martin", sim.getHomeTeam(), "Offense", 50, 99, 70);
		Player rt_H = new Player("La'el Collins", sim.getHomeTeam(), "Offense", 30, 80, 71);
		
		//add home offense to ArrayList
		homeOffense.add(qb_H);
		homeOffense.add(rb_H);
		homeOffense.add(wr1_H);
		homeOffense.add(wr2_H);
		homeOffense.add(te_H);
		homeOffense.add(fb_H);
		homeOffense.add(lt_H);
		homeOffense.add(lg_H);
		homeOffense.add(c_H);
		homeOffense.add(rg_H);
		homeOffense.add(rt_H);
		sim.homeOffense = homeOffense;
		
		//create home defensive players
		Player de1_H = new Player("Demarcus Lawrence", sim.getHomeTeam(), "Defense", 55, 99, 90);
		Player de2_H = new Player("Randy Gregory", sim.getHomeTeam(), "Defense", 40, 82, 94);
		Player dt1_H = new Player("Maliek Collins", sim.getHomeTeam(), "Defense", 40, 80, 96);
		Player dt2_H = new Player("Antwuan Woods", sim.getHomeTeam(), "Defense", 40, 85, 99);
		Player mlb_H = new Player("Jaylon Smith", sim.getHomeTeam(), "Defense", 55, 99, 54);
		Player olb_H = new Player("Leighton Vander-Esch", sim.getHomeTeam(), "Defense", 50, 95, 55);
		Player cb1_H = new Player("Byron Jones", sim.getHomeTeam(), "Defense", 45, 92, 31);
		Player cb2_H = new Player("Chidobe Awuzie", sim.getHomeTeam(), "Defense", 40, 87, 24);
		Player cb3_H = new Player("Jourdan Lewis", sim.getHomeTeam(), "Defense", 40, 85, 27);
		Player ss_H = new Player("Jeff Heath", sim.getHomeTeam(), "Defense", 30, 85, 38);
		Player fs_H = new Player("Xavier Woods", sim.getHomeTeam(), "Defense", 40, 90, 25);
		
		//add home defense to ArrayList
		homeDefense.add(mlb_H);
		homeDefense.add(olb_H);
		homeDefense.add(cb1_H);
		homeDefense.add(cb2_H);
		homeDefense.add(cb3_H);
		homeDefense.add(fs_H);
		homeDefense.add(de1_H);
		homeDefense.add(dt1_H);
		homeDefense.add(dt2_H);
		homeDefense.add(ss_H);
		homeDefense.add(de2_H);
		sim.homeDefense = homeDefense;
		
		//add away offensive players
		Player qb_A = new Player("Eli Manning", sim.getAwayTeam(), "Offense", 20, 90,  10);
		Player rb_A = new Player("Saquon Barkley", sim.getAwayTeam(), "Offense", 55, 99, 26);
		Player wr1_A = new Player("Sterling Shepard", sim.getAwayTeam(), "Offense", 45, 90, 87);
		Player wr2_A = new Player("Golden Tate III", sim.getAwayTeam(), "Offense", 45, 95, 15);
		Player te_A = new Player("Evan Engram", sim.getAwayTeam(), "Offense", 40, 85, 88);
		Player fb_A = new Player("Wayne Gallman", sim.getAwayTeam(), "Offense", 30, 80, 22);
		Player lt_A = new Player("Nate Solder", sim.getAwayTeam(), "Offense", 50, 95, 76);
		Player lg_A = new Player("Will Hernandez", sim.getAwayTeam(), "Offense", 50, 92, 71);
		Player c_A = new Player("Jon Halapio", sim.getAwayTeam(), "Offense", 20, 60, 75);
		Player rg_A = new Player("Kevin Zietler", sim.getAwayTeam(), "Offense", 50, 95, 70);
		Player rt_A = new Player("Chad Wheeler", sim.getAwayTeam(), "Offense", 20, 80, 63);
		
		//add away offense to ArrayList
		awayOffense.add(qb_A);
		awayOffense.add(rb_A);
		awayOffense.add(wr1_A);
		awayOffense.add(wr2_A);
		awayOffense.add(te_A);
		awayOffense.add(fb_A);
		awayOffense.add(lt_A);
		awayOffense.add(lg_A);
		awayOffense.add(c_A);
		awayOffense.add(rg_A);
		awayOffense.add(rt_A);
		sim.awayOffense = awayOffense;
		
		//add defensive players from other team
		Player de1_A = new Player("Olivier Vernon", sim.getAwayTeam(), "Defense", 55, 99, 54);
		Player de2_A = new Player("Dexter Lawrence", sim.getAwayTeam(), "Defense", 40, 80, 90);
		Player dt1_A = new Player("Dalvin Tomlinson", sim.getAwayTeam(), "Defense", 45, 85, 94);
		Player dt2_A = new Player("B.J. Hill", sim.getAwayTeam(), "Defense", 50, 85, 95);
		Player mlb_A = new Player("Alec Ogeltree", sim.getAwayTeam(), "Defense", 40, 85, 52);
		Player olb_A = new Player("B.J. Goodson", sim.getAwayTeam(), "Defense", 30, 85, 93);
		Player cb1_A = new Player("Janoris Jenkins", sim.getAwayTeam(), "Defense", 55, 99, 20);
		Player cb2_A = new Player("B.W. Webb", sim.getAwayTeam(), "Defense", 20, 70, 23);
		Player cb3_A = new Player("Grant Haley", sim.getAwayTeam(), "Defense", 30, 80, 34);
		Player ss_A = new Player("Landon Collins", sim.getAwayTeam(), "Defense", 55, 99, 21);
		Player fs_A = new Player("Curtis Riley", sim.getAwayTeam(), "Defense", 30, 80, 35);
		
		//add away defense to ArrayList
		awayDefense.add(mlb_A);
		awayDefense.add(olb_A);
		awayDefense.add(cb1_A);
		awayDefense.add(cb2_A);
		awayDefense.add(cb3_A);
		awayDefense.add(fs_A);
		awayDefense.add(de1_A);
		awayDefense.add(dt1_A);
		awayDefense.add(dt2_A);
		awayDefense.add(ss_A);
		awayDefense.add(de2_A);
		sim.awayDefense = awayDefense;
	}
}