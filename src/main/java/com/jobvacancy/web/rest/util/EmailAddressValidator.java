package com.jobvacancy.web.rest.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailAddressValidator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			
	public EmailAddressValidator() {
		this.pattern = Pattern.compile(EMAIL_PATTERN);
	}

	public boolean validateEmail(final String emailAddress) {

		this.matcher = this.pattern.matcher(emailAddress);
		return this.matcher.matches();

	}
}