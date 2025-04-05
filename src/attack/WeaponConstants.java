package attack;

// Class that contains the constant values for different weapons
// All is calculated so that E(extraDamageDealt) is the same for each weapon type
public abstract class WeaponConstants {
	public static final double extraDamageDealtAmbitions = 8;
	public static final double extraDamageDealtCuriosity = 10;
	public static final double extraDamageDealtRelations = 12;
	
	public static final int critChanceAmbitions = 25; // 25%
	public static final int critChanceCuriosity = 10; // 10%
	public static final int critChanceRelations = 0;  //  0%
	
	public static final double critMultiplier = 3;
}
