package be.kdg.sa.simulator.utils;

import org.reflections.Reflections;

import java.util.List;

public class TypeUtils {
	
	public static <T> List<Class<? extends T>> getAllSubTypesOfType (Class<T> type) {
		
		Reflections reflections = new Reflections("be.kdg.sa.simulator");
		return reflections.getSubTypesOf(type)
				.stream().filter (Class::isInterface).toList();
		
	}
	
}
