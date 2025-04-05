package hero;

public class HeroAdult extends Hero {
	
	private static String name = "HeroAdult";
	public static String fancyName = "Adult";
	public static String fancyDescription = "strong, but less defended";
	
	// constructor
	public HeroAdult() {
		super(HeroConstants.basicDamageDealtAdult,
			  HeroConstants.basicDamageReduceAdult,
			  HeroConstants.maxHealthPointsAdult);
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
		return "Hero: Adult";
	}
}
