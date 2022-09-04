package telran.measure;

public enum WeightUnit {
GR(1.0f), LBS(453.592f), KG(1000f), CN(100000f), TN(1000000f);
	float value;
	private WeightUnit(float value) {
		this.value = value;
	}
	public float convertValue(WeightUnit wu, float amount) {
		return value * amount / wu.value;
	}
	
}
