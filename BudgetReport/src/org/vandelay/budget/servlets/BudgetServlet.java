package org.vandelay.budget.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vandelay.budget.BudgetManager;
import org.vandelay.budget.mbeans.BudgetCategory;
import org.vandelay.budget.mbeans.BudgetSummaryItem;
import org.vandelay.budget.mbeans.BudgetTransactionItem;

/**
 * Servlet implementation class BudgetServlet
 */
@WebServlet("/BudgetServlet")
public class BudgetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BudgetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		BudgetCategory [] cats = BudgetManager.getCategories(startDate, endDate);
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    	    
	    out.println("<HTML>");
	    out.println("<HEAD>");
	    //out.println("<LINK REL='stylesheet' TYPE='text/css' HREF='styles.css'/>");
	    out.println("<TITLE>Budget Summary</TITLE>");
	    out.println("</HEAD>");
	    out.println("<BODY>");

    	BudgetSummaryItem[] summary = BudgetManager.summary(startDate, endDate);
		out.println("<TABLE style='margin: 1em; border-collapse: collapse;'>");
		out.println("<THEAD style='background: #fc9;'>");
		out.println("<TR><TH>Category</TH><TH>Budgeted</TH><TH>Actual</TH><TH>Difference</TH></TR>");
		out.println("</THEAD>");
		out.println("<TBODY>");
    	for (BudgetSummaryItem item : summary) {
    		out.println("<TR>");
    		out.println("<TD style='border: 1px #ccc solid; padding: .3em;'><A HREF='#"+item.getName()+"'>"+item.getName()+"</A></TD>");
    		out.println("<TD style='border: 1px #ccc solid; text-align: right; padding: .3em;'>"+number(item.getBudgeted())+"</TD>");
    		out.println("<TD style='border: 1px #ccc solid; text-align: right; padding: .3em;'>"+number(item.getActual())+"</TD>");
    		out.println("<TD style='border: 1px #ccc solid; text-align: right; padding: .3em;'>"+number(item.getDifference(), true)+"</TD>");
    		out.println("</TR>");    		
    	}
		out.println("</TBODY>");
		out.println("</TABLE>");	
    	
    	for (BudgetSummaryItem item : summary) {
//    		out.println("<TABLE>");
//    		out.println("<THEAD>");
//    		out.println("<TR><TH>Budgeted</TH><TH>Actual</TH><TH>Difference</TH></TR>");
//    		out.println("</THEAD>");
//    		out.println("<TBODY>");
//    		out.println("<TR><TD>"+number(item.getBudgeted())+"</TD><TD>"+number(item.getActual())+"</TD><TD>"+number(item.getDifference(), true)+"</TD></TR>");
//    		out.println("</TBODY");
//    		out.println("</TABLE>");
    		
    		BudgetTransactionItem[] items = BudgetManager.getItems(item.getAccountGuid(), startDate, endDate);
    		if (items.length > 0) {
        		out.println("<P>");
        		out.println("<A NAME='"+item.getName()+"'/>");
        		out.println("<H2>"+item.getName()+"</H2>");
        		out.println("<TABLE style='margin: 1em; border-collapse: collapse;'>");
        		for (BudgetTransactionItem tx: items) {
        			out.println("<TR>");
        			out.println("<TD style='border: 1px #ccc solid; padding: .3em;'>"+date(tx.getPosted())+"</TD>");
        			out.println("<TD style='border: 1px #ccc solid; padding: .3em; text-align: right;'>"+number(tx.getAmount())+"</TD>");
        			out.println("<TD style='border: 1px #ccc solid; padding: .3em;'>"+tx.getDescription()+"</TD>");
        			out.println("</TR>");
        		}
        		out.println("</TABLE>");
    		}
    	}
	    
	    out.println("</BODY>");
	    out.println("</HTML>");
	}
	
	private String number(double value) {
		return number(value, false);
	}
	
	private String number(double value, boolean color) {
		StringBuilder sb = new StringBuilder();
		if (color) {
			String clazz = value > 0 ? "color: red;" : "color: green;";
			sb.append("<span style='"+clazz+"'>" + NumberFormat.getCurrencyInstance().format(value) + "</span>");			
		} else {
			sb.append(NumberFormat.getCurrencyInstance().format(value));
		}
		return sb.toString();
	}
	
	private String date(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
		return sdf.format(date);
	}	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
