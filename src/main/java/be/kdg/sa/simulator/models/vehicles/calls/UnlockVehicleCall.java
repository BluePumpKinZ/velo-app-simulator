package be.kdg.sa.simulator.models.vehicles.calls;

import be.kdg.sa.simulator.models.vehicles.VehicleCall;

/**
 * Jonas Leijzen
 * 5/10/2022
 */
public abstract class UnlockVehicleCall extends VehicleCall {
	
	private final int userId;
	
	protected UnlockVehicleCall (int vehicleId, int userId) {
		super (vehicleId);
		this.userId = userId;
	}
	
	public int getUserId () {
		return userId;
	}
}
