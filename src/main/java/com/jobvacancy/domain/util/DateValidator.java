package com.jobvacancy.domain.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidator {
	private Pattern pattern;
	private Matcher matcher;

	private static final String DATE_PATTERN = "^2[0-9][0-9][0-9]-[0-1][0-2]-[0-3][0-9]$";
	
	
	public DateValidator() {
		this.pattern = Pattern.compile(DATE_PATTERN);
	}

	public boolean validateDate(final String date) {
		
		this.matcher = this.pattern.matcher(date);
		return this.matcher.matches();
	}

}
