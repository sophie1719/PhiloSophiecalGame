package player;

import java.util.Random;
import attack.Weapon;
import defence.Shield;
import hero.Hero;
import logHandler.OperationLog;

public class Player {
	private String nickname;
	private int id; // used for equals() method
	private Hero hero;
	private Weapon weapon;
	private Shield shield;
	
	private double HP;
	private boolean isDead;
	private boolean isActive;
	private boolean isMachine;
	
	private Random randomGod = new Random(); // creating a local instance of Random class
	
	// constructors
	
	Player(String nickname) {
		this.nickname = nickname;
	}
	
	public Player(String nickname, Hero hero, Weapon weapon, Shield shield) {
		this.nickname = nickname;
		this.hero = hero;
		this.weapon = weapon;
		this.shield = shield;
		this.HP = this.hero.getMaxHealthPoints();
		this.isDead = false;
		this.isMachine = false;
	}
	
	
	// game methods
	
	public double strike() {
		boolean strikeHard = (this.randomGod.nextInt(100) < this.weapon.getCritChance());
		double totalDamageDealt;
		if (strikeHard) {
			totalDamageDealt = this.hero.getBasicDamageDealt() +
						this.weapon.getExtraDamageDealt() * this.weapon.getCritMultiplier();
		} else {
			totalDamageDealt = this.hero.getBasicDamageDealt() + this.weapon.getExtraDamageDealt();
		}
		return totalDamageDealt;
	}
	
	public double getStruck(double damageDealt) {
		boolean reflect;
		double totalDamageTaken;
		if (this.shield.getIsActive()) {
			reflect = (this.randomGod.nextInt(100) < this.shield.getReflectChance());
			if (reflect) {
				totalDamageTaken = 0;
			} else {
				totalDamageTaken = damageDealt * (1 - this.hero.getBasicDamageReduce()
													- this.shield.getExtraDamageReduce());
			}
		} else {
			totalDamageTaken = damageDealt * (1 - this.hero.getBasicDamageReduce());
		}
		this.HP -= totalDamageTaken;
		if (this.HP <= 0) {
			this.isDead = true;
		}
		return totalDamageTaken;
	}
	
	public void die(String logFileName) {
		this.isDead = true;
		new OperationLog(this.nickname + " dies.\n", logFileName);
	}
	
	public void win(String logFileName) {
		new OperationLog(this.nickname + " is the winner.\n", logFileName);
		System.out.println("Congratulations, " + this.nickname + "!!\n");
	}
	
	
	public static Player generateMachine(int id, String logFileName) {
		Random randomGod = new Random();
		String nickname = "Machine#" + id;
		Hero hero = Hero.chooseHero(randomGod.nextInt(1, 4));
		Weapon weapon = Weapon.chooseWeapon(randomGod.nextInt(1, 4));
		Shield shield = Shield.chooseShield(randomGod.nextInt(1, 4));
		Player player = new Player(nickname, hero, weapon, shield);
		player.setId(id);
		player.setIsMachine(true);
		OperationLog.setupLogs(player, id, logFileName);
		return player;
	}
	
	public int chooseOptionRandomlyWithDelay(int numOptions) {
		try {
			Thread.sleep(1000); // simulating that machine "takes its time to choose"
		} catch (InterruptedException e) {
			System.err.println(e);
		}
//		int delay = 1000; // number of milliseconds to sleep
//		long start = System.currentTimeMillis();
//      while(start >= System.currentTimeMillis() - delay); // do nothing
		return this.randomGod.nextInt(numOptions);
	}
	
	public int chooseOptionRandomly(int numOptions) {
		return this.randomGod.nextInt(numOptions);
	}
	
	public static int chooseOptionRandomlyStatic(int numOptions) {
		Random randomGod = new Random(); // creating a local (for this static function) instance of Random class
		return randomGod.nextInt(numOptions);
	}
	
	
	// getters

	public String getNickname() {
		return this.nickname;
	}
	
	public int getId() {
		return this.id;
	}

	public Hero getHero() {
		return this.hero;
	}

	public Weapon getWeapon() {
		return this.weapon;
	}

	public Shield getShield() {
		return this.shield;
	}
	
	public double getHP() {
		return this.HP;
	}

	public boolean getIsDead() {
		return this.isDead;
	}
	
	public boolean getIsActive() {
		return this.isActive;
	}
	
	public boolean getIsMachine() {
		return this.isMachine;
	}

	// setters

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public void setShield(Shield shield) {
		this.shield = shield;
	}
	
	public void setHP(double HP) {
		this.HP = HP;
	}

	public void setIsDead(boolean isDead) {
		this.isDead = isDead;
	}
	
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public void setIsMachine(boolean isMachine) {
		this.isMachine = isMachine;
	}

	
	
	public String toString() {
		return this.nickname;
	}
	
	public String toStringLong() {
		return this.nickname + ", "
			 + this.hero.toString() + " with "
			 + this.weapon.toString() + " and "
			 + this.shield.toString();
	}
	
	public boolean equals(Player otherPlayer) {
		return this.id == otherPlayer.getId();
	}

}
