package telran.people;

import java.util.Arrays;

public class CompanySortedArray extends CompanyArray {
    @Override
    public boolean addEmployee(Employee empl) {
        int curIndex = getEmployeeIndex(empl.getId());
        if (curIndex >= 0) {
            return false;
        }
        curIndex = Math.abs(curIndex) - 1;
        Employee[] tmp = new Employee[employees.length + 1];
        System.arraycopy(employees, 0, tmp, 0, curIndex);
        tmp[curIndex] = empl;
        System.arraycopy(employees, curIndex, tmp, curIndex + 1, employees.length - curIndex);
        employees = tmp;
        return true;
    }

    @Override
    protected int getEmployeeIndex(long id) {
        return Arrays.binarySearch(employees, new Person(id, 0, ""));
    }

    @Override
    public Employee[] getAllEmployees() {
        return Arrays.copyOf(employees, employees.length);
    }
}
