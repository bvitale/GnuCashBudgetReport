package org.vandelay.budget.mbeans;


public class BudgetCategory {
	private String accountGuid;
	
	private String accountName;
	
	public void setAccountGuid(String accountName) {
		this.accountGuid = accountName;
		System.out.println("acct:"+accountName);
	}
	
	public String getAccountGuid() {
		return accountGuid;
	}
	
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public String getAccountName() {
		return accountName;
	}
}
