package com.jobvacancy.domain.util;

import org.junit.Assert;
import org.junit.Test;

public class DateValidatorTest {
	
    @Test
	public void validoFechaTest1() {
    	DateValidator dateValidator = new DateValidator();	
		Assert.assertTrue(dateValidator.validateDate("2015-12-15"));
	}
	
	@Test
	public void validoFechaTest2() {
		DateValidator dateValidator = new DateValidator();	
		Assert.assertFalse(dateValidator.validateDate("2015/12/15"));
	}
	@Test
	public void validoFechaTest3() {
		DateValidator dateValidator = new DateValidator();	
		Assert.assertFalse(dateValidator.validateDate("2015-13-01"));
	}
	
	@Test
	public void validoFechaTest4() {
		DateValidator dateValidator = new DateValidator();	
		Assert.assertFalse(dateValidator.validateDate("12/11/2015"));
	}
	
	@Test
	public void validoFechaTest5() {
		DateValidator dateValidator = new DateValidator();	
		Assert.assertFalse(dateValidator.validateDate("12-11-2015"));
	}
	
	@Test
	public void validoFechaTest6() {
		DateValidator dateValidator = new DateValidator();	
		Assert.assertFalse(dateValidator.validateDate(""));
	}	
}
