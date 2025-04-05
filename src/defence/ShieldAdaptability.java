package defence;

public class ShieldAdaptability extends Shield {
	public static String name = "Adaptability";
	public static String fancyName = "Armor of Adaptability";
	public static String fancyDescription = "harder in use, but there's a chance to completely reflect the damage";
	
	// constructor
	public ShieldAdaptability() {
		super(ShieldConstants.extraDamageReduceAdaptability,
			  ShieldConstants.reflectChanceAdaptability);
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
		return "Shield: " + fancyName;
	}
}
