/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Statement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Amy
 */
public class StockUpdater extends HttpServlet {

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
			int badquantities = 0;
			String line;
			
			//initialize column header array to defaults
			String columns[] = {"PROD_ID","CATEGORY_ID","STOCK_QTY","PROD_PRICE","PROD_WEIGHT","TAXABLE","PROD_NAME","LONG_DESC"};
			
			ServletContext context = getServletContext();
			InputStream input = context.getResourceAsStream("/WEB-INF/update.csv");
			InputStreamReader ireader = new InputStreamReader(input);
			BufferedReader reader = new BufferedReader(ireader);
						
			//parse the first line and save it to an array
			String temp = reader.readLine();
			if (temp.contains(";") || temp.contains("where") || temp.contains("drop") || temp.contains("select"))
			{
				dataerrors++;
			} //end if to check for SQL injection; default column headers used
			else
			{
				columns = temp.split(",", -1);
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
					//replace single quotes with escaped single quotes so SQL doesn't blow up
					line = line.replace("'", "''");

					String items[] = line.split(",", -1);
					
					//pass this to SQL and save the returned boolean
					int sqlfailed = savetodatabase(columns, items);
					
					if (sqlfailed == 1)
					{
						sqlerrors++;
						linefailures++;
					}
					else
					{
						if(sqlfailed == 2)
						{
							badquantities++;
						}
						else
						{
							linesuccesses++;
						}					
					}				

				} //end else when no SQL injection found

			} //end while loop to parse the file
			
			out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\n" +
							"\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
			out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">");
			out.println("<head>");
			out.println("<title>Olympic Pride</title>");
			out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"olympic.css\"/>");
			out.println("<script type=\"text/javascript\" src=\"utils.js\"> </script>");
			out.println("</head>");
			out.println("<body>");
			out.println("<script type=\"text/javascript\">");
			out.println("header();");
			out.println("</script>");
			out.println("<h1>Database Update Complete</h1>");
			out.println(linesuccesses + " lines successfully written.<br>");
			out.println(linefailures + " lines failed to write.<br>");
			out.println(sqlerrors + " lines caused SQL failures.<br>");
			out.println(dataerrors + " lines contained invalid data and were not written.<br>");
			out.println(badquantities + " lines contained data that would result in negative product quantities.<br><br><br>");
			out.println("<script type=\"text/javascript\">");
			out.println("footer();");
			out.println("</script>");
			out.println("</body>");
			out.println("</html>");
		}
	}

	//Saves the array of items into the columns named in the columns array.
	//Boolean returned is true if an error is encountered, or false if no errors.
	//Limitations: columns[] must contain exact matches to the columns of the Product table.
	//Columns[0] and items[0] must be Prod_ID and the product ID number.
	int savetodatabase(String columns[], String items[])
	{
		int errorfound = 0;
		String builtstatement;
		Statement stmt = null;
		java.sql.Connection conn = null;
		java.sql.ResultSet rs = null;
		try
		{
			//get a connection
			Connection.initialize_Connection_SQL();
			conn = Connection.getSQLConn();
			
			//check to see if a line already exists with the primary key
			builtstatement = "select * from Product where " + columns[0]
					  + " = " + items[0] + ";";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(builtstatement);
			
			//check to see if the resultset has data - while loop and boolean check
			//from Adam Hardy at http://www.coderanch.com/t/296373/JDBC/databases/check-Result-set-null
			boolean rshasdata = false;
			int currentstock = 0;
			while (rs.next())
			{
				currentstock = rs.getInt("STOCK_QTY");
				rshasdata = true;
			}
			
			if (rshasdata)
			{
				//control variables
				boolean stockfound = false;
				int stock = 1;
				boolean pricefound = false;
				int price = 2;
				int i = 0;
				int j = 0;
				
				//loop through column array to find where the stock quantity is
				while(stockfound == false)
				{
					if (columns[i].equals("STOCK_QTY"))
					{
						stock = i;
						stockfound = true;
					}
					i++;
				}
				
				//loop through the column array to find the price
				while(pricefound == false)
				{
					if (columns[j].equals("PROD_PRICE"))
					{
						price = j;
						pricefound = true;
					}
					j++;
				}
								
				int newstock = Integer.parseInt(items[stock]) + currentstock;
			
				if(newstock < 0)
				{
					errorfound = 2;
					System.out.println("Quantity less than 0!");
					stmt = null;
				}
				else
				{
					//build an update statement to update the product price and stock quantity
					builtstatement = "update Product set " + columns[stock] + " = '" 
							  + newstock + "', " + columns[price] + " = '" 
							  + items[price] + "' where " + columns[0] + " = " + items[0];
				}
								
			}
			else
			{
				//insert a new product row
				builtstatement = "insert into Product (" + columns[0] + ", " + columns[1]
						  + ", " + columns[2] + ", " + columns[3] + ", " + columns[4]
						  + ", " + columns[5] + ", " + columns[6] + ", " + columns[7]
						  + ") values('" + items[0] + "', '" + items[1] + "', '" + items[2]
						  + "', '" + items[3] + "', '" + items[4] + "', '" + items[5] + "', '"
						  + items[6] + "', '" + items[7] + "');";
			}		
						
			//send the SQL statement to SQL
			if(stmt != null)
			{
				stmt.executeUpdate(builtstatement);
			}
			
		} //end try
		catch (Exception e)
		{
			System.err.println(e);
			errorfound = 1;
		} //end catch
		//close connections and statement
		Connection.closeSQLConn();
		try
		{
			if(stmt != null)
			{
				stmt.close();
			}
		} //end try
		catch (Exception e)
		{
			System.err.println(e);
		} //end catch
		return errorfound;
	} //end savetodatabase method
	
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
