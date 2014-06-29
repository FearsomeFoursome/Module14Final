/*
 * Product Class
 * 3's Company (Amy Roberts, Bella Belova, Scott Young)
 * "We pledge that we have complied with the AIC in this work."
 * AR / BB / SY
 */

package classes;

/**
 *
 * @author Scott Young
 */
public class Product {
    private int prodID;
    private int categoryID;
    private String prodName;
    private int stockQty;
    private String longDesc;
    private float prodWeight;
    private float prodPrice;
    private char taxable;
    
    /************************************************************************** 
    CONSTRUCTORS
    **************************************************************************/
    
    /**
     * Constructor to set Product object with default values.
     * Object values can later be assigned values via set methods.
     */
    public Product() {
        prodID = 0;
        categoryID = 0;
        prodName = "";
        stockQty = 0;
        longDesc = "";
        prodWeight = 0.0f;
        prodPrice = 0.0f;
        taxable = '\u0000';
    }
    /**
     * A constructor to create a product object with designated values.
     * @param pID A unique integer to identify the product.
     * @param catID An integer to identify the product category for the product type.
     * @param pName The name of the product.
     * @param sQty The current stock quantity of the product.
     * @param lDesc The long description of the product (i.e. color, size, design, details).
     * @param pWeight The weight of the product.
     * @param pPrice The price of the product.
     * @param tax A single character to register the product as taxable (Y) or not taxable (N).
     */
    public Product(int pID, int catID, String pName, int sQty, String lDesc, float pWeight, float pPrice, char tax) {
        prodID = pID;
        categoryID = catID;
        prodName = pName;
        stockQty = sQty;
        longDesc = lDesc;
        prodWeight = pWeight;
        prodPrice = pPrice;
        taxable = tax;        
    }

    /**************************************************************************
    GET METHODS
    **************************************************************************/
   /**
    * Retrieves the product ID.
    * @return The unique identifier of the product.
    */ 
   public int getProductID(){ return prodID; }
   
   /**
    * Retrieves the category ID.
    * @return The integer to identify the product category for the product type.
    */
   public int getCategoryID() { return categoryID; }
   
   /**
    * Retrieves the product name.
    * @return The name of the product.
    */
   public String getProductName() { return prodName; }
   
   /**
    * Retrieves the stock quantity.
    * @return The current stock quantity of the product.
    */
   public int getStockQuantity() { return stockQty; }
   
   /**
    * Retrieves the long description of the product (i.e. size, design, color).
    * @return The long description of the product.
    */
   public String getLongDesc() { return longDesc; }
   
   /**
    * Retrieves the product weight.
    * @return The weight of the product.
    */
   public float getProductWeight() { return prodWeight; }
   
   /**
    * Retrieves the product price.
    * @return The price of the product.
    */
   public float getProductPrice() { return prodPrice; }
   
   /**
    * Retrieves the taxable designation (Y= yes, N= no).
    * @return A single character to register the product as taxable (Y) or not taxable (N).
    */
   public char getTaxable() { return taxable; } 
    
   /***************************************************************************
   SET METHODS
   ***************************************************************************/
   /**
    * Sets of changes the product's ID.
    * @param pID The unique integer to identify the product.
    */
   public void setProductID(int pID) { prodID = pID; }
   
   /**
    * Sets of changes the product's category ID.
    * @param catID The integer to identify the product category for the product type.
    */
   public void setCategoryID(int catID) { categoryID = catID; }
   
   /**
    * Sets or changes the product's name.
    * @param pName The name of the product.
    */
   public void setProductName(String pName) { prodName = pName; }
   
   /**
    * Sets or changes the product's stock quantity.
    * @param sQty The current stock quantity of the product.
    */
   public void setStockQuantity(int sQty) { stockQty = sQty; }
   
   /**
    * Sets of changes the product's description.
    * @param lDesc The long description of the product.
    */
   public void setLongDesc(String lDesc) { longDesc = lDesc; }
   
   /**
    * Sets or changes the product's weight.
    * @param pWeight The weight of the product.
    */
   public void setProductWeight(float pWeight) { prodWeight = pWeight; }
   
   /**
    * Sets of changes the product's price.
    * @param pPrice The price of the product.
    */
   public void setProductPrice(float pPrice) { prodPrice = pPrice; }
   
   /**
    * Sets or changes the single character designation of the product's taxability (Y= yes, N= no).
    * @param tax A single character to register the product as taxable (Y) or not taxable (N).
    */
   public void setTaxable(char tax) { taxable = tax; }
   
}
