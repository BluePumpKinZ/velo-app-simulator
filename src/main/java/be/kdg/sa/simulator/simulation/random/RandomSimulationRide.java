package be.kdg.sa.simulator.simulation.random;

import lombok.Getter;

import java.util.Objects;

/**
 * Jonas Leijzen
 * 2/11/2022
 */
@Getter
public final class RandomSimulationRide {
	private int id;
	private final int userId;
	private final int vehicleId;
	
	void setId (int id) {
		this.id = id;
	}
	
	public RandomSimulationRide (int id, int userId, int vehicleId) {
		this.id = id;
		this.userId = userId;
		this.vehicleId = vehicleId;
	}
	
	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (!(o instanceof RandomSimulationRide that)) return false;
		return id == that.id;
	}
	
	@Override
	public int hashCode () {
		return Objects.hash (id);
	}
	
	@Override
	public String toString () {
		return "RandomSimulationRide[" +
				"id=" + id + ", " +
				"userId=" + userId + ", " +
				"vehicleId=" + vehicleId + ']';
	}
	
}
