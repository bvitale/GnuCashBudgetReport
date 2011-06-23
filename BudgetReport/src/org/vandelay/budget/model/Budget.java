package org.vandelay.budget.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="budgets")
public class Budget {
	/*delimiter $$

CREATE TABLE `budgets` (
  `guid` varchar(32) NOT NULL,
  `name` varchar(2048) CHARACTER SET utf8 NOT NULL,
  `description` varchar(2048) CHARACTER SET utf8 DEFAULT NULL,
  `num_periods` int(11) NOT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$
*/
	@Id
	@Column
	private String guid;
	
	public String getGuid() {
		return guid;
	}
	
	@Column
	private String name;
	
	public String getName() {
		return name;
	}
	
	@Column
	private String description;
	
	public String getDescription() {
		return description;
	}
	
	@Column(name="num_periods")
	private int numPeriods;
	
	public int getNumPeriods() {
		return numPeriods;
	}
	
	@OneToMany(mappedBy="budget")
	private List<BudgetAmount> amounts = new ArrayList<BudgetAmount>();
	
	public List<BudgetAmount> getAmounts() {
		return amounts;
	}
}
