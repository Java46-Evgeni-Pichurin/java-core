import static org.junit.jupiter.api.Assertions.*;

import telran.people.Employee;
import telran.people.Person;
import telran.people.SalesPerson;
import telran.people.WageEmployee;
import org.junit.jupiter.api.Test;

class TestClasses {
    private final int ID = 1234567;
    private final int BIRTH_YEAR = 1999;
    private final String RIGHT_EMAIL = "ABC.abc@gmail.com";
    private final String WRONG_EMAIL = "ABC.abcgmailcom";
    private final int RIGHT_BASIC_SALARY = 3000;
    private final int WRONG_BASIC_SALARY = -4000;
    private final int RIGHT_WAGE = 50;
    private final int WRONG_WAGE = -30;
    private final int RIGHT_HOURS = 200;
    private final int WRONG_HOURS = -190;
    private final int RIGHT_SALES = 10000;
    private final int WRONG_SALES = -7000;
    private final int RIGHT_PERCENT_PAY = 20;
    private final int WRONG_PERCENT_PAY = -10;
    private final int ZERO_TEST = 0;

    @Test
    void personTest() {
        Person person1 = new Person(ID, BIRTH_YEAR, RIGHT_EMAIL);
        Person person2 = new Person(ID, BIRTH_YEAR, WRONG_EMAIL);
        System.out.println(person1.toString());
        System.out.println(person2.toString());
        assertEquals(ID, person1.getId());
        assertEquals(ID, person2.getId());
        assertEquals(BIRTH_YEAR, person1.getBirthYear());
        assertEquals(BIRTH_YEAR, person2.getBirthYear());
        assertEquals(RIGHT_EMAIL, person1.getEmail());
        assertNull(person2.getEmail());
        person2.setEmail(RIGHT_EMAIL);
        assertEquals(RIGHT_EMAIL, person2.getEmail());
    }

    @Test
    void EmployeeTest() {
        Employee employee1 = new Employee(ID, BIRTH_YEAR, RIGHT_EMAIL, RIGHT_BASIC_SALARY);
        Employee employee2 = new Employee(ID, BIRTH_YEAR, WRONG_EMAIL, WRONG_BASIC_SALARY);
        Employee employee3 = new Employee(ID, BIRTH_YEAR, RIGHT_EMAIL, ZERO_TEST);
        System.out.println(employee1.toString());
        System.out.println(employee2.toString());
        System.out.println(employee3.toString());
        assertEquals(RIGHT_BASIC_SALARY, employee1.computePay());
        assertEquals(-WRONG_BASIC_SALARY, employee2.computePay());
        assertEquals(ZERO_TEST, employee3.computePay());
        employee2.setBasicSalary(RIGHT_BASIC_SALARY);
        assertEquals(RIGHT_BASIC_SALARY, employee2.computePay());
    }

    @Test
    void WageEmployeeTest() {
        WageEmployee wageEmployee1 = new WageEmployee(ID, BIRTH_YEAR, RIGHT_EMAIL, RIGHT_BASIC_SALARY, RIGHT_WAGE, RIGHT_HOURS);
        WageEmployee wageEmployee2 = new WageEmployee(ID, BIRTH_YEAR, RIGHT_EMAIL, RIGHT_BASIC_SALARY, WRONG_WAGE, RIGHT_HOURS);
        WageEmployee wageEmployee3 = new WageEmployee(ID, BIRTH_YEAR, RIGHT_EMAIL, RIGHT_BASIC_SALARY, RIGHT_WAGE, WRONG_HOURS);
        WageEmployee wageEmployee4 = new WageEmployee(ID, BIRTH_YEAR, RIGHT_EMAIL, RIGHT_BASIC_SALARY, WRONG_WAGE, WRONG_HOURS);
        System.out.println(wageEmployee1.toString());
        System.out.println(wageEmployee2.toString());
        System.out.println(wageEmployee3.toString());
        System.out.println(wageEmployee4.toString());
        assertEquals(RIGHT_BASIC_SALARY + RIGHT_WAGE * RIGHT_HOURS, wageEmployee1.computePay());
        assertEquals(RIGHT_BASIC_SALARY - WRONG_WAGE * RIGHT_HOURS, wageEmployee2.computePay());
        assertEquals(RIGHT_BASIC_SALARY - RIGHT_WAGE * WRONG_HOURS, wageEmployee3.computePay());
        assertEquals(RIGHT_BASIC_SALARY + WRONG_WAGE * WRONG_HOURS, wageEmployee4.computePay());
        wageEmployee4.setWage(RIGHT_WAGE);
        wageEmployee4.setHours(RIGHT_HOURS);
        assertEquals(RIGHT_BASIC_SALARY + RIGHT_WAGE * RIGHT_HOURS, wageEmployee4.computePay());
        wageEmployee4.setWage(ZERO_TEST);
        assertEquals(RIGHT_BASIC_SALARY, wageEmployee4.computePay());
        wageEmployee3.setHours(ZERO_TEST);
        assertEquals(RIGHT_BASIC_SALARY, wageEmployee3.computePay());
    }

    @Test
    void SalesPersonTest() {
        SalesPerson salesPerson1 = new SalesPerson(ID, BIRTH_YEAR, RIGHT_EMAIL, RIGHT_BASIC_SALARY, RIGHT_SALES, RIGHT_PERCENT_PAY);
        SalesPerson salesPerson2 = new SalesPerson(ID, BIRTH_YEAR, RIGHT_EMAIL, RIGHT_BASIC_SALARY, WRONG_SALES, RIGHT_PERCENT_PAY);
        SalesPerson salesPerson3 = new SalesPerson(ID, BIRTH_YEAR, RIGHT_EMAIL, RIGHT_BASIC_SALARY, RIGHT_SALES, WRONG_PERCENT_PAY);
        SalesPerson salesPerson4 = new SalesPerson(ID, BIRTH_YEAR, RIGHT_EMAIL, RIGHT_BASIC_SALARY, WRONG_SALES, WRONG_PERCENT_PAY);
        System.out.println(salesPerson1.toString());
        System.out.println(salesPerson2.toString());
        System.out.println(salesPerson3.toString());
        System.out.println(salesPerson4.toString());
        assertEquals(RIGHT_BASIC_SALARY + RIGHT_SALES * RIGHT_PERCENT_PAY / 100, salesPerson1.computePay());
        assertEquals(RIGHT_BASIC_SALARY - WRONG_SALES * RIGHT_PERCENT_PAY / 100, salesPerson2.computePay());
        assertEquals(RIGHT_BASIC_SALARY - RIGHT_SALES * WRONG_PERCENT_PAY / 100, salesPerson3.computePay());
        assertEquals(RIGHT_BASIC_SALARY + WRONG_SALES * WRONG_PERCENT_PAY / 100, salesPerson4.computePay());
        salesPerson4.setSales(RIGHT_SALES);
        salesPerson4.setPercentPay(RIGHT_PERCENT_PAY);
        assertEquals(RIGHT_BASIC_SALARY + RIGHT_SALES * RIGHT_PERCENT_PAY / 100, salesPerson4.computePay());
        salesPerson4.setSales(ZERO_TEST);
        assertEquals(RIGHT_BASIC_SALARY, salesPerson4.computePay());
        salesPerson3.setPercentPay(ZERO_TEST);
        assertEquals(RIGHT_BASIC_SALARY, salesPerson3.computePay());
    }
}