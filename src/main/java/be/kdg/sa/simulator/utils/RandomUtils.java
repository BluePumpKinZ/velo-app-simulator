package be.kdg.sa.simulator.utils;

public class RandomUtils {
	
	public static double getRandomDoubleRanged (double min, double max) {
		return Math.random () * (max - min) + min;
	}
	
}
