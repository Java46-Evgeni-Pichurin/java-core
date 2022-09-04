package telran.measure;

public enum LengthUnit {
    MM(1f), CM(10f), IN(25.4f), FT(304.8f), M(1000f), KM(1000000f);
    final float value;

    private LengthUnit(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public Length between(Length l1, Length l2) {
        return new Length(
                Math.abs(getConvertedAmount(l1)- getConvertedAmount(l2))
                , this);
    }

    private float getConvertedAmount(Length length) {
        return length.convert(this).getAmount();
    }
}
