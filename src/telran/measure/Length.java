package telran.measure;

public class Length implements Comparable<Length> {
    private final float amount;
    private final LengthUnit unit;

    public Length(float amount, LengthUnit unit) {
        this.amount = amount;
        this.unit = unit;
    }

    public float getAmount() {
        return amount;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            return this.compareTo((Length) obj) == 0;
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int compareTo(Length o) {
        float compRes = getCompRes(o);
        if (compRes > 0) {
            return 1;
        }
        if (compRes < 0) {
            return -1;
        }
        return 0;
    }

    private float getCompRes(Length o) {
        return this.convert(LengthUnit.M).getAmount() - o.convert(LengthUnit.M).getAmount();
    }

    public Length convert(LengthUnit unit) {
        float factor = this.unit.value / unit.value;
        return new Length(factor * this.getAmount(), unit);
    }

    @Override
    public String toString() {
        return "" + Math.round(amount) + unit;
    }
}