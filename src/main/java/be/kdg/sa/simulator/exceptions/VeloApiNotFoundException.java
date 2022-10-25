package be.kdg.sa.simulator.exceptions;

import be.kdg.sa.simulator.api.velo.VeloApi;

/**
 * Jonas Leijzen
 * 25/10/2022
 */
public class VeloApiNotFoundException extends RuntimeException {
	public VeloApiNotFoundException (Class<? extends VeloApi> clazz) {
		super ("Could not find an instance of " + clazz.getName());
	}
}
