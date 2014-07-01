/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import database.Connection;
import product.Product;


/**
 *
 * @author Bella Belova
 */
public class Product_Queries {
        public static final String PRODUCT_TABLE_NAME = "PRODUCT";
        Product product[];         // array Product Object
        int index;
        int max_index;
        Connection conn;
        private static java.sql.Connection sqlConn = null;
        public static class TableException extends Exception{
        TableException(String s){
            super(s);
        }
    }
    
        
    public Product_Queries()
    {
        // Initialize connection
        conn = new Connection();
        conn.initialize_Connection_SQL();
        sqlConn = conn.getSQLConn();
        
        for(index = 0; index < 100; index++)
        {
            product[index] = new Product();            //Initialization Product Object array
        }
    }

        // return Product array for Product ID search
     public Product[] searchbyProductID(int Product_ID)
            throws TableException{
        int id; String fn; String ln;
        java.sql.Statement stmt;
        Object p = null;
        java.util.ArrayList results = null;
        java.sql.ResultSet rs = null;
        
        try{
          String createString = "select * from " + PRODUCT_TABLE_NAME + " where PROD_ID = " + Product_ID + ";" ;                
          stmt = sqlConn.createStatement();
          rs = stmt.executeQuery(createString);  
          index = 0;
          while (rs.next() == true)
          {
                product[index++] = new Product(rs.getInt("PROD_ID"), rs.getInt("CATEGORY_ID"), rs.getString("PROD_NAME"), 
                        rs.getInt("STOCK_QTY"),  rs.getString("LONG_DESC"), rs.getFloat("PROD_WEIGHT"), rs.getFloat("PROD_PRICE"), rs.getBoolean("TAXABLE"));
          }  
          max_index = index;        //maximum size of array
        }catch (java.sql.SQLException e){
            throw new TableException("Unable to search Product ID in Product Table." + "\nDetaill: " + e);
        }
        return product;
 }  
     
// Return maximum number of rows from database
    
     public int getMaxIndex()
    {
        return max_index;
    }
     
     // return Product array for Product description search
     public Product[] searchByProdDesc(String ProdDesc)
            throws TableException{
        int id; String fn; String ln;
        java.sql.Statement stmt;
        Object p = null;
        java.sql.ResultSet rs = null;
        
        try{
          String createString = "select * from " + PRODUCT_TABLE_NAME + " where PROD_NAME LIKE '% " + ProdDesc  + " %';" ;                
          stmt = sqlConn.createStatement();
          rs = stmt.executeQuery(createString);
          index = 0;
          while (rs.next() == true)
          {
                product[index++] = new Product(rs.getInt("PROD_ID"), rs.getInt("CATEGORY_ID"), rs.getString("PROD_NAME"), 
                        rs.getInt("STOCK_QTY"),  rs.getString("LONG_DESC"), rs.getFloat("PROD_WEIGHT"), rs.getFloat("PROD_PRICE"), rs.getBoolean("TAXABLE"));
          }
          max_index = index;        //maximum size of array
        }catch (java.sql.SQLException e){
            throw new TableException("Unable to search Product Table." + "\nDetaill: " + e);
        }
        return product;        
    }
    
    

    
}
