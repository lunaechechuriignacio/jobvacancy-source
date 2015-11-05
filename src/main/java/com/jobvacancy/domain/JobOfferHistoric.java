package com.jobvacancy.domain;

public class JobOfferHistoric {
	private int countOfferHistoric;

	public JobOfferHistoric() {

		this.countOfferHistoric = 0;
	}

	public int getCountOfferHistoric() {

		return this.countOfferHistoric;
	}

	public void setAddOneCountOfferHistoric() {

		this.countOfferHistoric++;
	}

}
