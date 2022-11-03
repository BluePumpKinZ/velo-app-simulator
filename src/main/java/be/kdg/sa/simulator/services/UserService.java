package be.kdg.sa.simulator.services;

import be.kdg.sa.simulator.api.di.VeloApiContainer;
import be.kdg.sa.simulator.api.velo.UserVeloApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {

	private final UserVeloApi userVeloApi;
	private final Logger logger = LoggerFactory.getLogger (UserService.class);
	
	public UserService (VeloApiContainer veloApiContainer) {
		this.userVeloApi = veloApiContainer.getVeloApi (UserVeloApi.class);
	}
	
	public List<Integer> getValidSimulationUserIds () throws IOException {
		var call = userVeloApi.getValidSimulationUserIds ();
		
		var response = call.execute ();
		if (response.isSuccessful ())
			return response.body ();
		
		assert response.errorBody () != null;
		String message = response.errorBody ().string ();
		logger.error (message);
		throw new IOException (message);
	}

}
