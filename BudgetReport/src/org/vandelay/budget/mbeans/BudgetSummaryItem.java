package org.vandelay.budget.mbeans;


public class BudgetSummaryItem {
	private String accountGuid;
	private String name;
	private float budgeted;
	private float actual;
	
	public BudgetSummaryItem(String accountGuid, String name, float budgeted, float actual) {
		this.accountGuid = accountGuid;
		this.name = name;
		this.budgeted = budgeted;
		this.actual = actual;
	}
	
	public String getName() {
		return name;
	}
	
	public float getBudgeted() {
		return budgeted;
	}
	
	public float getDifference() {
		return actual - budgeted;
	}
	
	public float getActual() {
		return actual;
	}
	
	public String getAccountGuid() {
		return accountGuid;
	}
}
