<%-- 
    Document   : DisplayProducts
    Description: JSP to display the user search results
    Created on : July 7, 2014
    Team       : 3's Company (Amy Roberts, Bella Belova, Scott Young)
    AIC        : "We pledge that we have complied with the AIC in this work" -AR/BB/SY
    Author     : Scott Young, Bella Belova
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
            // @author: Scott Young
                
            // set the "prodlist" object as a ProductList variable:
            ProductList p1 = (ProductList) request.getAttribute("prodlist");
            // set the p1 object variable as an array list object:
            ArrayList<Product> prodList = p1.getProdList();
            
            // @author: Bella Belova
            
            // check prodlist & display error msg if array list is null/empty:
            if(prodList.size()== 0) {
                out.println("<br><h1><div style=\"text-align:center\">OOPS!!</div></h1>");
                out.println("<br><center><b>We're sorry but the category selected or product that you have ");
                out.println("searched for is not currently available.</b></center>");
                out.println("<br>");
                out.println("<center><b>Please perform another search or select from the links above.<b></center>");
                out.println("<br><br><br>");
            } else {   
            %>    
            
            <!-- @author: Scott Young    -->
            
            <!-- Search results header:  -->
            <h1><div style="text-align:center">Olympic Pride Product Search Results:</div></h1>
            
            <!-- Create the table & columns: -->
            <table border="1" align="center">
                <tr><th>Product ID</th>
             <!--<th>Category ID</th><--><th>Product Name</th><!--<th>Stock Qty</th><-->
                <th>Product Description</th><th>Product Price</th>
              <!--  <th>Product Weight</th><th>Taxable</th> -->
              <!-- <th>Purchase Quantity</th> -->
              <th>Add To Cart</th>
                </tr>      

        <%
            // @author: Scott Young (assisted by Amy Roberts)
                
            // loop through the list and construct the html links via anchor tags:    
            for (int x = 0; x < prodList.size(); x++) {
                // insert the table row data:
                out.println("<tr><td>");                
                out.println(prodList.get(x).getProdID() + "</td><td>");
             //   out.println(prodList.get(x).getCategoryID() + "</td><td>");
                out.println(prodList.get(x).getProdName() + "</td><td>");
              //  out.println(prodList.get(x).getStockQty() + "</td><td>");
                out.println(prodList.get(x).getLongDesc() + "</td>");
                out.println("<td>$" + prodList.get(x).getProdPrice() + "</td>");
              //  out.println("<td>" + prodList.get(x).getProdWeight() + " oz.</td><td>");
               // out.println(prodList.get(x).getTaxable() + "</td>");
              //  out.println("<td><form method=&quot;post&quot; action=&quot;Add2Cart&quot;>");
              //  out.println("<td><input type=&quot;number&quot; name=&quot;test&quot;>");
              //  out.println("<size=&quot;10&quot; maxlength=&quot;12&quot; value=&quot;0&quot;/></form></td>");
              //  out.println("<td><form method=&quot;post&quot; action=&quot;Add2Cart&quot;>");
                out.println("<td>Qty: <input type=&quot;number&quot; name=&quot;Qty&quot;>");
                out.println("<size=&quot;10&quot; maxlength=&quot;12&quot; value=&quot;0&quot;>");
                out.println("<button type=&quot;submit&quot;>Add To Cart</button></form></td>");
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
