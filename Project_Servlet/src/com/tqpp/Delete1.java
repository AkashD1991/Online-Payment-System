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
 * Servlet implementation class Delete1
 */
@WebServlet("/Delete1")
public class Delete1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		String email=req.getParameter("Email");
		PrintWriter out=res.getWriter();
		int ans=0;
		res.setContentType("text/html");
		
		try {
			ans=Bank_Dao.DeletePayee(email);
			if(ans>0)
			{
				out.println("Deleted Sucessfully");
				//RequestDispatcher rd=req.getRequestDispatcher("View");
				//rd.include(req, res);
			}
			else
			{
				out.println("Deleted Unsucessfully");
				//RequestDispatcher rd=req.getRequestDispatcher("View");
				//rd.include(req, res);
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
