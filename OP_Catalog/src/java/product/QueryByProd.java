/*
 * Servlet to query the Product database by Product_ID or Product Description and create an aray list of Product objects.
 * 3's Company (Amy Roberts, Bella Belova, Scott Young)
 * "We pledge that we have complied with the AIC in this work."
 * AR / BB / SY
 */

package product;

import database.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Bella Belova
 */
public class QueryByProd extends HttpServlet {

        static final String PRODUCT_TABLE_NAME = "PRODUCT";
        private java.sql.Connection sqlConn;
        boolean letter = true;
        int max_index, index;
        int prod_id_num;
        String Prod_ID;
    /**
     * Query to get products from the Product database by CATEGORY_ID.
     * @param prodID A PROD_ID integer value for a product.
     * @return An array list of product objects.
     */    
    public ProductList searchbyProductID(int Product_ID){
        java.util.ArrayList prodObjects;
        ProductList results = null;
        java.sql.Statement stmt;
        java.sql.ResultSet rs = null;
        prodObjects = new java.util.ArrayList(); 
        Connection.initialize_Connection_SQL();
        sqlConn = Connection.getSQLConn();
        
        try{
          String createString = "select * from " + PRODUCT_TABLE_NAME + " where PROD_ID = " + Product_ID + ";" ;                
          stmt = sqlConn.createStatement();
          rs = stmt.executeQuery(createString);
          results = new product.ProductList();
          while (rs.next() == true)
            prodObjects.add(new product.Product(rs. getInt("PROD_ID"), rs.getInt("CATEGORY_ID"), 
                    rs.getString("PROD_NAME"), rs.getInt("STOCK_QTY"), rs.getString("LONG_DESC"),
                    rs.getFloat("PROD_WEIGHT"), rs.getFloat("PROD_PRICE"), rs.getBoolean("TAXABLE")));   
          Connection.closeSQLConn();
          stmt.close();
        }catch (java.sql.SQLException e){
            System.err.println("Unable to create requested Product object." + "\nDetail: " + e);
        }
        results.setProdList(prodObjects);
        return results;
 }  
     public ProductList searchByProdDesc(String ProdDesc){
        java.util.ArrayList prodObjects;
        ProductList results = null;
        java.sql.Statement stmt;
        java.sql.ResultSet rs = null;
        prodObjects = new java.util.ArrayList(); 
        Connection.initialize_Connection_SQL();
        sqlConn = Connection.getSQLConn();
        
        try{
          String createString = "select * from " + PRODUCT_TABLE_NAME + " where PROD_NAME LIKE '% " + ProdDesc  + " %' or LONG_DESC LIKE '%" + ProdDesc + " %';" ;                
          stmt = sqlConn.createStatement();
          rs = stmt.executeQuery(createString);
          results = new product.ProductList();
          while (rs.next() == true)
            prodObjects.add(new product.Product(rs. getInt("PROD_ID"), rs.getInt("CATEGORY_ID"), 
                    rs.getString("PROD_NAME"), rs.getInt("STOCK_QTY"), rs.getString("LONG_DESC"),
                    rs.getFloat("PROD_WEIGHT"), rs.getFloat("PROD_PRICE"), rs.getBoolean("TAXABLE")));   
          Connection.closeSQLConn();
          stmt.close();
                 }catch (java.sql.SQLException e){
            System.err.println("Unable to create requested Product object." + "\nDetail: " + e);
        }
        results.setProdList(prodObjects);
        return results;        
    }
    public ProductList getProductList(String prod_desc)
    {
        if(prod_desc.isEmpty())
        {
            letter = true;
            return null;
        }
        else
        {
            max_index = prod_desc.length();       // Check number of symbols
            prod_id_num = 0;
            if((prod_desc.charAt(0) < '0')||(prod_desc.charAt(0) > '9'))
            {
                    // First symbol is not a digit
                    letter = true;
            }
            else
            {
                    letter = false;
            }

            for(index = 0; index < max_index; index++)
            {
                // convert input to Integer
                prod_id_num += prod_id_num*10 + prod_desc.charAt(index) - '0';
            }
            
            if(letter == true)
            {
                return searchByProdDesc(prod_desc);
            }
            else
            {
                return searchbyProductID(prod_id_num);
            }
        }    
    }
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = "/DisplayProductsbyCatID.jsp";
        
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet QueryByCatID</title>");            
            out.println("</head>");
            out.println("<body>");
            
            // UPDATE PARAMETER VALUE to match the updated HTML href value****
            String Prod_ID = request.getParameter("prodNumber");
            out.println("<h1>Product Search Results from the " + Prod_ID + "Product: </h1>");
            
            // Create the product list object:
            ProductList p1 = new ProductList();
            
           
            // Search the Product DB by Category ID selection of the user:
            p1 = getProductList(Prod_ID);
            out.println("<table border=1 cellspacing=0 cellpadding=3>");
            out.println("<tr>");
	    out.println("<th>Product ID</th>");
	    out.println("<th>Description</th>");
	    out.println("<th width=75>Price</th>");
	    out.println("<th width=75>Quantity</th>");
	    out.println("</tr>");
            if(p1!=null)
            {
                max_index = p1.getProdList().size();
                for(index = 0; index <  max_index; index++)
                {
                    out.println("<tr>");
                    out.println("<th>" + p1.getProdList().get(index).getProdID() + "</th>");
                    out.println("<th>" + p1.getProdList().get(index).getLongDesc() + "</th>");
                    out.println("<th width=75>" + p1.getProdList().get(index).getProdPrice() + "</th>");
                    out.println("<th width=75>" + p1.getProdList().get(index).getStockQty() + "</th>");
                    out.println("</tr>");
                }                
            }
            out.println("</table>");
            // set the attributes for category list object
            request.setAttribute("prodlist", p1);
            
            // forward request and response to JSP page for display to user
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);    
            
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }
    
   
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
                processRequest(request, response);
            
            }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
                    processRequest(request, response);
            }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
