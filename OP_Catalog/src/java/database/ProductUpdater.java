/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Amy
 */
public class ProductUpdater extends HttpServlet {

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
		try (PrintWriter out = response.getWriter()) {
			
			//variable declarations
			int linesuccesses = 0;
			int linefailures = 0;
			int sqlerrors = 0;
			int dataerrors = 0;
			String line;
			
			//write a function which dumps an array of strings to SQL and returns a boolean
			//true if no errors, false if errors
			
			BufferedReader reader = new BufferedReader(new FileReader("backup.csv"));
			
			//parse the first line and save it to an array
			String temp = reader.readLine();
			if (temp.contains(";") || temp.contains("where") || temp.contains("drop") || temp.contains("select"))
			{
				dataerrors++;
				String columns[] = {"PROD_ID","CATEGORY_ID","STOCK_QTY","PROD_PRICE","PROD_WEIGHT","TAXABLE","PROD_NAME","LONG_DESC"};
			} //end if to check for SQL injection
			else
			{
				String columns[] = temp.split(",");
			} //end else when no SQL injection found
		
			//now parse the rest and save them
			while ((line = reader.readLine()) != null)
			{
				if (line.contains(";") || line.contains("where") || line.contains("drop") || line.contains("select"))
				{
					dataerrors++;
					linefailures++;
				} //end if to check for SQL injection
				else
				{
					String items[] = line.split(",");
					//pass this to SQL
					//save the returned boolean somewhere
					//increment error values or linesuccess as appropriate
				} //end else when no SQL injection found

			} //end while loop to parse the file
			
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet ProductUpdater</title>");			
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet ProductUpdater at " + request.getContextPath() + "</h1>");
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
