package com.tqpp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Pay1
 */
@WebServlet("/Pay1")
public class Pay1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pay1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		PrintWriter out=res.getWriter();
		HttpSession http= req.getSession(true);
		String uemail=req.getParameter("uemail");
		String pemail=req.getParameter("pemail");
		int bal=Integer.parseInt(req.getParameter("amount"));
		try {
			int ans=Bank_Dao.Transfer(uemail,pemail,bal);
			if(ans>0)
			{
				Users u =Bank_Dao.getDetails(uemail);
				
				out.println("Transcation Sucessfull");
				res.setContentType("text/html");
				/*out.println("<form><body>");
				out.println("Welcome " + uemail+"<br><br>");
				out.println("Your Balance is :"+u.getAmount()+"<br><br>");
				out.println("<a href='Add'>Add Payee</a>");
				out.println("<a href='View'>View Payee</a>");
				out.println("<a href='Profile'>My Profile</a>");
				out.println("</form></body>");*/
			}
			else
			{
				Users u =Bank_Dao.getDetails(uemail);
				out.println("Insufficiant Balance");
				res.setContentType("text/html");
				/*out.println("<form><body>");
				out.println("Welcome " + uemail+"<br><br>");
				out.println("Your Balance is :"+u.getAmount()+"<br><br>");
				out.println("<a href='Add'>Add Payee</a>");
				out.println("<a href='View'>View Payee</a>");
				out.println("<a href='Profile'>My Profile</a>");
				out.println("</form></body>");*/
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
