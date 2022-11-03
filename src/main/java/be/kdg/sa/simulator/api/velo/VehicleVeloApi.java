package be.kdg.sa.simulator.api.velo;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface VehicleVeloApi extends VeloApi {
	
	@GET ("/api/v1/vehicles/validSimulatorIds")
	Call<List<Integer>> getValidSimulationVehicleIds();
	
}
