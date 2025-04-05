package attack;

public class WeaponRelations extends Weapon {
	public static String name = "Relations";
	public static String fancyName = "Arrows of Relations";
	public static String fancyDescription = "gives a steadily high effect";
	
	// constructor
	public WeaponRelations() {
		super(WeaponConstants.extraDamageDealtRelations,
			  WeaponConstants.critChanceRelations);
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
