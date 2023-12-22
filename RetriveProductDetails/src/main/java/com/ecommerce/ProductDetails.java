package com.ecommerce;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@SuppressWarnings("serial")

public class ProductDetails extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw= response.getWriter();
		
		int id=Integer.parseInt(request.getParameter("id"));
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "master");
			PreparedStatement ps = con.prepareStatement("select * from eproduct where ID=?");
			ps.setInt(1, id);
			ResultSet res = ps.executeQuery();
			if(res.next())
			{
				pw.println("<h2>Your Product Detailas are...</h2>");
				pw.println("id of the product : "+res.getInt(1)+"<br>product name : "+res.getString(2)+"<br>price : "+res.getFloat(3));
				pw.println("<br><br><a href=\"/RetriveProductDetails\">Go back to Previous Menu</a>");
				res.close();
			
			}
			else
			{
				pw.println("<br> <h2>Please enter the correct id !!</h2>");
				pw.println("<br><br><a href=\"/RetriveProductDetails\">Go back to Previous Menu</a>");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		response.setContentType("text/html");
		RequestDispatcher req = request.getRequestDispatcher("index.html");
		req.include(null, response);
		
}
}