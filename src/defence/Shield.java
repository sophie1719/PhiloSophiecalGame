package defence;

import player.Modifiable;

public abstract class Shield implements Modifiable {
	protected double extraDamageReduce;
	protected int reflectChance;
	protected boolean isActive;
	public static String name = "Shield";
	public static String fancyName = "Shield";
	public static String fancyDescription = "";
	
	// constructor
	Shield (double extraDamageReduce, int reflectChance) {
		this.extraDamageReduce = extraDamageReduce;
		this.reflectChance = reflectChance;
		this.isActive = false;
	}
	
	// polymorphic function that chooses the shield
	public static Shield chooseShield(int userOption) {
		switch (userOption) {
			case 1:
				return new ShieldEscapism();
			case 2:
				return new ShieldAdaptability();
			case 3:
				return new ShieldOvercoming();
			default:
				return new ShieldAdaptability(); // default option, never used, added for mistakes eliminations
		}
	}
	
	// getters

	public double getExtraDamageReduce() {
		return this.extraDamageReduce;
	}

	public int getReflectChance() {
		return this.reflectChance;
	}

	public boolean getIsActive() {
		return this.isActive;
	}
	
	public String getFancyName() {
		return fancyName;
	}

	// setters

	public void setExtraDamageReduce(double extraDamageReduce) {
		this.extraDamageReduce = extraDamageReduce;
	}

	public void setReflectChance(int reflectChance) {
		this.reflectChance = reflectChance;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
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
