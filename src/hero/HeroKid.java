package hero;

public class HeroKid extends Hero {
	
	private static String name = "HeroKid";
	public static String fancyName = "Kid";
	public static String fancyDescription = "weak, but well-defended";
	
	// constructor
	public HeroKid() {
		super(HeroConstants.basicDamageDealtKid,
			  HeroConstants.basicDamageReduceKid,
			  HeroConstants.maxHealthPointsKid);
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
		return "Hero: Kid";
	}
}
