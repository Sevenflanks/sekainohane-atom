package tw.org.sekainohane.atom.maze.service.impl;

import java.util.Map;
import java.util.Objects;

import tw.org.sekainohane.atom.maze.enums.AreaType;
import tw.org.sekainohane.atom.maze.service.RateBuilder;

import com.google.common.collect.Maps;

public class RateBuilderBySetter implements RateBuilder {
	
	private Map<AreaType, Integer> rates = Maps.newEnumMap(AreaType.class);
	
	@Override
	public RateBuilderBySetter setRate(AreaType areaType, int rate) {
		if (rate < 0) {
			rate = 0;
		}
		rates.put(areaType, rate);
		return this;
	}
	
	@Override
	public Map<AreaType, Integer> build() {
		rates.entrySet().parallelStream().filter(r -> Objects.isNull(r.getValue())).forEach(r -> r.setValue(0));
		return this.rates;
	}
	
}
