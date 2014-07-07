<%-- 
    Document   : displayCategories
    Created on : Jul 7, 2014, 12:15:51 PM
    Author     : Scott Young
    Description: Display category information using JavaBean Category List class
    Team       : 3's Company (Amy Roberts, Bella Belova, Scott Young)
    AIC        : "We pledge that we have complied with the AIC in this work" -AR/BB/SY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="product.Category"%>
<%@page import="product.CategoryList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Olympic Pride Store by Product Category</title>
    </head>
    <body>
        <h1>Please select from the following product categories:</h1>
        
        <%
            String[] catList = request.getParameterValues("catlist");
           // Category catObj = (Category)request.getParameter(catList);
            for (int i = 0; i < catList.length; i++) {
            out.println("<a href=\"products?id=");
            out.println(catList.catID);
            out.println("&name=");
            out.println(catList.catName);
            out.println("\">");
            out.println(catName);
            out.println("</a>");
            out.println("<br>");
		}
	%>
        
    </body>
</html>
