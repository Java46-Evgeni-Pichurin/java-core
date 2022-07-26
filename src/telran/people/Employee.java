package telran.people;

import java.time.Year;

public class Employee extends Person {
    private final int CURRENT_YEAR = Year.now().getValue();
    private static int minAge = 18;
    private static int maxAge = 64;
    private static int minBasicSalary = 100;

    private int basicSalary;

    public static int getMaxAge() {
        return maxAge;
    }

    public static void setMaxAge(int maxAge) {
        if (maxAge < minAge) {
            //Switch values
            Employee.maxAge = Employee.minAge - (Employee.minAge = maxAge) + Employee.minAge;
        }
        else {
            Employee.maxAge = maxAge;
        }
    }

    public static int getMinAge() {
        return minAge;
    }

    public static void setMinAge(int minAge) {
        if (minAge > maxAge) {
            //Switch values
            Employee.minAge = Employee.maxAge - (Employee.maxAge = minAge) + Employee.maxAge;
        }
        else {
            Employee.minAge = minAge;
        }
    }

    public static int getMinBasicSalary() {
        return minBasicSalary;
    }

    public static void setMinBasicSalary(int minBasicSalary) {
        Employee.minBasicSalary = minBasicSalary;
    }

    public Employee(long id, int birthYear, String email, int basicSalary) {
        super(id, birthYear, email);
        if (!ageValidator(birthYear)) {
            throw new IllegalArgumentException(String.format("%d - wrong age, "
                            + "should be in range [%d - %d]", CURRENT_YEAR - birthYear,
                    minAge, maxAge));
        }
        setBasicSalary(basicSalary);
    }


    public void setBasicSalary(int basicSalary) {
        if (!basicSalaryValidator(basicSalary)) {
            throw new IllegalArgumentException(String.format("%d - wrong basic salary, "
                            + "should be more than %d", basicSalary, minBasicSalary));
        }
        else {
            this.basicSalary = basicSalary;
        }
    }

    public int computePay() {
        return basicSalary;
    }

    @Override
    public String toString() {
        return "Employee {\n\t\t" +
                super.toString() +
                "\n\t\tbasicSalary=" + basicSalary +
                "\t\n }";
    }

    private boolean ageValidator(int birthYear) {
        return CURRENT_YEAR - birthYear >= minAge && CURRENT_YEAR - birthYear <= maxAge;
    }

    private boolean basicSalaryValidator(int basicSalary) {
        return basicSalary >= minBasicSalary;
    }
}
