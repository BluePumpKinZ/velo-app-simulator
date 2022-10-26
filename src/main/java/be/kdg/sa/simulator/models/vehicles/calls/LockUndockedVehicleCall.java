package be.kdg.sa.simulator.models.vehicles.calls;


public final class LockUndockedVehicleCall extends LockVehicleCall {
	
	private final double latitude;
	private final double longitude;
	
	public LockUndockedVehicleCall (int vehicleId, int userId, boolean defect, double latitude, double longitude) {
		super (vehicleId, userId, defect);
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double getLatitude () {
		return latitude;
	}
	
	public double getLongitude () {
		return longitude;
	}
}
