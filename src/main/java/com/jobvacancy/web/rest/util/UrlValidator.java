package com.jobvacancy.web.rest.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlValidator {
	private Pattern pattern;
	private Matcher matcher;

	private static final String URL_PATTERN =  "^(ftp|http|https)://?(([\\w!~*'().&=+$%-]+: )?[\\w!~*'().&=+$%-]+@)?(([0-9]{1,3}\\.){3}[0-9]{1,3}|([\\w!~*'()-]+\\.)*([\\w^-][\\w-]{0,61})?[\\w]\\.[a-z]{2,6})(:[0-9]{1,4})?((/*)|(/+[\\w!~*'().;?:@&=+$,%#-]+)+/*)$";
			
	public UrlValidator() {
		this.pattern = Pattern.compile(URL_PATTERN);
	}

	public boolean validateUrl(final String url) {

		this.matcher = this.pattern.matcher(url);
		return this.matcher.matches();

	}

}
