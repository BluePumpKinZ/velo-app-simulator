package be.kdg.sa.simulator.models.vehicles;

/**
 * Jonas Leijzen
 * 5/10/2022
 */
public abstract class VehicleCall {
	
	private final int vehicleId;
	
	protected VehicleCall (int vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	public int getVehicleId () {
		return vehicleId;
	}
	
}
