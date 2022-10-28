package be.kdg.sa.simulator.models.vehicles.calls;


public final class LockDockedVehicleCall extends LockVehicleCall {
	
	private final int lockId;
	
	public LockDockedVehicleCall (int vehicleId, int userId, boolean defect, int lockId) {
		super (vehicleId, userId, defect);
		this.lockId = lockId;
	}
	
	public int getLockId () {
		return lockId;
	}
}
