package com.peloteros.app.peloteros.entity;

public class PaymentIntentDTO {
	
	public enum Currency{
		USD,EUR,PEN;
	}
	
	private long amount;
    private String currency;
    private String description;
	
	public PaymentIntentDTO(long amount, String currency, String description) {
		super();
		this.amount = amount;
		this.currency = currency;
		this.description = description;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
