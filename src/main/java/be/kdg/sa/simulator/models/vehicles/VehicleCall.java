package be.kdg.sa.simulator.models.vehicles;


public abstract class VehicleCall {
	
	private int vehicleId;
	
	protected VehicleCall (int vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	public int getVehicleId () {
		return vehicleId;
	}
	
	public void setVehicleId (int vehicleId) {
		this.vehicleId = vehicleId;
	}
	
}
