package telran.oop.people;

import java.util.Arrays;

public class CompanySortedArray extends CompanyArray {
	@Override
public boolean addEmployee(Employee empl) {
		
		//calls getEmployeeIndex
		int ind = getEmployeeIndex(empl.getId());
		if (ind >= 0) {
			return false;
		}
		ind = -(ind + 1);
		employees = Arrays.copyOf(employees, employees.length + 1);
		System.arraycopy(employees, ind, employees, ind + 1, employees.length - 1 - ind);
		employees[ind] = empl;
	return true;
		
}
	
	@Override 
	protected int getEmployeeIndex(long id) {
	
		Person pattern = new Person(id, 0, null);
		return Arrays.binarySearch(employees, pattern);
	}
	@Override
	public Employee[] getAllEmployees() {
		return Arrays.copyOf(employees, employees.length);
	}
}
