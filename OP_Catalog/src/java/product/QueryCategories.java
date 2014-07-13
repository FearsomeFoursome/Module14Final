/*
 * Servlet to query the Category database and create a Category object.
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
public class QueryCategories extends HttpServlet {

        private java.sql.Connection sqlConn;   
    
    /**
     * Query to get categories from the database.
     * @return A category list (array list) of category objects.
     */
    public CategoryList getCategories() {
        Connection.initialize_Connection_SQL();
        sqlConn = Connection.getSQLConn();
        java.sql.Statement stmt;
        CategoryList results = null;
        java.sql.ResultSet rs; 
        
        try{
          String createString = "select * from " + Connection.CATEGORY_TABLE_NAME + ";";                
          stmt = sqlConn.createStatement();
          rs = stmt.executeQuery(createString);                   
          results = new product.CategoryList();
          while (rs.next() == true)
            results.addCategoryList(new product.Category(rs.getInt("CATEGORY_ID"), rs.getString("CAT_NAME")));   
          Connection.closeSQLConn();
          stmt.close();        
        } catch (java.sql.SQLException e) {
		System.err.println("Unable to create requested Category object." + "\nDetail: " + e);
		}
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
        String url = "/displayCategories.jsp";
                
        try {                                   
            // query the db for the category names & ID's, return category list object
            CategoryList c1 = getCategories();
            
            // set the attributes for category list object
            request.setAttribute("catlist", c1);
            
            // forward request and response to JSP page for display to user
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);              

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
