package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

public class Calendar {
	
	private static final int THIS_YEAR = 2023;
	public static final DayOfWeek[] weekday = 
		{DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY}; 
			
	private static DayOfWeek findDayOfWeek(int month, int inputDate) {
		LocalDate date = LocalDate.of(THIS_YEAR, month, inputDate);
		return date.getDayOfWeek();
	}
	
	public static boolean isWeekday(int month, int inputDate) {
		DayOfWeek day = findDayOfWeek(month, inputDate);
		return Arrays.stream(weekday)
				.anyMatch(dayOfWeek -> dayOfWeek == day);
	}
	

}
