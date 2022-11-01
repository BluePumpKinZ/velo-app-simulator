package be.kdg.sa.simulator.models.vehicles.messages;

import be.kdg.sa.simulator.models.vehicles.VehicleCall;

public final class VehicleLocationPingMessage extends VehicleCall {
	private double latitude;
	private double longitude;
	
	public VehicleLocationPingMessage (int vehicleId, double latitude, double longitude) {
		super (vehicleId);
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double getLatitude () {
		return latitude;
	}
	
	public void setLatitude (double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude () {
		return longitude;
	}
	
	public void setLongitude (double longitude) {
		this.longitude = longitude;
	}
}
