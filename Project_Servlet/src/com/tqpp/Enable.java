package com.tqpp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Enable
 */
@WebServlet("/Enable")
public class Enable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Enable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		String uemail=req.getParameter("uemail");
		System.out.println(uemail);
		
		int ans;
		try {
			ans = Bank_Dao.ActiveE(uemail);
			if(ans>0)
			{
				res.setContentType("text/html");
				PrintWriter out=res.getWriter();  
		        out.print("User Is Eanble");
		          
			}
			else
			{
				res.setContentType("text/html");
				PrintWriter out=res.getWriter();  
		        out.print("User Is Deleted ");
		         req.getRequestDispatcher("Login.html").include(req,res); 
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
