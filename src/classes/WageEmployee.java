package classes;

public class WageEmployee extends Employee {
    private int wage;
    private int hours;

    public WageEmployee(long id, int birthYear, String email, int basicSalary, int wage, int hours) {
        super(id, birthYear, email, basicSalary);
        this.wage = Math.abs(wage);
        this.hours = Math.abs(hours);
    }

    public void setWage(int wage) {
        this.wage = Math.abs(wage);
    }

    public void setHours(int hours) {
        this.hours = Math.abs(hours);
    }

    @Override
    public int computePay() {
        return super.computePay() + wage * hours;
    }

    @Override
    public String toString() {
        return "WageEmployee {\n\t" +
                super.toString() +
                "\n\twage=" + wage +
                ", hours=" + hours +
                "\n}";
    }
}
