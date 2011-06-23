package org.vandelay.budget.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="budget_amounts")
public class BudgetAmount {
	@Id
	@Column
	private int id;
	
	public int getId() {
		return id;
	}
	
	@Column(name="amount_num")
	private long amountNum;
	
	public long getAmountNum() {
		return amountNum;
	}
	
	@Column(name="amount_denom")
	private long amountDenom;
	
	public long getAmountDenom() {
		return amountDenom;
	}
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="account_guid")
	@NotFound(action=NotFoundAction.IGNORE)
	private Account account;
	
	public Account getAccount() {
		return account;
	}

	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="budget_guid")
	private Budget budget;
	
	public Budget getBudget() {
		return budget;
	}
	
	@Column(name="period_num")
	private int periodNum;
	
	public int getPeriodNum() {
		return periodNum;
	}
	/*
	 * delimiter $$

CREATE TABLE `budget_amounts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `budget_guid` varchar(32) NOT NULL,
  `account_guid` varchar(32) NOT NULL,
  `period_num` int(11) NOT NULL,
  `amount_num` bigint(20) NOT NULL,
  `amount_denom` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2675 DEFAULT CHARSET=latin1$$
	 */
}
