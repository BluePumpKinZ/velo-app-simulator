package be.kdg.sa.simulator.commands.commands;

import be.kdg.sa.simulator.commands.SimulatorCommand;
import be.kdg.sa.simulator.commands.SimulatorCommandParams;
import org.springframework.stereotype.Component;


@Component
public final class DelayCommand extends SimulatorCommand<DelayCommand.DelayCommandParams> {
	
	private DelayCommand () {
		super ("DELAY");
	}
	
	public static class DelayCommandParams implements SimulatorCommandParams {
		
		public long delay;
		
		@Override
		public void setParams (String paramString) {
			delay = Long.parseLong(paramString);
		}
	}
	
	@Override
	public String execute (DelayCommandParams params) {
		try {
			Thread.sleep(params.delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return String.format ("Waited for %d milliseconds.", params.delay);
	}
	
}
