package telran.util.tests;

import telran.util.ArrayList;
import telran.util.Collection;

public class ArrayListTests extends ListTests {

	@Override
	protected Collection<Integer> createCollection() {
		
		return new ArrayList<>();
	}

}
