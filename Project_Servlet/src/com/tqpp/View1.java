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
 * Servlet implementation class View1
 */
@WebServlet("/View1")
public class View1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public View1() {
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
		String pemail=req.getParameter("uemail");		
		Set<Users> s=new LinkedHashSet<>();
		
			try {
				s=Bank_Dao.View(pemail);
				out.println("<table border='1'><tr><th>Name</th><th>UserId</th></tr>");
				for(Users u:s)
				{
					out.println("<tr><td>"+u.getName()+"</td>");
					out.println("<td> "+u.getEmail()+"</td>");
					//out.println("<td> <a href='Delete?Email="+u.getEmail()+"'> Delete</a></td>");
					//out.println("<td> <a href='Pay?Pemail="+u.getEmail()+"'>pay</a></td></tr>");			
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
