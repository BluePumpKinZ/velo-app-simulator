package be.kdg.sa.simulator.models.vehicles.calls;

import be.kdg.sa.simulator.models.vehicles.VehicleCall;


public abstract class UnlockVehicleCall extends VehicleCall {
	
	private int userId;
	
	protected UnlockVehicleCall (int vehicleId, int userId) {
		super (vehicleId);
		this.userId = userId;
	}
	
	public int getUserId () {
		return userId;
	}
	
	public void setUserId (int userId) {
		this.userId = userId;
	}
}
