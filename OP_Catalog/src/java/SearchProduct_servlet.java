/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import product.*;

/**
 *
 * @author Gregory
 */
@WebServlet(urlPatterns = {"/SearchProduct"})
public class SearchProduct_servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws product.Product_Queries.TableException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Product_Queries.TableException {
        response.setContentType("text/html;charset=UTF-8");
        SearchProduct search = new SearchProduct();
        search.setProductID("prodNumber");
        search.getDataFromTable();
        int max_index = search.getNumRecords();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchProduct</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<table border=1 cellspacing=0 cellpadding=3>");
	    out.println("<tr>");
	    out.println("<th>Product ID</th>");
	    out.println("<th>Description</th>");
	    out.println("<th width=75>Price</th>");
	    out.println("<th width=75>Quantity</th>");
	    out.println("</tr>");
            for(int index = 0; index < max_index; index++)
            {
                out.println("<tr>");
                out.println("<th>" + search.getProdId(index) + "</th>");
                out.println("<th>" + search.getDescr(index) + "</th>");
                out.println("<th width=75>" + search.getPrice(index) + "</th>");
                out.println("<th width=75>" + search.getQty(index) + "</th>");
                out.println("</tr>");
            }
            out.println("<h1>Servlet SearchProduct at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        try {
            processRequest(request, response);
        } catch (Product_Queries.TableException ex) {
            Logger.getLogger(SearchProduct_servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Product_Queries.TableException ex) {
            Logger.getLogger(SearchProduct_servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
