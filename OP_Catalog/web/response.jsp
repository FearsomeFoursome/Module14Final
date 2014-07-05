<%-- 
    Document   : response
    Description: JSP to display the user search or selection results
    Created on : Jun 30, 2014, 10:17:37 PM
    Team       : 3's Company (Amy Roberts, Bella Belova, Scott Young)
    AIC        : "We pledge that we have complied with the AIC in this work" -AR/BB/SY
    Author     : Scott Young
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%-- Place any instance variables here --%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%-- Title of the JSP Page --%>
        <title>Olympic Pride Web Store</title>
    </head>
    <body>
        
        <%-- Header of the results page --%>
        <h1>Olympic Pride Product Search Results:</h1>
        
        <%-- Product Search Results as a Table  --%>
        <table border=\"1\" align=\"center\"><br>
            <tr><td>Product ID</td><td>Category ID</td><td>Product Name</td><td>Stock Qty</td>
                <td>Product Description</td>
                <td>Product Price</td><td>Product Weight</td>
                <td>Taxable</td>
            </tr>
            <tr><td>  </td></tr>
            <tr><td>  </td></tr>
        </table>
        
    </body>
</html>
