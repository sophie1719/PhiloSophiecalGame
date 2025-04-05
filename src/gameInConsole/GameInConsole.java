package gameInConsole;

import java.util.ArrayList;
import java.util.Scanner;

import hero.*;
import attack.*;
import defence.*;
import player.Player;
import logHandler.OperationLog;

public class GameInConsole {
	public int numPlayers = 4;
	public int numHumanPlayers;
	public int difficultyLevel;
	public Player[] players;
	public ArrayList<Player> playersAlive;
	public Scanner scanner = new Scanner(System.in);
	public String logFileName;
	
	
	// constructor that actually plays the game
	public GameInConsole() {
		logFileName = OperationLog.logFileNameGenerator();
		playersAlive = new ArrayList<Player>();
		this.askNumPlayers();
		this.playerSetUp();
		this.addMachines();
		this.gameLoop();
		OperationLog.finalLine(logFileName);
		scanner.close();
	}
	
	
	// setup methods
	
	private void askNumPlayers() {
		new OperationLog("PLAYERS SETUP\n\n", logFileName);
		do {
			System.out.print("Please, provide the number of human players, 1 - 4: ");
			this.numHumanPlayers = Integer.parseInt(scanner.nextLine());
		}
		while (this.numHumanPlayers < 1 || this.numHumanPlayers > 4);
		players = new Player[numPlayers];
		new OperationLog("Number of human players: " + numHumanPlayers + "\n", logFileName);
	}
	
	private void playerSetUp() {
		ArrayList<String> nicknames = new ArrayList<String>();
		for (int i = 0; i < numHumanPlayers; i++) {
			String nickname = chooseNickname(i, nicknames);
			Hero hero = chooseHeroInGame();
			Weapon weapon = chooseWeaponInGame();
			Shield shield = chooseShieldInGame();
			this.players[i] = new Player(nickname, hero, weapon, shield);
			this.players[i].setId(i);
			this.playersAlive.add(players[i]);
			OperationLog.setupLogs(players[i], i, logFileName);
			nicknames.add(nickname);
		}
	}
	
	private String chooseNickname(int i, ArrayList<String> nicknames) {
		String nickname;
		System.out.print("Player" + Integer.toString(i+1) + ", please, introduce yourself: ");
		nickname = scanner.nextLine();
		boolean nicknameIsFree = true;
		for (int j = 0; j < nicknames.size(); j++) {
			if (nicknames.get(j).equals(nickname)) {
				nicknameIsFree = false;
			}
		}
		while (!nicknameIsFree) {
			System.out.print("Sorry, this nickname is already taken. Choose another: ");
			nickname = scanner.nextLine();
			nicknameIsFree = true;
			for (int j = 0; j < nicknames.size(); j++) {
				if (nicknames.get(j).equals(nickname)) {
					nicknameIsFree = false;
				}
			}
		}
		
		System.out.print("Hello, " + nickname + "! ");
		return nickname;
	}
	
	private Hero chooseHeroInGame() {
		int option = 0;
		do {
			System.out.print("Please, choose your hero:\n" +
							 "1. " + HeroKid.fancyName + " — " + HeroKid.fancyDescription + "\n" + 
							 "2. " + HeroAdult.fancyName + " — " + HeroAdult.fancyDescription + "\n" + 
							 "3. " + HeroElder.fancyName + " — " + HeroElder.fancyDescription + "\n");
			option = Integer.parseInt(scanner.nextLine());
		}
		while (option < 1 || option > 3);
		System.out.print("Good! ");
		return Hero.chooseHero(option);
	}
	
	private Weapon chooseWeaponInGame() {
		int option = 0;
		do {
			System.out.print("Now, please, choose your way to attack the world around you:\n" +
							 "1. " + WeaponAmbitions.fancyName + 
							 " — " + WeaponAmbitions.fancyDescription + "\n" + 
							 "2. " + WeaponCuriosity.fancyName + 
							 " — " + WeaponCuriosity.fancyDescription + "\n" + 
							 "3. " + WeaponRelations.fancyName + 
							 " — " + WeaponRelations.fancyDescription + "\n");
			option = Integer.parseInt(scanner.nextLine());
		}
		while (option < 1 || option > 3);
		System.out.print("Great! ");
		return Weapon.chooseWeapon(option);
	}
	
	private Shield chooseShieldInGame() {
		int option = 0;
		do {
			System.out.print("Now, please, choose your way to defend from the world:\n" +
							 "1. " + ShieldEscapism.fancyName + 
							 " — " + ShieldEscapism.fancyDescription + "\n" + 
							 "2. " + ShieldAdaptability.fancyName + 
							 " — " + ShieldAdaptability.fancyDescription + "\n" + 
							 "3. " + ShieldOvercoming.fancyName + 
							 " — " + ShieldOvercoming.fancyDescription + "\n");
			option = Integer.parseInt(scanner.nextLine());
		}
		while (option < 1 || option > 3);
		System.out.print("Perfect!\n");
		return Shield.chooseShield(option);
	}
	
	private void addMachines() {
		numPlayers = numHumanPlayers;
		if (numPlayers == 1) {
			System.out.println("Playing this game alone is ultimately boring: you will win instantly.\n");
			Player machine = Player.generateMachine(1, logFileName);
			this.players[1] = machine;
			this.playersAlive.add(machine);
			numPlayers++;
			System.out.println("Here, I've added one machine opponent for you.");
		}
		while (numPlayers < 4) {
			System.out.println("Do you want to add another machine player? y/n");
			// everything other then 'y' is considered as "no" here
			if (scanner.nextLine().equals("y")) {
				Player machine = Player.generateMachine(numPlayers, logFileName);
				this.players[numPlayers] = machine;
				this.playersAlive.add(machine);
				numPlayers++;
			} else {
				break;
			}
		}
	}
	
	
	// play methods
	
	private void gameLoop() {
		for (;;) {
			if (this.playersAlive.size() == 1) {
				this.playersAlive.get(0).win(logFileName);
				break;
			} else {
				Player activePlayer = this.playersAlive.remove(0); // temporarily removing the first player in the queue
				turn(activePlayer);
				this.playersAlive.add(activePlayer); // then adding it back to the end of the queue
			}
		}
	}
	
	private void turn(Player activePlayer) {
		activePlayer.setIsActive(true);
		activePlayer.getShield().setIsActive(false); // this way the shield is up until it's again your turn
		Player target = chooseTarget(activePlayer);
		if (target.equals(activePlayer)) {
			activePlayer.getShield().setIsActive(true);
			new OperationLog("Action: " + activePlayer.toString() + " raises the shield.\n", logFileName);
		} else {
			target.getStruck(activePlayer.strike());
			new OperationLog("Action: " + activePlayer.toString() + " hits " + target.toString() + ".\n", logFileName);
			if (target.getHP() <= 0) {
				target.die(logFileName);
				this.playersAlive.remove(target);
			} else {
				OperationLog.hpLog(target, logFileName);
			}
		}
		activePlayer.setIsActive(false);
	}
	
	private Player chooseTarget(Player activePlayer) {
		int numOtherPlayersAlive = this.playersAlive.size();
		int option = -1;
		if (activePlayer.getIsMachine()) {
			option = activePlayer.chooseOptionRandomlyWithDelay(numOtherPlayersAlive + 1);
		} else {
			do {
				System.out.format(activePlayer.toString() + ", choose your action for this round:\n" +
								   "0. Raise your shield (your HP: %.2f)\n", activePlayer.getHP());
				for (int i = 0; i < numOtherPlayersAlive; i++) {
					System.out.format(Integer.toString(i+1) + ". Strike " + playersAlive.get(i).getNickname() + 
										" (their HP: %.2f)\n", playersAlive.get(i).getHP());
				}
				option = Integer.parseInt(scanner.nextLine());
			} while (option < 0 || option > numOtherPlayersAlive);
		}
		if (option == 0) {
			return activePlayer;
		} else {
			return playersAlive.get(option - 1);
		}
	}
}
