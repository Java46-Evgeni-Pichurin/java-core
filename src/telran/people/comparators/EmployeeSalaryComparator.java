package telran.people.comparators;

import java.util.Comparator;

import telran.people.Employee;

public class EmployeeSalaryComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		
		return Integer.compare(o1.computePay(), o2.computePay());
	}

}
