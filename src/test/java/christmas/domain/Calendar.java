package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Calendar {
	
	private static final int THIS_YEAR = 2023;
	
	public static DayOfWeek findDayOfWeek(int month, int inputDate) {
		LocalDate date = LocalDate.of(THIS_YEAR, month, inputDate);
		return date.getDayOfWeek();
	}
	

}
