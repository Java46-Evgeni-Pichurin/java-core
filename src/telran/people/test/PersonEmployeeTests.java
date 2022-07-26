package telran.people.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.people.*;

class PersonEmployeeTests {

    private final int ID = 1234567;
    private final int RIGHT_BIRTH_YEAR = 1999;
    private final int WRONG_BIRTH_YEAR_1 = 1920;
    private final int WRONG_BIRTH_YEAR_2 = 2020;
    private final String RIGHT_EMAIL = "ABC.abc@gmail.com";
    private final String WRONG_EMAIL_1 = "ABC.abcgmailcom";
    private final String WRONG_EMAIL_2 = "ABC@com";
    private final int RIGHT_BASIC_SALARY = 3000;
    private final int WRONG_BASIC_SALARY_1 = -4000;
    private final int WRONG_BASIC_SALARY_2 = 80;
    private final int RIGHT_WAGE = 50;
    private final int RIGHT_HOURS = 200;
    private final int RIGHT_SALES = 10000;
    private final int RIGHT_PERCENT_PAY = 20;
    private final int WRONG_PERCENT_PAY_1 = -10;
    private final int WRONG_PERCENT_PAY_2 = 120;


    @Test
    void wrongEmail() {
        System.out.println("-----------------------WRONG EMAIL TEST---------------------------");
        try {
            new Person(ID, 2004, WRONG_EMAIL_1);
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        try {
            new WageEmployee(ID, 1999, WRONG_EMAIL_2, WRONG_BASIC_SALARY_1,
                    RIGHT_WAGE, RIGHT_HOURS);
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("------------------------------------------------------------------\n");
    }

    @Test
    void wrongBasicSalary() {
        System.out.println("-------------------WRONG BASIC SALARY TEST------------------------");
        try {
            new SalesPerson(ID, RIGHT_BIRTH_YEAR, RIGHT_EMAIL,
                    WRONG_BASIC_SALARY_1, RIGHT_SALES, RIGHT_PERCENT_PAY);
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        try {
            new WageEmployee(ID, RIGHT_BIRTH_YEAR, RIGHT_EMAIL,
                    WRONG_BASIC_SALARY_2, RIGHT_WAGE, RIGHT_HOURS);
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("------------------------------------------------------------------\n");
    }

    @Test
    void wrongBirthYearTest() {
        System.out.println("------------------------WRONG AGE TEST----------------------------");
        try {
            new SalesPerson(ID, WRONG_BIRTH_YEAR_1, RIGHT_EMAIL,
                    RIGHT_BASIC_SALARY, RIGHT_SALES, RIGHT_PERCENT_PAY);
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        try {
            new Employee(ID, WRONG_BIRTH_YEAR_2, RIGHT_EMAIL, RIGHT_BASIC_SALARY);
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("------------------------------------------------------------------\n");
    }

    @Test
    void wrongPercentPay() {
        System.out.println("--------------------WRONG PERCENT PAY TEST------------------------");
        try {
            new SalesPerson(ID, RIGHT_BIRTH_YEAR, RIGHT_EMAIL,
                    RIGHT_BASIC_SALARY, RIGHT_SALES, WRONG_PERCENT_PAY_1);
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        try {
            new SalesPerson(ID, RIGHT_BIRTH_YEAR, RIGHT_EMAIL, RIGHT_BASIC_SALARY, RIGHT_SALES, WRONG_PERCENT_PAY_2);
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("------------------------------------------------------------------\n");
    }
}