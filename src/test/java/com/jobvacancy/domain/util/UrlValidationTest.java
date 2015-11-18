package com.jobvacancy.domain.util;

import org.junit.Assert;
import org.junit.Test;

public class UrlValidationTest {

	@Test
	public void urlValidaConHttps() {
		String urlValida = "https://www.dropbox.com/s/5cmg2gr84g4fmhz/cv.pdf?dl=0";

		UrlValidator url = new UrlValidator();
		Assert.assertTrue(url.validateUrl(urlValida));

	}

	@Test
	public void urlValidaConHttp() {
		String urlValida = "http://www.dropbox.com/s/5cmg2gr84g4fmhz/cv.pdf?dl=0";

		UrlValidator url = new UrlValidator();
		Assert.assertTrue(url.validateUrl(urlValida));

	}

	@Test
	public void urlValidaConFtp() {
		String urlValida = "ftp://ciencias.uis.edu.co";

		UrlValidator url = new UrlValidator();
		Assert.assertTrue(url.validateUrl(urlValida));
	}

	@Test
	public void urlInvalidaSinFtpHttpHttps() {
		String urlInvalida = "www.ciencias.uis.edu.co";

		UrlValidator url = new UrlValidator();
		Assert.assertFalse(url.validateUrl(urlInvalida));

	}
	
	@Test
	public void urlInvalidaSegunScenario() {
		String urlInvalida = "htls:\\//))dfadsf))){{{{{{{{.dropbox.com/cv.pdf";

		UrlValidator url = new UrlValidator();
		Assert.assertFalse(url.validateUrl(urlInvalida));

	}

}
