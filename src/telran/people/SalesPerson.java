package telran.people;

public class SalesPerson extends Employee {
    private static int minPercent = 0;
    private static int maxPercent = 100;

    private int sales;
    private int percentPay;

    public SalesPerson(long id, int birthYear, String email, int basicSalary, int sales, int percentPay) {
        super(id, birthYear, email, basicSalary);
        setSales(sales);
        setPercentPay(percentPay);
    }

    public static int getMinPercent() {
        return minPercent;
    }

    public static void setMinPercent(int minPercent) {
        if (minPercent > maxPercent) {
            //Switch values
            SalesPerson.minPercent = SalesPerson.maxPercent - (SalesPerson.maxPercent = minPercent) + SalesPerson.maxPercent;
        }
        else {
            SalesPerson.minPercent = minPercent;
        }
    }

    public static int getMaxPercent() {
        return maxPercent;
    }

    public static void setMaxPercent(int maxPercent) {
        if (maxPercent < minPercent) {
            //Switch values
            SalesPerson.maxPercent = SalesPerson.minPercent - (SalesPerson.minPercent = maxPercent) + SalesPerson.minPercent;
        }
        else {
            SalesPerson.maxPercent = maxPercent;
        }
    }

    public void setSales(int sales) {
        this.sales = Math.abs(sales);
    }

    public void setPercentPay(int percentPay) {
        if (!percentPayValidator(percentPay)) {
            throw new IllegalArgumentException(String.format("%d - wrong percent, "
                    + "should be in range [%d - %d]", percentPay, minPercent, maxPercent));
        }
        else {
            this.percentPay = percentPay;
        }
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
    private boolean percentPayValidator(int percentPay) {
        return percentPay >= minPercent && percentPay <= maxPercent;
    }
}
