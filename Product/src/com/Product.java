package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Product {
	public Connection connect()
	{ 
	 Connection con = null; 
	 
	 try 
	 { 
	 Class.forName("com.mysql.cj.jdbc.Driver"); 
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/product_mng", "root", ""); 
	 //For testing
	 System.out.print("Successfully connected"); 
	 } 
	 catch(Exception e) 
	 { 
	 e.printStackTrace(); 
	 } 
	 
	 return con; 
	}



	public String insertItem(String code, String name, String desc, String resc, String email) {
		
		 String output = "";
		 
		 try {
		
		Connection con = connect();
		if (con == null) 
		{ 
		return "Error while connecting to the database"; 
		}
		
		// create a prepared statement
		String query = "insert into product (ItemID,ItemCode, ItemName, Description, Researcher, Email)" + " values (?, ?, ?, ?, ?, ?)"; 
		PreparedStatement preparedStmt = con.prepareStatement(query); 
		// binding values
		preparedStmt.setInt(1, 0); 
		preparedStmt.setString(2, code); 
		preparedStmt.setString(3, name); 
		preparedStmt.setString(4, desc);
		preparedStmt.setString(5, resc);
		preparedStmt.setString(6, email);
		
		System.out.println(code);
		 
		 preparedStmt.execute(); 
		 con.close(); 
		 
		 String newItems = readItems();
		 output =  "{\"status\":\"success\", \"data\": \"" + newItems + "\"}"; 
		 } 

		catch (Exception e) 
		 { 
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";  
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}


	public String readItems()
	{ 
			 String output = ""; 
			try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 { 
			 return "Error while connecting to the database for reading."; 
			 } 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Product Code</th>" 
			 +"<th>Product Name</th><th>Product Description</th>"
			 + "<th>Product Researcher</th>" 
			 + "<th>Product Email</th>" 
			 + "<th>Update</th><th>Remove</th></tr>"; 
			 
			 String query = "select * from product"; 
			 
			 java.sql.Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
			 String ItemID = Integer.toString(rs.getInt("ItemID")); 
			 String ItemCode = rs.getString("ItemCode"); 
			 String ItemName = rs.getString("ItemName"); 
			 String Description = rs.getString("Description"); 
			 String Researcher = rs.getString("Researcher"); 
			 String Email = rs.getString("Email"); 

			 // Add a row into the html table
			 output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + ItemID + "'>" + ItemCode + "</td>";
			 
			 output += "<td>" + ItemName + "</td>"; 
			 
			 output += "<td>" + Description + "</td>";
			 
			 output += "<td>" + Researcher + "</td>";
			 
			 output += "<td>" + Email + "</td>";
			 // buttons
			 output += "<td><input name='btnUpdate' " 
			 + " type='button' value='Update' class =' btnUpdate btn btn-secondary'data-pid='" + ItemID + "'></td>"
			 + "<td><form method='post' action='Product.jsp'>"
			 + "<input name='btnRemove' " 
			 + " type='button' value='Remove' class='btnRemove btn btn-danger' data-pid='" + ItemID + "'>"
			 + "<input name='hidItemIDDelete' type='hidden' " 
			 + " value='" + ItemID + "'>" + "</form></td></tr>"; 
			 } 
			 con.close(); 
			 // Complete the html table
			 output += "</table>"; 
			 } 
			catch (Exception e) 
			 { 
			 output = "Error while reading the items."; 
			 System.err.println(e.getMessage()); 
			 } 
			return output; 
		}

	public String deleteItem(String ItemID){ 
		String output = ""; 
		
	     try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for deleting."; 
		 } 
		 // create a prepared statement
		 String query = "delete from product where ItemID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(ItemID)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItems = readItems();
		 output =  "{\"status\":\"success\", \"data\": \"" + newItems + "\"}"; 
		 } 
	
		catch (Exception e) 
		 { 
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";  
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
	}

	public String updateItem(String ID, String code, String name, String desc, String resc, String email ){
		String output = "";
		try {
		Connection conn = connect();
		if (conn == null) {
		return "Error while connecting to the database for updating.";
		}

		// create a prepared statement
		String query = "UPDATE product SET ItemCode=?,ItemName=?,Description=?,Researcher=?,Email=? WHERE ItemID=?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		//binding values
		preparedStmt.setString(1, code);
		preparedStmt.setString(2, name);
		preparedStmt.setString(3, desc);
		preparedStmt.setString(4, resc);
		preparedStmt.setString(5, email);
		preparedStmt.setInt(6, Integer.parseInt(ID));
		//execute the statement
		preparedStmt.execute();
		conn.close();
		String newItems = readItems();
		 output =  "{\"status\":\"success\", \"data\": \"" + newItems + "\"}"; 
		 } 
	
		catch (Exception e) 
		 { 
			output = "{\"status\":\"error\", \"data\": \"Error while Updating the item.\"}";  
		
		System.err.println(e.getMessage());
		}
		return output;
	}


}
