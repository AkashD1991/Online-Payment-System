package com.tqpp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddPayee
 */
@WebServlet("/AddPayee")
public class AddPayee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPayee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		String uemail=req.getParameter("uemail");
		String pemail=req.getParameter("pemail");
		PrintWriter out=res.getWriter();
		Users u1=new Users();
		Users u2=new Users();
		u1.setEmail(uemail);
		u2.setEmail(pemail);
		int ans;
			try {
				ans = Bank_Dao.AddPayee(u1,u2);
				if(ans>0)
				{
					out.println("Sucessfully Added");
					res.setContentType("text/html");
					/*out.println("<form><body>");
					out.println("Welcome " + u1.getEmail()+"<br><br>");
					out.println("Your Balance is :" + u1.getAmount()+"<br><br>");
					HttpSession http =req.getSession();
					http.setAttribute("uemail", u1.getEmail());
					out.println("<a href='Add'>Add Payee</a>");
					out.println("<a href='View'>View Payee</a>");
					out.println("<a href='Profile'>My Profile</a>");
					out.println("</form></body>");*/
					
				}
				else
				{
					out.println("Unable to Add Payee or Allready Added Payee");
					res.setContentType("text/html");
					/*out.println("<form><body>");
					out.println("Welcome " + u1.getEmail()+"<br><br>");
					out.println("Your Balance is :" + u1.getAmount()+"<br><br>");
					HttpSession http =req.getSession();
					http.setAttribute("uemail", u1.getEmail());
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
