package telran.people.comparators;

import java.util.Comparator;

import telran.people.Person;

public class PersonAgeComparator implements Comparator<Person> {

	@Override
	public int compare(Person p1, Person p2) {
		
		return Integer.compare(p2.getBirthYear(), p1.getBirthYear());
	}

}
