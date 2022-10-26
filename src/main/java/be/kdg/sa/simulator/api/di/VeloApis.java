package be.kdg.sa.simulator.api.di;

import be.kdg.sa.simulator.api.velo.VeloApi;
import be.kdg.sa.simulator.utils.TypeUtils;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

@Component
public class VeloApis {
	@Value ("${velo.url}")
	private String BASE_URL = "";
	
	private static List<VeloApiClass> veloApis;
	
	public record VeloApiClass(VeloApi apiInstance, Class<? extends VeloApi> apiClass) { }
	
	List<VeloApiClass> getVeloApis () {
		if (veloApis == null)
			veloApis = getVeloApis (BASE_URL);
		
		return veloApis;
	}
	
	private static List<VeloApiClass> getVeloApis (String baseUrl) {
		
		var converterFactory = GsonConverterFactory.create (new GsonBuilder ().create ());
		var retrofit = new Retrofit.Builder ()
				.addConverterFactory (converterFactory)
				.baseUrl (baseUrl)
				.build ();
		
		return TypeUtils.getAllSubTypesOfType (VeloApi.class).stream()
				.map (type -> new VeloApiClass (retrofit.create (type), type)).toList ();
	}
	
}
