package telran.people.test;

import java.util.function.Predicate;

import telran.people.Employee;

public class SalaryRangePredicate implements Predicate<Employee> {
int minSalary;
int maxSalary;
	public SalaryRangePredicate(int minSalary, int maxSalary) {
	this.minSalary = minSalary;
	this.maxSalary = maxSalary;
}
	@Override
	public boolean test(Employee t) {
		int salary = t.computePay();
		return salary >= minSalary && salary <= maxSalary;
	}

}
