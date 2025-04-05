package attack;

import player.Modifiable;

public abstract class Weapon implements Modifiable {
	protected double extraDamageDealt;
	protected int critChance;
	protected double critMultiplier = WeaponConstants.critMultiplier;
	public static String name = "Weapon";
	public static String fancyName = "Weapon";
	public static String fancyDescription = "";
	
	// constructor
	Weapon(double extraDamageDealt, int critChance) {
		this.extraDamageDealt = extraDamageDealt;
		this.critChance = critChance;
	}
	
	// polymorphic function that chooses the weapon
	public static Weapon chooseWeapon(int userOption) {
		switch (userOption) {
			case 1:
				return new WeaponAmbitions();
			case 2:
				return new WeaponCuriosity();
			case 3:
				return new WeaponRelations();
			default:
				return new WeaponRelations(); // default option, never used, added for mistakes eliminations
		}
	}
	
	// getters

	public double getExtraDamageDealt() {
		return this.extraDamageDealt;
	}

	public int getCritChance() {
		return this.critChance;
	}

	public double getCritMultiplier() {
		return this.critMultiplier;
	}
	
	public String getFancyName() {
		return fancyName;
	}

	// setters

	public void setExtraDamageDealt(double extraDamageDealt) {
		this.extraDamageDealt = extraDamageDealt;
	}

	public void setCritChance(int critChance) {
		this.critChance = critChance;
	}
	
	
	public String toString() {
		return fancyName;
	}
	
	public String getName() {
		return name;
	}
	
	public String getFancyDescription() {
		return fancyDescription;
	}
	
	public String checkModification() {
		return "None";
	}
}
