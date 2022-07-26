package telran.people.test;

import java.util.function.Predicate;

import telran.people.*;

public class SalesPersonPredicate implements Predicate<Employee> {

	@Override
	public boolean test(Employee t) {
		
		return t instanceof SalesPerson;
	}

}
