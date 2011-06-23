package org.vandelay.budget.mbeans;

import java.util.Date;

public class BudgetTransactionItem {
	private Date posted;
	private float amount;
	private String description;
	
	public BudgetTransactionItem(Date posted, float amount, String description) {
		this.posted = posted;
		this.amount = amount;
		this.description = description;
	}
	
	public Date getPosted() {
		return posted;
	}
	
	public float getAmount() {
		return amount;
	}
	
	public String getDescription() {
		return description;
	}
}
