package defence;

public class ShieldOvercoming extends Shield {
	public static String name = "Overcoming";
	public static String fancyName = "Energy of Overcoming";
	public static String fancyDescription = "the weakest in normal defence, but the highest in chances to overcome";
	
	// constructor
	public ShieldOvercoming() {
		super(ShieldConstants.extraDamageReduceOvercoming,
			  ShieldConstants.reflectChanceOvercoming);
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
