package hero;

// Class that contains the constant values for different Heroes
// All is calculated so that the function (maxHP / (basicDamageDealt * basicDamageTaken))
// is the same for each Hero type
public abstract class HeroConstants {
	public static final double basicDamageDealtKid = 4;
	public static final double basicDamageDealtAdult = 8;
	public static final double basicDamageDealtElder = 5;
	
	public static final double basicDamageReduceKid = 0.18;
	public static final double basicDamageReduceAdult = 0.08;
	public static final double basicDamageReduceElder = 0.12;
	
	public static final double maxHealthPointsKid = 180;
	public static final double maxHealthPointsAdult = 160;
	public static final double maxHealthPointsElder = 150;
}
