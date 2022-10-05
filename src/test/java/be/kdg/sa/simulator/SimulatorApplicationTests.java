package be.kdg.sa.simulator;

import be.kdg.sa.simulator.messaging.senders.VehicleLocationSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SimulatorApplicationTests {
	
	@Test
	void contextLoads () {
	}
	
	@Autowired
	VehicleLocationSender sender;
	
	
}
