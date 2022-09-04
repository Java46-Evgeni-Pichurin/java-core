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
	/**
	 * equals two Length objects according to LengthUnit
	 * 10m == 10000mm
	 */
	public boolean equals(Object obj) {
		try	{
			return obj == this.unit
					|| this.compareTo((Length) obj) == 0;
		}
		catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public int compareTo(Length o) {
		return Math.round(
				this.convert(LengthUnit.M).getAmount()
						- o.convert(LengthUnit.M).getAmount());
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
