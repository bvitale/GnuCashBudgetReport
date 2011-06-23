package org.vandelay.budget.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="accounts")
public class Account {
/*
 * delimiter $$

CREATE TABLE `accounts` (
  `guid` varchar(32) NOT NULL,
  `name` varchar(2048) CHARACTER SET utf8 NOT NULL,
  `account_type` varchar(2048) CHARACTER SET utf8 NOT NULL,
  `commodity_guid` varchar(32) DEFAULT NULL,
  `commodity_scu` int(11) NOT NULL,
  `non_std_scu` int(11) NOT NULL,
  `parent_guid` varchar(32) DEFAULT NULL,
  `code` varchar(2048) CHARACTER SET utf8 DEFAULT NULL,
  `description` varchar(2048) CHARACTER SET utf8 DEFAULT NULL,
  `hidden` int(11) DEFAULT NULL,
  `placeholder` int(11) DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$
 */
	@Id
	@Column(name="guid")
	private String guid;
	
	public String getGuid() {
		return guid;
	}
	
	@Column(name="name", nullable=false)
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
