package org.vandelay.budget.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="splits")
public class Split {
	/*
	 * delimiter $$

CREATE TABLE `splits` (
  `guid` varchar(32) NOT NULL,
  `tx_guid` varchar(32) NOT NULL,
  `account_guid` varchar(32) NOT NULL,
  `memo` varchar(2048) CHARACTER SET utf8 NOT NULL,
  `action` varchar(2048) CHARACTER SET utf8 NOT NULL,
  `reconcile_state` varchar(1) CHARACTER SET utf8 NOT NULL,
  `reconcile_date` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `value_num` bigint(20) NOT NULL,
  `value_denom` bigint(20) NOT NULL,
  `quantity_num` bigint(20) NOT NULL,
  `quantity_denom` bigint(20) NOT NULL,
  `lot_guid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`guid`),
  KEY `splits_tx_guid_index` (`tx_guid`),
  KEY `splits_account_guid_index` (`account_guid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$
	 */
	@Id
	@Column
	private String guid;
	
	public String getGuid() {
		return guid;
	}
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="tx_guid")
	private Transaction transaction;
	
	public Transaction getTransaction() {
		return transaction;
	}
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="account_guid")
	private Account account;
	
	public Account getAccount() {
		return account;
	}
	
	@Column(name="value_num")
	private long valueNum;
	
	public long getValueNum() {
		return valueNum;
	}
	
	@Column(name="value_denom")
	private long valueDenom;
	
	public long getValueDenom() {
		return valueDenom;
	}
	
	@Column(name="memo")
	private String memo;
	
	public String getMemo() {
		return memo;
	}
}
