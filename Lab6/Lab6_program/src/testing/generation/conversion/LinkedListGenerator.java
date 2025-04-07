package testing.generation.conversion;

import testing.MarkedValue;
import testing.generation.Generator;

import java.util.LinkedList;

public class LinkedListGenerator<T> implements Generator<MarkedValue<T>> {

	private Generator<? extends T> generator;
	
	public LinkedListGenerator(Generator<? extends T> generator) {
		this.generator = generator;
	}
	
	@Override
	public LinkedList<MarkedValue<T>> generate(int size) {
		LinkedList<MarkedValue<T>> list = new LinkedList<MarkedValue<T>>();

		MarkedValue.clearMarkers();
		for(T value : generator.generate(size)) {
			list.add(new MarkedValue<T>(value));
		}
		
		return list;
	}
}
