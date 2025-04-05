package defence;

// Class that contains the constant values for different shields
// All is calculated so that E(extraDamageTaken) is the same for each shield type
public abstract class ShieldConstants {
	public static final double extraDamageReduceEscapism = 0.28;
	public static final double extraDamageReduceAdaptability = 0.2;
	public static final double extraDamageReduceOvercoming = 0.1;
	
	public static final int reflectChanceEscapism = 0;		//  0%
	public static final int reflectChanceAdaptability = 10;  // 10%
	public static final int reflectChanceOvercoming = 20;	// 20%
}
