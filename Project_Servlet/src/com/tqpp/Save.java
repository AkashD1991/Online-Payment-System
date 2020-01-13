package com.tqpp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Save
 */
@WebServlet("/Save")
public class Save extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Save() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		int status=0;
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String b_date=req.getParameter("bdate");
		String password=req.getParameter("pass");
		String cpassword=req.getParameter("pass1");
		int amount=Integer.parseInt(req.getParameter("amt"));
		int role=Integer.parseInt(req.getParameter("role"));
		PrintWriter out=res.getWriter();
		Users u=new Users();
		u.setName(name);
		u.setEmail(email);
		u.setB_date(b_date);
		u.setPassword(password);
		u.setAmount(amount);
		u.setRole(role);
		res.setContentType("text/html");
		if(password.equals(cpassword))
		{
			try {
				status=Bank_Dao.Save(u);
				if(status>0)
				{
					out.println("Successfully Added");
					RequestDispatcher rd=req.getRequestDispatcher("Login.html");
					rd.include(req, res);
				}
				else
				{
					out.println("Unable to add");
					RequestDispatcher rd=req.getRequestDispatcher("Create.html");
					rd.include(req, res);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			out.println("Entered Mis-Match Password");
			RequestDispatcher rd=req.getRequestDispatcher("Create.html");
			rd.include(req, res);
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
