package attack;

public class WeaponAmbitions extends Weapon {
	public static String name = "Ambitions";
	public static String fancyName = "Sword of Ambitions";
	public static String fancyDescription = "gives little effect, but there's a chance to a sudden tripple in outcome.";
	
	// constructor
	public WeaponAmbitions() {
		super(WeaponConstants.extraDamageDealtAmbitions,
			  WeaponConstants.critChanceAmbitions);
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
