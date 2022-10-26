package be.kdg.sa.simulator.api.velo;


import be.kdg.sa.simulator.models.vehicles.calls.UnlockDockedVehicleCall;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface RideVeloApi extends VeloApi {
	
	@POST ("/api/v1/rides/start")
	Call<Integer> startDockedRide (UnlockDockedVehicleCall unlockDockedVehicleCall);
	
}
