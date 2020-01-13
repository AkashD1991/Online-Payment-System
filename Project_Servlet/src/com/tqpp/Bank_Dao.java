package com.tqpp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Bank_Dao
{
	
	public static Connection getConnection()
	{
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 try {
			con=DriverManager.getConnection
					("jdbc:mysql://localhost:3306/Project","root","root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	public static Boolean Check_Login(Users u) throws SQLException
	{
		Connection con=Bank_Dao.getConnection();
		
			PreparedStatement pt=con.prepareStatement("select Email,Password from users where Email=?");
			pt.setString(1,u.getEmail());
			ResultSet rs=pt.executeQuery();
			
			while(rs.next())
			{
				String demail=rs.getString(1);
				String dpass=rs.getString(2);
			if(demail.equals(u.getEmail()) && dpass.equals(u.getPassword()))
			{
				return true;
			}
			}
			return false;

	}
	public static Users getDetails(String email) throws SQLException
	{
		Connection con=Bank_Dao.getConnection();
			PreparedStatement pt=con.prepareStatement("select Name,Email,B_Date,Password,Balance,Role_id,IsActiveId from users where Email=?");
			pt.setString(1,email);
			ResultSet rs=pt.executeQuery();
			Users u=new Users();
			while(rs.next())
			{
				String name=rs.getString(1);
				String demail=rs.getString(2);
				String b_date=rs.getString(3);
				String pass=rs.getString(4);
				int damt=rs.getInt(5);
				int role=rs.getInt(6);
				int isActiveId=rs.getInt(7);
				if(demail.equals(email))
				{
					u.setName(name);
					u.setEmail(demail);
					u.setB_date(b_date);
					u.setPassword(pass);
					u.setAmount(damt);
					u.setRole(role);
					u.setIsActiveId(isActiveId);
					return u;
				}
			}			
			return u;
	}
	public static int Save(Users u) throws SQLException 
	{
		int status=0;
		Connection con=Bank_Dao.getConnection();
		if(u.getRole()==1)
		{
			PreparedStatement pt=con.prepareStatement("insert into Users(Name,Email,B_Date,Password,Balance,Role_id,IsActiveId) values(?,?,?,?,?,?,?)");
			pt.setString(1,u.getName());
			pt.setString(2,u.getEmail());
			pt.setString(3,u.getB_date());
			pt.setString(4,u.getPassword());
			pt.setInt(5,0);
			pt.setInt(6, u.getRole());
			pt.setInt(7,1);
			status=pt.executeUpdate();
		}
		else
		{
		PreparedStatement pt=con.prepareStatement("insert into Users(Name,Email,B_Date,Password,Balance,Role_id,IsActiveId) values(?,?,?,?,?,?,?)");
		pt.setString(1,u.getName());
		pt.setString(2,u.getEmail());
		pt.setString(3,u.getB_date());
		pt.setString(4,u.getPassword());
		pt.setInt(5,u.getAmount());
		pt.setInt(6, u.getRole());
		pt.setInt(7,1);
		status=pt.executeUpdate();
		}
		return status;
	}
	
	public static int AddPayee(Users u1, Users u2) throws SQLException 
	{
		Connection con=Bank_Dao.getConnection();
		int ans=0;
		
		PreparedStatement pt1=con.prepareStatement("select * from Payee");
		ResultSet rs1=pt1.executeQuery();
		while(rs1.next())
		{
			String uemail=rs1.getString(1);
			String pemail=rs1.getString(2);
			
			if(uemail.equals(u1.getEmail()) && pemail.equals(u2.getEmail()))
			{
				return 0;
			}
		}
				PreparedStatement pt=con.prepareStatement("select * from users");
				ResultSet rs=pt.executeQuery();
				while(rs.next())
				{
					String Email=rs.getString(3);
					if(Email.equals(u2.getEmail()))
					{
						PreparedStatement pt2=con.prepareStatement("insert into payee values(?,?)");
						pt2.setString(1,u1.getEmail());
						pt2.setString(2,u2.getEmail());
						ans=pt2.executeUpdate();
						return ans;
					}
			}
		return ans;
	}
	public static Set<Users> View(String nm) throws SQLException
	{
		Connection con=Bank_Dao.getConnection();
		int ans=0;
		PreparedStatement pt=con.prepareStatement("select u.* from users u,payee p where u.Email = p.Payeeid and p.Userid = ?;");
		pt.setString(1, nm);
		ResultSet rs=pt.executeQuery();
		Set<Users> s=new LinkedHashSet<>();
		while(rs.next())
		{
			String name=rs.getString(2);
			String email=rs.getString(3);
			String B_date=rs.getString(4);
			String password=rs.getString(5);
			int balance=rs.getInt(6);
			s.add(new Users(name,email,B_date,password,balance));
		}
		return s;		
	}
	
	public static int DeletePayee(String email) throws SQLException 
	{
		System.out.println(email);
		Connection con=Bank_Dao.getConnection();
		int ans=0;
		PreparedStatement pt=con.prepareStatement("delete from payee where PayeeId=?");
		pt.setString(1,email);
		ans=pt.executeUpdate();
		return ans;
	}
	public static int DeleteUser(String email) throws SQLException 
	{
		Connection con=Bank_Dao.getConnection();
		int ans=0;
		PreparedStatement pt=con.prepareStatement("delete from users where Email=?");
		pt.setString(1,email);
		ans=pt.executeUpdate();
		return ans;
	}
	
	public static int Transfer(String uemail,String pemail,int a) throws SQLException
	{
		Connection con=Bank_Dao.getConnection();
		int ans=0;
		int bal1 = 0;
		int bal2 = 0;
		int bal=a;
		PreparedStatement pt=con.prepareStatement("Select * from users");
		ResultSet rs=pt.executeQuery();
		while(rs.next())
		{
			String s2=rs.getString(3);
			if(uemail.equals(s2))
			{
				bal1=rs.getInt(6);
				if(bal>bal1)
				{
					return 0;
				}
				else
				{
				PreparedStatement pt1=con.prepareStatement("Select * from users");
				ResultSet rs1=pt1.executeQuery();
				while(rs1.next())
				{
				String s3=rs1.getString(3);
				if(pemail.equals(s3))
				{
					bal2=rs1.getInt(6);
				}	
				}
				}
						bal1=bal1-bal;
						bal2=bal2+bal;
						 
						 
						PreparedStatement pt2=con.prepareStatement("UPDATE users SET Balance=IF(Email=?,?,Balance),Balance=IF (Email=?,?,Balance);");
						pt2.setString(1,uemail);
						pt2.setInt(2,bal1);
						pt2.setString(3,pemail);
						pt2.setInt(4,bal2);
						ans=pt2.executeUpdate();
						
						PreparedStatement pt3=con.prepareStatement("insert into transaction(User_id,Payee_id,Date,Amount) values(?,?,?,?)");
						pt3.setString(1,uemail);
						pt3.setString(2,pemail);
						pt3.setString(3,"08/01/2020");
						pt3.setInt(4,a);
						ans=pt3.executeUpdate();
						
						return ans;
			}
		}			
			return ans;
	}
	public static int Update(String uemail, String bdate, String pass) throws SQLException
	{
		Connection con=Bank_Dao.getConnection();
		int ans=0;
		PreparedStatement pt=con.prepareStatement("Update users set B_Date=?,Password=? where Email=?");
		
		pt.setString(1,bdate);
		pt.setString(2,pass);
		pt.setString(3,uemail);
		ans=pt.executeUpdate();
		return ans;
	}
	public static Set<Tran> Vtran() throws SQLException
	{
		Connection con=Bank_Dao.getConnection();
		PreparedStatement pt=con.prepareStatement("select * from transaction");
		ResultSet rs=pt.executeQuery();
		Set<Tran> s=new LinkedHashSet<>();
		while(rs.next())
		{
			int tran_id=rs.getInt(1);
			String email=rs.getString(2);
			String Payee_id=rs.getString(3);
			String date=rs.getString(4);
			int balance=rs.getInt(5);
			s.add(new Tran(tran_id,email,Payee_id,date,balance));
		}
		return s;
	}
	public static Set<Tran> Vtran(String pemail) throws SQLException
	{
		
		Connection con=Bank_Dao.getConnection();
		PreparedStatement pt=con.prepareStatement("select * from transaction where User_id=?");
		pt.setString(1,pemail);
		ResultSet rs=pt.executeQuery();
		Set<Tran> s=new LinkedHashSet<>();
		while(rs.next())
		{
			int tran_id=rs.getInt(1);
			String email=rs.getString(2);
			String Payee_id=rs.getString(3);
			String date=rs.getString(4);
			int balance=rs.getInt(5);
			s.add(new Tran(tran_id,email,Payee_id,date,balance));
		}
		return s;
	}
	public static Set<Users> VUser() throws SQLException
	{
		Connection con=Bank_Dao.getConnection();
		PreparedStatement pt=con.prepareStatement("select u.Account_No,u.Name,u.Email,u.B_Date,u.Balance,u.Role_id,r.Role_name ,i.Name,u.IsActiveId,i.Id\r\n" + 
				"from users u,role r,isactive i\r\n" + 
				"where r.Role_id=u.Role_id and u.IsActiveId=i.Id;");

		ResultSet rs=pt.executeQuery();
		Set<Users> s=new LinkedHashSet<>();
		while(rs.next())
		{
			int account=rs.getInt(1);
			String name=rs.getString(2);
			String email=rs.getString(3);
			String bdate=rs.getString(4);
			int balance=rs.getInt(5);
			int role_id=rs.getInt(6);
			String role_name=rs.getString(7);
			int isActiveId=rs.getInt(9);
			String isActiveName=rs.getString(8);
			s.add(new Users(account,name,email,bdate,balance,role_name,isActiveName));
		}
		return s;
	}
	public static LinkedHashSet<Tran> Utran(String uemail) throws SQLException 
	{
		Connection con=Bank_Dao.getConnection();
		PreparedStatement pt=con.prepareStatement("select * from transaction where User_id=?");
		pt.setString(1,uemail);
		ResultSet rs=pt.executeQuery();
		LinkedHashSet<Tran> s=new LinkedHashSet<>();
		while(rs.next())
		{
			int tran_id=rs.getInt(1);
			String email=rs.getString(2);
			String Payee_id=rs.getString(3);
			String date=rs.getString(4);
			int balance=rs.getInt(5);
			s.add(new Tran(tran_id,email,Payee_id,date,balance));
		}
		return s;
	}
	public static int ActiveE(String uemail) throws SQLException
	{
		int ans=0;
		Connection con=Bank_Dao.getConnection();
		
		PreparedStatement pt=con.prepareStatement("Update users set IsActiveId=? where Email=?");
		pt.setInt(1,1);
		pt.setString(2,uemail);
		ans=pt.executeUpdate();
		return ans;
	}
	public static int ActiveD(String uemail) throws SQLException
	{
		int ans=0;
		Connection con=Bank_Dao.getConnection();
		
		PreparedStatement pt=con.prepareStatement("Update users set IsActiveId=? where Email=?");
		pt.setInt(1,2);
		pt.setString(2,uemail);
		ans=pt.executeUpdate();
		return ans;
	}
	public static int Print() throws SQLException, IOException 
	{

		Connection con=Bank_Dao.getConnection();
		PreparedStatement pt=con.prepareStatement("select * from transaction");
		ResultSet rs=pt.executeQuery();
		Set<Tran> s=new LinkedHashSet<>();
		while(rs.next())
		{
			String tran_id=Integer.toString(rs.getInt(1));
			String email=rs.getString(2);
			String Payee_id=rs.getString(3);
			String date=rs.getString(4);
			String balance=Integer.toString(rs.getInt(5));
			//s.add(new Tran(tran_id,email,Payee_id,date,balance));
			
			FileOutputStream file=new FileOutputStream("C://Users//Aaksj//Desktop//Bank//Demo.txt");
			ObjectOutputStream out=new ObjectOutputStream(file);
			out.writeObject(tran_id);
			out.writeObject(email);
			out.writeObject(Payee_id);
			out.writeObject(date);
			out.writeObject(balance);
			out.writeObject("\n");
			out.close();
			file.close();
			System.out.println("Sucess");
			
		}
		return 1;
	}
}
