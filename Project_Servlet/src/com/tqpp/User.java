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
 * Servlet implementation class User
 */
@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		HttpSession http= req.getSession(true);
		String nm=(String) http.getAttribute("uemail");
		
		PrintWriter out=res.getWriter();
		res.setContentType("text/html");
		Set<Users> s=new LinkedHashSet<>();
		
	
				try {
					s=Bank_Dao.VUser();
					
					out.println("<table border='1'><tr><th>Account_no</th><th>Name</th><th>UserId</th><th>Bdate</th><th>Balance</th><th>Role</th><th>IsActive</th><th>Enable</th><th>Disable</th><th>View Payee</th><th>Delete</th><th>View Transaction</th></tr>");
					for(Users u:s)
					{						
						out.println("<tr><td>"+u.getAccount()+"</td>");
						out.println("<td>"+u.getName()+"</td>");
						out.println("<td> "+u.getEmail()+"</td>");
						out.println("<td>"+u.getB_date()+"</td>");
						out.println("<td>"+u.getAmount()+"</td>");
						out.println("<td>"+u.getRolen()+"</td>");
						out.println("<td>"+u.getIsActiveName()+"</td>");
						out.println("<td><a href='Enable?uemail="+u.getEmail()+"'>Enable</a></td>");
						out.println("<td><a href='Disable?uemail="+u.getEmail()+"'>Disable</a></td>");
						out.println("<td><a href='View1?uemail="+u.getEmail()+"'>View Payee</a></td>");
						out.println("<td><a href='Delete?uemail="+u.getEmail()+"'>Delete User</a></td>");
						out.println("<td><a href='VPTran?uemail="+u.getEmail()+"'>View Transaction</a></td></tr>");
						System.out.println("in View" + u.getAccount());
						System.out.println("in View" + u.getEmail());
						System.out.println("in View" + u.getAmount());
						System.out.println("in View" + u.getRole());
						System.out.println("in View" + u.getIsActiveName());
						
					}	
					out.println("</table>");
				} catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

	private HttpSession getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
