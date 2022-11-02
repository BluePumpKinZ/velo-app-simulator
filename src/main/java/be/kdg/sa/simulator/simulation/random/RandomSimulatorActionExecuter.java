package be.kdg.sa.simulator.simulation.random;

import be.kdg.sa.simulator.exceptions.NoRandomSimulationCommandFoundException;
import be.kdg.sa.simulator.simulation.random.actions.RandomSimulationAction;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

@Component
public class RandomSimulatorActionExecuter {
	
	private final List<RandomSimulationAction> randomSimulationActions;
	
	public RandomSimulatorActionExecuter (List<RandomSimulationAction> randomSimulationActions) {
		this.randomSimulationActions = randomSimulationActions;
	}
	
	public String executeRandomAction (RandomSimulationContext context) {
		var possibleActions = randomSimulationActions.stream()
				.filter (action -> action.doesApply (context)).toList ();
		// the possible actions are weighted, so we need to take this into account when selecting a random action
		var totalWeight = possibleActions.stream().mapToInt (RandomSimulationAction::getWeight).sum ();
		AtomicReference<Stream<RandomSimulationAction>> stream = new AtomicReference<> (Stream.empty ());
		possibleActions.forEach (action -> {
			var weight = action.getWeight ();
			var weightStream = Stream.generate (() -> action).limit (weight);
			stream.set (Stream.concat (stream.get (), weightStream));
		});
		var randomAction = stream.get ()
				.skip ((int) (Math.random () * totalWeight))
				.findFirst ()
				.orElseThrow (NoRandomSimulationCommandFoundException::new);
		return randomAction.execute (context);
	}
}
