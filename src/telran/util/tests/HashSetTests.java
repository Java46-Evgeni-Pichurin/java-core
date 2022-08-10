package telran.util.tests;

import telran.util.Collection;
import telran.util.HashSet;

public class HashSetTests extends SetTests {

	@Override
	protected Collection<Integer> createCollection() {
		
		return new HashSet<>();
	}

}
