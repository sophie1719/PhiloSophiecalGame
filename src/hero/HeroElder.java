package hero;

public class HeroElder extends Hero {
	
	private static String name = "HeroElder";
	public static String fancyName = "Elder";
	public static String fancyDescription = "balanced, but the least healthy of the three";

	// constructor
	public HeroElder() {
		super(HeroConstants.basicDamageDealtElder,
			  HeroConstants.basicDamageReduceElder,
			  HeroConstants.maxHealthPointsElder);
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
		return "Hero: Elder";
	}
}
