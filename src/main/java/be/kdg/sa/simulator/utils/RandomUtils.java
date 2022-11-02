package be.kdg.sa.simulator.utils;

/**
 * Jonas Leijzen
 * 2/11/2022
 */
public class RandomUtils {
	
	public static double getRandomDoubleRanged (double min, double max) {
		return Math.random () * (max - min) + min;
	}
	
}
