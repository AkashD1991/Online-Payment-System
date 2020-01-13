package com.tqpp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		String email=req.getParameter("Email");
		String pass=req.getParameter("Pass");
		
		Users u=new Users();
		u.setEmail(email);
		u.setPassword(pass);
		
		Users u1 = new Users();
		try {
			Boolean ans = Bank_Dao.Check_Login(u);
			res.setContentType("text/html");
			PrintWriter out=res.getWriter();
			if(ans)
			{
				u1 = Bank_Dao.getDetails(email);
				if(u1.getRole()==1)
				{
					out.println("<body>");
					out.println("<div width=\"50%\">\r\n" + 
							"<img src=\"http://localhost:8081/Project_Servlet/Images/Logo2.png\" height=\"100\"><b>Welcome Apna Bank</b>\r\n" + 
							"</div><br><br>");
					out.println("<form>");
					out.println("Welcome Admin: " + u1.getEmail()+"<br><br>");
					//out.println("Your Balance is :" + u1.getAmount()+"<br><br>");
						
					HttpSession http =req.getSession();
					http.setAttribute("uemail", u1.getEmail());
					out.println("<a href='Vtran' target='Akash'>View Transaction</a>");
					out.println("<a href='User' target='Akash'>View Users</a>");
					out.println("<a href='Profile' target='Akash'>My Profile</a><br><br>");
					out.println("<button onclick='href ='Logout?Email='"+email+"''>Logout</button><br><br>");
					out.println("<iframe name='Akash' height='70%' width='100%'>");
					out.println("</iframe></form></body>");
				}
				else
				{
				  if(u1.getIsActiveId()==1)
				  {
					out.println("<body>");
					out.println("<div width=\"50%\">\r\n" + 
							"<img src=\"http://localhost:8081/Project_Servlet/Images/Logo2.png\" height=\"100\"><b>Welcome Apna Bank</b>\r\n" + 
							"</div><br><br>");
					out.println("<form>");
					out.println("Welcome " + u1.getEmail()+"<br><br>");
					out.println("Your Balance is :" + u1.getAmount()+"<br><br>");
					HttpSession http =req.getSession();
					http.setAttribute("uemail", u1.getEmail());
					
					out.println("<a href='Add' target='Akash'>Add Payee</a>");
					out.println("<a href='View' target='Akash'>View Payee</a>");
					out.println("<a href='Utran' target='Akash'>View Transaction</a>");
					out.println("<a href='Profile' target='Akash'>My Profile</a><br><br>");
					out.println("<button onclick='href ='Logout?Email='"+email+"''>Logout</button><br><br>");
					out.println("<iframe  id=1 name='Akash' height='50%' width='50%'>");
					out.println("</iframe></form></body>");
				  }
				  else
				  {
					  out.println("User is Disable Please Conatct Admin");
					  RequestDispatcher rd=req.getRequestDispatcher("Login.html");
					  rd.include(req,res);
				  }
				}
			}
			else
			{
				out.println("Invalid Username And Password");
				RequestDispatcher rd=req.getRequestDispatcher("Login.html");
				rd.include(req,res);
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
