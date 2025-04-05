package defence;

public class ShieldEscapism extends Shield {
	public static String name = "Escapism";
	public static String fancyName = "Shield of Escapism";
	public static String fancyDescription = "defends you well, but some damage will always get you";
	
	// constructor
	public ShieldEscapism() {
		super(ShieldConstants.extraDamageReduceEscapism,
			  ShieldConstants.reflectChanceEscapism);
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
