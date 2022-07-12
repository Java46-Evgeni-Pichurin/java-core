package telran.people;

public class Employee extends Person {
    private int basicSalary;

    public Employee(long id, int birthYear, String email, int basicSalary) {
        super(id, birthYear, email);
        this.basicSalary = Math.abs(basicSalary);
    }

    public void setBasicSalary(int basicSalary) {
        this.basicSalary = Math.abs(basicSalary);
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
}
