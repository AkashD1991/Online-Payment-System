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
 * Servlet implementation class Pay
 */
@WebServlet("/Pay")
public class Pay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pay() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		PrintWriter out=res.getWriter();
		String pemail=req.getParameter("Pemail");
		res.setContentType("text/html");
		HttpSession http= req.getSession(true);
		String uemail=(String) http.getAttribute("uemail");
		Users u=new Users();
		
		
		try {
			u=Bank_Dao.getDetails(uemail);
			out.println("<body><form action='Pay1'>");
			out.println("<h1>Transfer Details</h1>");
			out.println("Welcome: "+uemail+"<br><br>");
			out.println("<input type='hidden' name='uemail' value='"+uemail+"'>");
			out.println("<input type='hidden' name='pemail' value='"+pemail+"'>");
			out.println("Avialble Balance in Rupees :"+u.getAmount()+" <br><br>");
			out.println("Payee Name:"+pemail+" <br><br>");
			
			out.println("Enter Amount to Pay:");
			out.println("<input type='text' name='amount'><br><br>");
			//out.println("<a href='Pay1'>Pay</a>");
			
			out.println("<input type='submit' value='Pay'>");
			out.println("</form></body>");
			
		
		//	http.setAttribute("uemail", uemail);
		//	http.setAttribute("pemail", pemail);
		//	http.setAttribute("bal","name");
			
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
