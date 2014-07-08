<%-- 
    Document   : DisplayProducts
    Description: JSP to display the user search results
    Created on : July 7, 2014
    Team       : 3's Company (Amy Roberts, Bella Belova, Scott Young)
    AIC        : "We pledge that we have complied with the AIC in this work" -AR/BB/SY
    Author     : Scott Young
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="product.Product"%>
<%@page import="product.ProductList"%>
<!DOCTYPE html>
<%-- Place any instance variables here --%>


<html>
	<head>
            <title>Olympic Pride</title>
            <link rel="stylesheet" type="text/css" href="olympic.css"/>
            <script type="text/javascript" src="utils.js"> </script>
	</head>
	<body>
		<script type="text/javascript">
			header();
		</script>
        <h1>Olympic Pride Product Search Results:</h1>
        
        <%-- Product Search Results as a Table  --%>
        <table border=\"1\" align=\"center\"><br>
            <tr><td>Add Item To Cart</td><td>Product ID</td><td>Category ID</td><td>Product Name</td>
                <td>Stock Qty</td><td>Product Description</td>
                <td>Product Price</td><td>Product Weight</td><td>Taxable</td>
            </tr>
        <%
            // set the "catlist" object as a CategoryList variable:
            ProductList p1 = (ProductList) request.getAttribute("prodlist");
            // set the c1 object variable as an array list object:
            ArrayList<Product> prodList = p1.getProdList();
            // loop through the list and construct the html links via anchor tags:
            for (int x = 0; x < prodList.size(); x++) {
                out.println("<tr><td>");
		out.println("<form method=&quot;post&quot; action=&quot;Add2Cart&quot;>");
                out.println("<button type=&quot;submit&quot;>Add To Cart</button></form></td><td>");                
                out.println(prodList.get(x).getProdID() + "</td><td>");
                out.println(prodList.get(x).getCategoryID() + "</td><td>");
                out.println(prodList.get(x).getProdName() + "</td><td>");
                out.println(prodList.get(x).getStockQty() + "</td><td>");
                out.println(prodList.get(x).getLongDesc() + "</td><td>");
                out.println(prodList.get(x).getProdPrice() + "</td><td>");
                out.println(prodList.get(x).getProdWeight() + "</td><td>");
                out.println(prodList.get(x).getTaxable() + "</td>");
                out.println("</tr>");                
            }
	%>            

        </table>
            <script type="text/javascript">
		footer();
            </script>        
    </body>
</html>
