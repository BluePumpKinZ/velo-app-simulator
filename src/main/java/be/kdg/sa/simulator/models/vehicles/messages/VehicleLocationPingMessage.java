package be.kdg.sa.simulator.models.vehicles.messages;

import be.kdg.sa.simulator.models.vehicles.VehicleCall;


public final class VehicleLocationPingMessage extends VehicleCall {
	
	private final long timeStamp;
	private final double latitude;
	private final double longitude;
	
	public VehicleLocationPingMessage (long timeStamp, int vehicleId, double latitude, double longitude) {
		super (vehicleId);
		this.timeStamp = timeStamp;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public long getTimeStamp () {
		return timeStamp;
	}
	
	public double getLatitude () {
		return latitude;
	}
	
	public double getLongitude () {
		return longitude;
	}
}
