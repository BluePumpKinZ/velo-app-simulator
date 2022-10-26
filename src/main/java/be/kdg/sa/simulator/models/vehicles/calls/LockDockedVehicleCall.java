package be.kdg.sa.simulator.models.vehicles.calls;


public final class LockDockedVehicleCall extends LockVehicleCall {
	
	private final int stationId;
	private final int lockId;
	
	public LockDockedVehicleCall (int vehicleId, int userId, boolean defect, int stationId, int lockId) {
		super (vehicleId, userId, defect);
		this.stationId = stationId;
		this.lockId = lockId;
	}
	
	public int getStationId () {
		return stationId;
	}
	
	public int getLockId () {
		return lockId;
	}
}
