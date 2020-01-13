package com.tqpp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class View
 */
@WebServlet("/View")
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public View() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		PrintWriter out=res.getWriter();
		res.setContentType("text/html");
		HttpSession http= req.getSession(true);
		String nm=(String) http.getAttribute("uemail");
		Set<Users> s=new LinkedHashSet<>();
		
		try {
			s=Bank_Dao.View(nm);
			out.println("<table border='1'><tr><th>Name</th><th>UserId</th><th>Delete</th><th>Pay</th></tr>");
			for(Users u:s)
			{
				out.println("<tr><td>"+u.getName()+"</td>");
				out.println("<td> "+u.getEmail()+"</td>");
				out.println("<td> <a href='Delete1?Email="+u.getEmail()+"'> Delete</a></td>");
				out.println("<td> <a href='Pay?Pemail="+u.getEmail()+"'>pay</a></td></tr>");			
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
