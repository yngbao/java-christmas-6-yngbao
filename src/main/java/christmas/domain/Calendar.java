package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

public class Calendar {
	
	private static final int EVENT_YEAR = 2023;
	public static final int EVENT_MONTH = 12;
	public static final int FIRST_DATE = 1;
	public static final int X_MAS = 25;
	public static final int END_DATE = 31;
	private final int[] SPECIAL_DAY = {3, 10, 17, 24, 25, 31};
	public static final DayOfWeek[] WEEKDAY = 
		{DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY}; 
	
	public static boolean isBeforeChristmas(int inputDate) {
		return (inputDate >= FIRST_DATE && inputDate <= X_MAS);
	}
			
	private static DayOfWeek findDayOfWeek(int month, int inputDate) {
		LocalDate date = LocalDate.of(EVENT_YEAR, month, inputDate);
		return date.getDayOfWeek();
	}
	
	public static boolean isWeekday(int inputDate) {
		DayOfWeek day = findDayOfWeek(EVENT_MONTH, inputDate);
		return Arrays.stream(WEEKDAY)
				.anyMatch(dayOfWeek -> dayOfWeek == day);
	}
	
	public boolean isSpecial(int Inputdate) {
		boolean isSpecial = Arrays.stream(SPECIAL_DAY)
				.anyMatch(day -> day == Inputdate);
		if (isSpecial) {
			return true;
		}
		return false;
	}
	

}
