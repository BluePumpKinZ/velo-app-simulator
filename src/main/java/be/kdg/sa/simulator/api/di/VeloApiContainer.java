package be.kdg.sa.simulator.api.di;

import be.kdg.sa.simulator.api.velo.VeloApi;
import be.kdg.sa.simulator.exceptions.VeloApiNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Jonas Leijzen
 * 25/10/2022
 */
@Component
public class VeloApiContainer {
	
	private final VeloApis veloApis;
	
	public VeloApiContainer (VeloApis veloApis) {
		this.veloApis = veloApis;
	}
	
	@SuppressWarnings ("unchecked")
	public <TVeloApi extends VeloApi>TVeloApi getVeloApi (Class<TVeloApi> apiClass) {
		
		String apiClassCanonicalName = apiClass.getCanonicalName ();
		
		return (TVeloApi) veloApis.getVeloApis ().stream()
				.filter (api -> api.apiClass ().getCanonicalName ().equals(apiClassCanonicalName))
				.findFirst ()
				.orElseThrow (() -> new VeloApiNotFoundException (apiClass))
				.apiInstance ();
	}
}
