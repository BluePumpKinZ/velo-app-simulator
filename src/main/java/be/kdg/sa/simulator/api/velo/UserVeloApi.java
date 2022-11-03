package be.kdg.sa.simulator.api.velo;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface UserVeloApi extends VeloApi {
	
	@GET ("/api/v1/users/validSimulatorIds")
	Call<List<Integer>> getValidSimulationUserIds();

}
