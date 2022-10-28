package be.kdg.sa.simulator.api.velo;


import be.kdg.sa.simulator.models.vehicles.calls.LockDockedVehicleCall;
import be.kdg.sa.simulator.models.vehicles.calls.LockUndockedVehicleCall;
import be.kdg.sa.simulator.models.vehicles.calls.UnlockDockedVehicleCall;
import be.kdg.sa.simulator.models.vehicles.calls.UnlockUndockedVehicleCall;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RideVeloApi extends VeloApi {
	
	@POST ("/api/v1/rides/start/docked")
	Call<Integer> startDockedRide (@Body UnlockDockedVehicleCall unlockDockedVehicleCall);
	
	@POST ("/api/v1/rides/start/undocked")
	Call<Void> startUndockedRide (@Body UnlockUndockedVehicleCall unlockUndockedVehicleCall);
	
	@POST ("/api/v1/rides/end/docked")
	Call<Void> endDockedRide (@Body LockDockedVehicleCall lockDockedVehicleCall);
	
	@POST ("/api/v1/rides/end/undocked")
	Call<Void> endUndockedRide (@Body LockUndockedVehicleCall lockUndockedVehicleCall);
	
}
