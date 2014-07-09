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
                        
            <%
            // set the "catlist" object as a CategoryList variable:
            ProductList p1 = (ProductList) request.getAttribute("prodlist");
            // set the c1 object variable as an array list object:
            ArrayList<Product> prodList = p1.getProdList();
            if(prodList.size()== 0) {
                out.println("<br><h1><div style=\"text-align:center\">OOPS!!</div></h1>");
                out.println("<br><center><b>We're sorry but the category selected or product that you have ");
                out.println("searched for is not currently available.</b></center>");
                out.println("<br>");
                out.println("<center><b>Please perform another search or select from the links above.<b></center>");
                out.println("<br><br><br>");
            // loop through the list and construct the html links via anchor tags:
            } else {   
            %>    
            
            <!-- Search results header:  -->
            <h1><div style="text-align:center">Olympic Pride Product Search Results:</div></h1>
            
            <!-- Create the table & columns: -->
            <table border="1" align="center">
                <tr><th>Add Item To Cart</th><th>Product ID</th>
                <th>Category ID</th><th>Product Name</th><th>Stock Qty</th>
                <th>Product Description</th><th>Product Price</th>
                <th>Product Weight</th><th>Taxable</th>
                </tr>      

        <%
            // loop through the prodList object:
            for (int x = 0; x < prodList.size(); x++) {
                // insert the table row data:
                out.println("<tr><td>");
		out.println("<form method=&quot;post&quot; action=&quot;Add2Cart&quot;>");
                out.println("<button type=&quot;submit&quot;>Add To Cart</button></form></td><td>");                
                out.println(prodList.get(x).getProdID() + "</td><td>");
                out.println(prodList.get(x).getCategoryID() + "</td><td>");
                out.println(prodList.get(x).getProdName() + "</td><td>");
                out.println(prodList.get(x).getStockQty() + "</td><td>");
                out.println(prodList.get(x).getLongDesc() + "</td>");
                out.println("<td>$" + prodList.get(x).getProdPrice() + "</td>");
                out.println("<td>" + prodList.get(x).getProdWeight() + " oz.</td><td>");
                out.println(prodList.get(x).getTaxable() + "</td>");
                out.println("</tr>");
            }
            }
	%>            
        
        </table>
        <br><br>
            <script type="text/javascript">
		footer();
            </script>        
    </body>
</html>
