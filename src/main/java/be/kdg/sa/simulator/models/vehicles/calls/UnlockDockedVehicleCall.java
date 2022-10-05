package be.kdg.sa.simulator.models.vehicles.calls;

/**
 * Jonas Leijzen
 * 5/10/2022
 */
public final class UnlockDockedVehicleCall extends UnlockVehicleCall {
	
	private final int stationId;
	
	public UnlockDockedVehicleCall (int userId, int stationId) {
		super (0, userId);
		this.stationId = stationId;
	}
	
	public int getStationId () {
		return stationId;
	}
}
