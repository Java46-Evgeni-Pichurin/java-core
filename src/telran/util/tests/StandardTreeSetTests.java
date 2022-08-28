package telran.util.tests;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import telran.util.Collection;
import telran.util.StandardTreeSet;

public class StandardTreeSetTests extends SortedSetTests {

	@Override
	protected Collection<Integer> createCollection() {
		
		return new StandardTreeSet<Integer>();
	}
	

}
