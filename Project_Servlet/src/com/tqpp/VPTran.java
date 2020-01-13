package com.tqpp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class VPTran
 */
@WebServlet("/VPTran")
public class VPTran extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VPTran() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		HttpSession http= req.getSession(true);
		//String nm=(String) http.getAttribute("uemail");
		String nm=req.getParameter("uemail");
		PrintWriter out=res.getWriter();
		res.setContentType("text/html");
		Set<Tran> s=new LinkedHashSet<>();
		
				try {
					s=Bank_Dao.Vtran(nm);
					out.println("<table border='1'><tr><th>Transaction_id</th><th>UserId</th><th>PayeeId</th><th>Date</th><th>Amount</th></tr>");
					for(Tran u:s)
					{				
						out.println("<tr><td>"+u.getTran_id()+"</td>");
						out.println("<td> "+u.getUser_id()+"</td>");
						out.println("<td>"+u.getPayee_id()+"</td>");
						out.println("<td>"+u.getDate()+"</td>");
						out.println("<td>"+u.getAmount()+"</td></tr>");
					}	
					out.println("</table>");
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
