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
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		HttpSession http= req.getSession(true);
		String uemail=(String) http.getAttribute("uemail");
		PrintWriter out=res.getWriter();
		res.setContentType("text/html");
		Users u=new Users();
		try {
			u=Bank_Dao.getDetails(uemail);
			out.println("<body><form action='Update'>");
			out.println("Name: "+u.getName()+"<br><br>");
			out.println("User Name: "+u.getEmail()+"<br><br>");
			out.println("Birthdate: ");
			out.println("<input type='text' name='bdate' placeholder='"+u.getB_date()+"'><br><br>");
			out.println("Password:");
			out.println("<input type='password' name='pass' placeholder='Enter Password'><br><br>");
			out.println("<input type='Submit' value='Update'>");
			//out.println("<a href='Update'>Update</a>");
			out.println("</body></form>");
			http.setAttribute("uemail", uemail);
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
