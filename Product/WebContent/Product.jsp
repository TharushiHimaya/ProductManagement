<%@page import= "com.Product" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="View/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Product.js"></script>
</head>
<body>
	
	<div class="container"><div class="row"><div class="col-6">
	
	<h1>Product Management </h1>
	
		<form id="formItem" name="formItem" method="post" action="Product.jsp">
		
			 Product Code: 
			<input id="ItemCode" name="ItemCode" type="text" 
			 class="form-control form-control-sm">
			 
			<br> 
			Product Name: 
			<input id="ItemName" name="ItemName" type="text" 
			 class="form-control form-control-sm">
			 
			<br>
			 Product Description: 
			<input id="Description" name="Description" type="text" 
			 class="form-control form-control-sm">
			 
			<br>
			 Product Researcher: 
			<input id="Researcher" name="Researcher" type="text" 
			 class="form-control form-control-sm">
			 
			 <br>
			 Product Email: 
			 <input id="Email" name="Email" type="text" 
			 class="form-control form-control-sm">
			 
			 <br>
			 
			 <input id="btnSave" name="btnSave" type="button" value="Save"  class="btn btn-primary">
			 
			<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
		</form>
	
	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	
	
	<br>
	<div id="divItemsGrid">
	
	    <%
			Product productObj = new Product(); 
			out.print(productObj.readItems());
		%>
	</div>
	
	</div></div></div>

</body>
</html>