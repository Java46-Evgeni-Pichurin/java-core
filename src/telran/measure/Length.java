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
		//TODO
		//LengthUnit.
		
		
		return false;
	}

	@Override
	public int compareTo(Length o) {
		//TODO
		return 0;
	}

	public Length convert(LengthUnit unit) {
		//TODO
		float factor = this.unit.value / unit.value;
		return new Length(factor * this.getAmount(), unit);
	}

	@Override
	public String toString() {
		return "" + Math.round(amount) + unit;
	}
}
