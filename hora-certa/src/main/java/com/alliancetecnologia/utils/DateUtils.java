/**
 * 
 */
package com.alliancetecnologia.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

/**
 * @author Lucas P. Soares (lucasperes20@gmail.com)
 * @date 19 de mai de 2019
 *
 */
public class DateUtils {
	
	public static Temporal convertDate(String date) {
		Temporal result = null;
		if(date != null) {
			if(date.matches(Constantes.REGEX_DATE_BRAZILLIAN)) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constantes.PATTERN_DATE_BRAZILLIAN);
				result = LocalDate.parse(date, formatter);
			}
		}
		return result;
	}
	
	public static String formatterDate(Temporal date) {
		String result = null;
		if(date != null) {
			if(date instanceof LocalDate) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constantes.PATTERN_DATE_BRAZILLIAN);
				result = formatter.format(date);
			} else if(date instanceof LocalDateTime) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constantes.PATTERN_DATE_TIME_BRAZILLIAN);
				result = formatter.format(date);
			}
		}
		return result;
	}
	
	public static String formatterDate(Object date) {
		String result = null;
		if(date != null) {
			if(date instanceof LocalDate) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constantes.PATTERN_DATE_BRAZILLIAN);
				result = formatter.format((LocalDate) date);
			}
		}
		return result;
	}

}
