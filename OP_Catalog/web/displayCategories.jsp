<%-- 
    Document   : displayCategories
    Created on : Jul 7, 2014, 12:15:51 PM
    Author     : Scott Young
    Description: Display category information using JavaBean Category List class
    Team       : 3's Company (Amy Roberts, Bella Belova, Scott Young)
    AIC        : "We pledge that we have complied with the AIC in this work" -AR/BB/SY
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="product.Category"%>
<%@page import="product.CategoryList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="olympic.css"/>
        <script type="text/javascript" src="utils.js"> </script>        
        <title>Search Olympic Pride Store by Product Category</title>
    </head>
    <body>
            <script type="text/javascript">
                header();
            </script>        
            <h1><div style="text-align:center">Please select from the following product categories:</div></h1>
            <div style="text-align:center">
        <%
            // set the "catlist" object as a CategoryList variable:
            CategoryList c1 = (CategoryList) request.getAttribute("catlist");
            // set the c1 object variable as an array list object:
            ArrayList<Category> catList = c1.getCatList();
            // loop through the list and construct the html links via anchor tags:
            for (int x = 0; x < catList.size(); x++) {
                out.println("<a href=\"QueryByCatID?id=");
                out.println(catList.get(x).getCategoryID());
                out.println("&name=");
                out.println(catList.get(x).getCategoryName());
                out.println("\">");
                out.println(catList.get(x).getCategoryName());
                out.println("</a>");
                out.println("<br><br>");                
            }
	%>
            </div>

            <script type="text/javascript">
		footer();
            </script>            
    </body>
</html>
