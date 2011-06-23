package org.vandelay.budget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.vandelay.budget.mbeans.BudgetCategory;
import org.vandelay.budget.mbeans.BudgetSummaryItem;
import org.vandelay.budget.mbeans.BudgetTransactionItem;
import org.vandelay.budget.model.Account;
import org.vandelay.budget.model.Budget;
import org.vandelay.budget.model.BudgetAmount;
import org.vandelay.budget.model.Split;

public class BudgetManager {
	public static BudgetSummaryItem [] summary() {
		List<BudgetSummaryItem> items = new ArrayList<BudgetSummaryItem>();
		Configuration config = new Configuration().configure("/hibernate.cfg.xml");
		SessionFactory sf = config.buildSessionFactory();
		Session s = sf.openSession();
		
		List<String> exclusions = Arrays.asList(new String [] {
				"2010","Dental Insurance","Group Term Life Insurance (Ben)","Health Insurance","Income Tax","Supplemental Long-Term Disability","Vision Insurance"
		});
		
		Budget b = (Budget) s.createQuery("from Budget where name='BSV Budget'").uniqueResult();
		for (BudgetAmount ba : b.getAmounts()) {
			Account a = ba.getAccount();
			
			// Skip exclusions
			if (a == null || a.getName() == null || exclusions.contains(a.getName())) {
				continue;
			}

			// Only look at the first budget period
			if (ba.getPeriodNum() != 0) {
				continue;
			}
			
			// Get the splits for this acct
			List<Split> splits = s.createQuery("from Split s where s.account = '"+a.getGuid()+"' and s.transaction.postDate >= '2011-06-01' and s.transaction.postDate < '2011-07-01'").list();
			float actual = 0;
			for (Split split : splits) {
				float amount = (float) split.getValueNum() / (float) split.getValueDenom();
				actual += amount;
				System.out.println("\t"+split.getTransaction().getPostDate()+", $"+amount+", "+split.getTransaction().getDescription());
			}
			System.out.println(a.getName()+": $"+actual);
			items.add(new BudgetSummaryItem(a.getGuid(), a.getName(), (float) ba.getAmountNum() / (float) ba.getAmountDenom(), actual));
		}
		s.close();
		sf.close();
		
		Collections.sort(items, new Comparator<BudgetSummaryItem>(){
			@Override
			public int compare(BudgetSummaryItem o1, BudgetSummaryItem o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		
		return items.toArray(new BudgetSummaryItem[items.size()]);
	}
	
	public static BudgetTransactionItem [] getItems(String accountGuid) {
		Configuration config = new Configuration().configure("/hibernate.cfg.xml");
		List<BudgetTransactionItem> items = new ArrayList<BudgetTransactionItem>();
		SessionFactory sf = config.buildSessionFactory();
		Session s = sf.openSession();
		Account a = (Account) s.createQuery("from Account a where a.guid='"+accountGuid+"'").uniqueResult();
		if (a != null) {
			// Get the splits for this acct
			List<Split> splits = s.createQuery("from Split s where s.account = '"+a.getGuid()+"' and s.transaction.postDate >= '2011-06-01' and s.transaction.postDate < '2011-07-01' order by s.transaction.postDate asc").list();
			for (Split split : splits) {
				float amount = (float) split.getValueNum() / (float) split.getValueDenom();
				String desc = split.getTransaction().getDescription();
				String memo = split.getMemo();
				if (memo != null && !"".equals(memo)) {
					desc = desc + " - " + memo;
				}
				
				items.add(new BudgetTransactionItem(split.getTransaction().getPostDate(), amount, desc));
			}
		}
		s.close();
		sf.close();
		return items.toArray(new BudgetTransactionItem[items.size()]);
	}
	
	public static BudgetCategory [] getCategories() {
		List<BudgetCategory> cats = new ArrayList<BudgetCategory>();
		Configuration config = new Configuration().configure("/hibernate.cfg.xml");
		SessionFactory sf = config.buildSessionFactory();
		Session s = sf.openSession();
		
		List<String> exclusions = Arrays.asList(new String [] {
				"2010","Dental Insurance","Group Term Life Insurance (Ben)","Health Insurance","Income Tax","Supplemental Long-Term Disability","Vision Insurance"
		});
		
		Budget b = (Budget) s.createQuery("from Budget where name='BSV Budget'").uniqueResult();
		for (BudgetAmount ba : b.getAmounts()) {
			Account a = ba.getAccount();
			
			// Skip exclusions
			if (a == null || a.getName() == null || exclusions.contains(a.getName())) {
				continue;
			}
			
			// Only look at the first budget period
			if (ba.getPeriodNum() != 0) {
				continue;
			}
			
			// Get the splits for this acct
			List<Split> splits = s.createQuery("from Split s where s.account = '"+a.getGuid()+"' and s.transaction.postDate >= '2011-06-01' and s.transaction.postDate < '2011-07-01'").list();
			if (splits.isEmpty()) {
				continue;
			}
			
			BudgetCategory c = new BudgetCategory();
			c.setAccountGuid(a.getGuid());
			c.setAccountName(a.getName());
			cats.add(c);
		}
		s.close();
		sf.close();
		return cats.toArray(new BudgetCategory[cats.size()]);
	}
}
