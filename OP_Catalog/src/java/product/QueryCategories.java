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
        static final String CATEGORY_TABLE_NAME = "CATEGORY";
        private java.sql.Connection sqlConn;   
    
    /**
     * Query to get categories from the database.
     * @return A category list (array list) of category objects.
     */
    public CategoryList getCategories() {
        java.sql.Statement stmt;
        CategoryList results = null;
        java.util.ArrayList catObjects;
        java.sql.ResultSet rs;
		  catObjects = new java.util.ArrayList(); 
        
        try{
          String createString = "select * from " + CATEGORY_TABLE_NAME + ";";                
          stmt = sqlConn.createStatement();
          rs = stmt.executeQuery(createString);                   
          results = new product.CategoryList();
          while (rs.next() == true)
            catObjects.add(new product.Category(rs.getInt("CATEGORY_ID"), rs.getString("CAT_NAME")));   
        } catch (java.sql.SQLException e) {
		System.out.println("Unable to create requested Category object." + "\nDetail: " + e);
		}
	results.setCatList(catObjects);
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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet QueryCategories</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println("<h1>Please choose a product category: </h1>");
            
            // initialize db connection
            Connection.initialize_Connection_SQL();            
            
            // create the category list object
            CategoryList c1 = new CategoryList();
            
            // get the sql connection
            Connection.getSQLConn();
            
            // query the db for the category names & ID's, return category list object
            getCategories();
            
            // close the sql connection
            Connection.closeSQLConn();
            
            // retrieve the category list
            c1.getCatList();
            
            // set the attributes for category list object
            request.setAttribute("catlist", c1);
            
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
