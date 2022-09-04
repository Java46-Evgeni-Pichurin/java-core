package telran.time;

public enum DayOfWeek {
SUN, MON, TUE, WED, THU, FRI, SHABBAT;
	public DayOfWeek pluseDays(int days) {
		int weekDayNum = ordinal();
		int resultNum = (weekDayNum + days) % 7;
		return values()[resultNum];
	}

	static public int between(DayOfWeek day1, DayOfWeek day2) {
		int day1Num = day1.ordinal();
		int day2Num = day2.ordinal();
		if (day2Num <= day1Num) {
			day2Num += 7;
		}
		return day2Num - day1Num;
	}
}
