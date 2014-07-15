/*
 * Servlet to query the Product database by Product_ID or Product Description and create an aray list of Product objects.
 * 3's Company (Amy Roberts, Bella Belova, Scott Young)
 * Author: Bella Belova, Scott Young
 * "We pledge that we have complied with the AIC in this work."
 * AR / BB / SY
 */

package product;

import database.Connection;
import java.io.IOException;
import java.io.PrintWriter;
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
        
    private java.sql.Connection sqlConn;
        boolean letter = true;
        int max_index, index;
        int prod_id_num;
        String Prod_ID;
    /**
     * Query to get products from the Product database by PROD_ID.
     * @param prodID A PROD_ID string value for a product.
     * @return An array list of product objects.
     * @author Bella Belova (assistance from Scott Young)
     */    
    public ProductList searchByProductID(String prodID){
        ProductList results = null;
        java.sql.Statement stmt;
        java.sql.ResultSet rs;
        Connection.initialize_Connection_SQL();
        sqlConn = Connection.getSQLConn();
        
        try{
          String createString = "select * from " + Connection.PRODUCT_TABLE_NAME + " where PROD_ID LIKE '%" + prodID + "%';" ;                
          stmt = sqlConn.createStatement();
          rs = stmt.executeQuery(createString);
          results = new product.ProductList();
          while (rs.next() == true)
            results.addProductList(new product.Product(rs. getInt("PROD_ID"), rs.getInt("CATEGORY_ID"), 
                    rs.getString("PROD_NAME"), rs.getInt("STOCK_QTY"), rs.getString("LONG_DESC"),
                    rs.getFloat("PROD_WEIGHT"), rs.getFloat("PROD_PRICE"), rs.getBoolean("TAXABLE")));   
          Connection.closeSQLConn();
          stmt.close();
        }catch (java.sql.SQLException e){
            System.err.println("Unable to create requested Product object." + "\nDetail: " + e);
        }
        return results;
 } 
    
    /**
     * Query to get products from the Product database by LONG_DESC.
     * @param ProdDesc
     * @return An array list of product objects.
     * @author Bella Belova (assistance from Scott Young)
     */
     public ProductList searchByProductDesc(String ProdDesc){
        ProductList results = null;
        java.sql.Statement stmt;
        java.sql.ResultSet rs = null;
        Connection.initialize_Connection_SQL();
        sqlConn = Connection.getSQLConn();
        
        try{
          String createString = "select * from " + Connection.PRODUCT_TABLE_NAME + " where PROD_NAME LIKE '% " + ProdDesc  + " %' or LONG_DESC LIKE '%" + ProdDesc + " %';" ;                
          stmt = sqlConn.createStatement();
          rs = stmt.executeQuery(createString);
          results = new product.ProductList();
          while (rs.next() == true)
            results.addProductList(new product.Product(rs. getInt("PROD_ID"), rs.getInt("CATEGORY_ID"), 
                    rs.getString("PROD_NAME"), rs.getInt("STOCK_QTY"), rs.getString("LONG_DESC"),
                    rs.getFloat("PROD_WEIGHT"), rs.getFloat("PROD_PRICE"), rs.getBoolean("TAXABLE")));   
          Connection.closeSQLConn();
          stmt.close();
                 }catch (java.sql.SQLException e){
            System.err.println("Unable to create requested Product object." + "\nDetail: " + e);
        }
        return results;        
    }
     
    /**
     * Method to check if search box input is a letter or number.
     * @param prod_desc A variable for the input from the user.
     * @return An array list of Product objects.
     * @author Bella Belova
     */ 
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
                return searchByProductDesc(prod_desc);
            }
            else
            {
                return searchByProductID(String.valueOf(prod_id_num));
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
     * @author Bella Belova (assistance from Scott Young)
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = "/displayProducts.jsp";
        
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
            
            // declare the product list variable:
            ProductList p1;
           
            /*
            Check if the Prod_ID string contains a number, if so run searchByProductID,
            If it does not contain a number, run the searchByProductDesc method
            referenced http://stackoverflow.com/questions/6344867/checking-if-a-string-contains-a-number-or-not
            for .matches logic & syntax
            */
            if(Prod_ID.matches(".*\\d.*")) {
            p1 = searchByProductID(Prod_ID);
            } else {
            // Search the Product DB by Category ID selection of the user:
            p1 = searchByProductDesc(Prod_ID);
            }
 
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
