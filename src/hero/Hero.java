package hero;

import player.Modifiable;

public abstract class Hero implements Modifiable {
	protected double basicDamageDealt;
	protected double basicDamageReduce;
	protected double maxHealthPoints;
	public static String fancyName = "Hero";
	public static String fancyDescription = "";
	
	// constructor
	Hero(double basicDamageDealt, double basicDamageReduce, double maxHealthPoints) {
		this.basicDamageDealt = basicDamageDealt;
		this.basicDamageReduce = basicDamageReduce;
		this.maxHealthPoints = maxHealthPoints;
	}
	
	// polymorphic function that chooses the hero
	public static Hero chooseHero(int userOption) {
		switch (userOption) {
			case 1:
				return new HeroKid();
			case 2:
				return new HeroAdult();
			case 3:
				return new HeroElder();
			default:
				return new HeroKid(); // default option, never used, added for mistakes eliminations
		}
	}
	
	// getters

	public double getBasicDamageDealt() {
		return this.basicDamageDealt;
	}

	public double getBasicDamageReduce() {
		return this.basicDamageReduce;
	}

	public double getMaxHealthPoints() {
		return this.maxHealthPoints;
	}

	// setters

	public void setBasicDamageDealt(double basicDamageDealt) {
		this.basicDamageDealt = basicDamageDealt;
	}

	public void setBasicDamageReduce(double basicDamageReduce) {
		this.basicDamageReduce = basicDamageReduce;
	}

	public void setMaxHealthPoints(double maxHealthPoints) {
		this.maxHealthPoints = maxHealthPoints;
	}

	
	public String toString() {
		return fancyName;
	}
	
	public String getFancyDescription() {
		return fancyDescription;
	}
	

	public String checkModification() {
		return "None";
	}

	public abstract String getName();
}
