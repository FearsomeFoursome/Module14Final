/*
 * Servlet to query the Product database by CategoryID and create an aray list of Product objects.
 * 3's Company (Amy Roberts, Bella Belova, Scott Young)
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
 * @author Scott Young
 */
public class QueryByCatID extends HttpServlet {
        static final String PRODUCT_TABLE_NAME = "PRODUCT";
        private java.sql.Connection sqlConn;
    
    /**
     * Query to get products from the Product database by CATEGORY_ID.
     * @param catID A CATEGORY_ID integer value for a product.
     * @return An array list of product objects.
     */    
    public ProductList getProductsbyCatID(String catID) {
        Connection.initialize_Connection_SQL();
        sqlConn = Connection.getSQLConn();
        java.sql.Statement stmt;
        ProductList results = null;
        java.util.ArrayList prodObjects;
        java.sql.ResultSet rs;
	prodObjects = new java.util.ArrayList(); 
        
        try{
          String createString = "select * from " + PRODUCT_TABLE_NAME +
                  " where CATEGORY_ID like " + catID + ";";                
          stmt = sqlConn.createStatement();
          rs = stmt.executeQuery(createString);                   
          results = new product.ProductList();
          while (rs.next() == true)
            prodObjects.add(new product.Product(rs. getInt("PROD_ID"), rs.getInt("CATEGORY_ID"), 
                    rs.getString("PROD_NAME"), rs.getInt("STOCK_QTY"), rs.getString("LONG_DESC"),
                    rs.getFloat("PROD_WEIGHT"), rs.getFloat("PROD_PRICE"), rs.getBoolean("TAXABLE")));   
          Connection.closeSQLConn();
          stmt.close();         
        } catch (java.sql.SQLException e) {
		System.err.println("Unable to create requested Product object." + "\nDetail: " + e);
		}
	results.setProdList(prodObjects);
        return results;
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
        String url = "/displayProducts.jsp";
        
        try {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet QueryByCatID</title>");            
            out.println("</head>");
            out.println("<body>");
                      
            // Create the product list object:
            ProductList p1 = new ProductList();
            
            // Get the product category selection from the user:
            String prodCat = request.getParameter("id");
            
            // Search the Product DB by Category ID selection of the user:
            p1 = getProductsbyCatID(prodCat);
            
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
