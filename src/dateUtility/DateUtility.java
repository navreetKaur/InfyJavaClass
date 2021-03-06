package dateUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class DateUtility {
	

	public static String displayCalendar(int month, int year) {
		Calendar cal = new GregorianCalendar();

		cal.clear();
		cal.set(year, month - 1, 1);
		System.out.println(
				"\n" + cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + " " + cal.get(Calendar.YEAR));

		int firstWeekdayOfMonth = cal.get(Calendar.DAY_OF_WEEK);

		int numberOfMonthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		return printCalendar(numberOfMonthDays, firstWeekdayOfMonth);
	}

	private static String printCalendar(int numberOfMonthDays, int firstWeekdayOfMonth) {
		String output = "";
		int weekdayIndex = 0;
		output += ("Su  Mo  Tu  We  Th  Fr  Sa\n");
		for (int day = 1; day < firstWeekdayOfMonth; day++) {
			output += ("    ");
			weekdayIndex++;
		}
		for (int day = 1; day <= numberOfMonthDays; day++) {
			if (day < 10)
				output += (" " + day);
			else
				output += (day);

			weekdayIndex++;
			if (weekdayIndex == 7) {
				weekdayIndex = 0;
				output += "\n";
			} else {
				output += ("  ");
			}
		}

		output += "\n";
		return output;
	}

	public static int getDaysInMonth(int month, int year) {
		Calendar cal = new GregorianCalendar();

		cal.clear();
		cal.set(year, month - 1, 1);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);

	}

	public static String toString(int month, int day, int year, char del) {
		return month+""+del+day+del+year;
	}

	public static boolean isLeapYear(int year) {
		return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
	}
	
	public static int dayOfTheWeek(int month, int day, int year) {
		Calendar cal = new GregorianCalendar(year,month,day);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	public static int dayOfTheWeek(String date) {
		Scanner dateParse = new Scanner(date);
		dateParse.useDelimiter("/");
		int month = (dateParse.nextInt());
		int day = (dateParse.nextInt());
		int year = (dateParse.nextInt());
		dateParse.close();
		return dayOfTheWeek(month,day,year);
	}
	public static int daysBwDates(String date1, String date2) {
		Scanner dateParse = new Scanner(date1);
		dateParse.useDelimiter("/");
		int month = (dateParse.nextInt());
		int day = (dateParse.nextInt());
		int year = (dateParse.nextInt());
		dateParse.close();
		
		Scanner dateParse1 = new Scanner(date2);
		dateParse1.useDelimiter("/");
		int month1 = (dateParse1.nextInt());
		int day1 = (dateParse1.nextInt());
		int year1 = (dateParse1.nextInt());
		dateParse1.close();
		return daysBwDates(month,day,year,month1,day1,year1);
	}
	public static int daysBwDates(int month,int day,int year ) {
		Calendar cal1 = new GregorianCalendar();
		int month1 = cal1.get(GregorianCalendar.MONTH)+1;
		int day1 = cal1.get(GregorianCalendar.DAY_OF_MONTH);
		int year1 = cal1.get(GregorianCalendar.YEAR);
		return daysBwDates(month,day,year,month1,day1,year1);
	}
	public static int daysBwDates(int month, int day, int year, int month1, int day1, int year1) {
		Calendar cal1 = new GregorianCalendar(year,month,day);
		Calendar cal2 = new GregorianCalendar(year1,month1,day1);
		Calendar dayOne = (Calendar) cal1.clone(),
	            dayTwo = (Calendar) cal2.clone();

	    if (dayOne.get(Calendar.YEAR) == dayTwo.get(Calendar.YEAR)) {
	        return Math.abs(dayOne.get(Calendar.DAY_OF_YEAR) - dayTwo.get(Calendar.DAY_OF_YEAR));
	    } else {
	        if (dayTwo.get(Calendar.YEAR) > dayOne.get(Calendar.YEAR)) {
	            Calendar temp = dayOne;
	            dayOne = dayTwo;
	            dayTwo = temp;
	        }
	        int extraDays = 0;

	        int dayOneOriginalYearDays = dayOne.get(Calendar.DAY_OF_YEAR);

	        while (dayOne.get(Calendar.YEAR) > dayTwo.get(Calendar.YEAR)) {
	            dayOne.add(Calendar.YEAR, -1);
	            // getActualMaximum() important for leap years
	            extraDays += dayOne.getActualMaximum(Calendar.DAY_OF_YEAR);
	        }

	        return extraDays - dayTwo.get(Calendar.DAY_OF_YEAR) + dayOneOriginalYearDays ;
	    }
	}

}
