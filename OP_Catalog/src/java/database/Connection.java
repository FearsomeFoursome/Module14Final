/*
 * 3's Company (Amy Roberts, Bella Belova, Scott Young)
 * "We pledge that we have complied with the AIC in this work."
 *
 * Class to handle establishing database connections
 */
package database;

/**
 * This class includes methods to establish SQL and MySQL database connections.
 * @author Bella Belova; modified by Scott Young
 */
public class Connection {

	private static java.sql.Connection sqlConn = null;
	private static final String SQLjdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
	private static final String SQLconnectionUrl = "jdbc:sqlserver://localhost";
	private static final String SQLusername = "sa";
	private static final String SQLpassword = "password";
	private static java.sql.ResultSet rs = null;
	private static java.sql.Statement stmt = null;

	/**
	 * Retrieves the SQL connection.
	 * @return A pointer to the currently open SQL connection.
	 */
	public  static  java.sql.Connection getSQLConn()
	{
		return sqlConn;
	}  
	
	/**
	 * Initializes the SQL connection.
	 */
	public static void initialize_Connection_SQL() 
	{      

		try{
			sqlConn = java.sql.DriverManager.getConnection(SQLconnectionUrl,SQLusername, SQLpassword);
		} catch (java.sql.SQLException e){System.err.println(e); }
	}
    
        
	  /**
		* Method to close the SQL connection.
		*/
	  public static void closeSQLConn() {
		//three try-catch blocks to ensure clean error handling
		try
		{
			if (stmt != null)
				stmt.close();
		} //end try
		catch (java.sql.SQLException e)
		{
			System.err.println(e);
		} //end catch	
		try
		{
			if (rs!= null)
				rs.close();
		} //end try
		catch (java.sql.SQLException e)
		{
			System.err.println(e);
		} //end catch
		try
		{
			if(sqlConn != null)
				sqlConn.close();
		} //end try
		catch (java.sql.SQLException e)
		{
			System.err.println(e);
		} //end catch	
	} //end SQLdisconnect
}

