package telran.people;

public class SalesPerson extends Employee {
    private int sales;
    private int percentPay;

    public SalesPerson(long id, int birthYear, String email, int basicSalary, int sales, int percentPay) {
        super(id, birthYear, email, basicSalary);
        this.sales = Math.abs(sales);
        this.percentPay = Math.abs(percentPay);
    }

    public void setSales(int sales) {
        this.sales = Math.abs(sales);
    }

    public void setPercentPay(int percentPay) {
        this.percentPay = Math.abs(percentPay);
    }

    @Override
    public int computePay() {
        return super.computePay() + sales * percentPay / 100;
    }

    @Override
    public String toString() {
        return "SalesPerson {\n\t" +
                super.toString() +
                "\n\tsales=" + sales +
                ", percentPay=" + percentPay +
                "\n{";
    }
}
