package attack;

public class WeaponCuriosity extends Weapon {
	public static String name = "Curiosity";
	public static String fancyName = "Spear of Curiosity";
	public static String fancyDescription = "gives more effect, but a chance to boost is less";
	
	// constructor
	public WeaponCuriosity() {
		super(WeaponConstants.extraDamageDealtCuriosity,
			  WeaponConstants.critChanceCuriosity);
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
		return "Weapon: " + fancyName;
	}
}
