package org.vandelay.budget.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="transactions")
public class Transaction {
	/*
	 * delimiter $$

CREATE TABLE `transactions` (
  `guid` varchar(32) NOT NULL,
  `currency_guid` varchar(32) NOT NULL,
  `num` varchar(2048) CHARACTER SET utf8 NOT NULL,
  `post_date` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `enter_date` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `description` varchar(2048) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`guid`),
  KEY `tx_post_date_index` (`post_date`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$
	 */
	
	@Id
	private String guid;
	
	public String getGuid() {
		return guid;
	}
	
	@Column(name="post_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date postDate;
	
	public Date getPostDate() {
		return postDate;
	}
	
	@Column
	private String description;
	
	public String getDescription() {
		return description;
	}
}
