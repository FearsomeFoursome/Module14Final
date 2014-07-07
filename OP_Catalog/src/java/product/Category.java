/*
 * Category Class
 * 3's Company (Amy Roberts, Bella Belova, Scott Young)
 * "We pledge that we have complied with the AIC in this work."
 * AR / BB / SY
 */


package product;

/**
 *
 * @author Scott Young
 */
public class Category {
    private int categoryID;
    private String categoryName;
    
    /**************************************************************************
     * CONSTRUCTORS
     **************************************************************************/
    
    /**
     * Default constructor to build an empty Category object.
     */
    public Category(){
        categoryID = 0;
        categoryName = "";}
        
    /**
     * Constructor that accepts the category ID and category name values.
     * @param catID integer value of the category ID.
     * @param catName String value of the category name.
     */    
    public Category(int catID, String catName){
        categoryID = catID;
        categoryName = catName;}
  
    /**************************************************************************
     * GET METHODS
     *************************************************************************/
    
    /**
     * Retrieve the category ID.
     * @return the category ID.
     */
    public int getCategoryID() { return categoryID; }
    
    /**
     * Retrieve the category name.
     * @return the category name.
     */
    public String getCategoryName() { return categoryName; }

    /**************************************************************************
     * SET METHODS
     *************************************************************************/
    
    /**
     * Set the category ID.
     * @param catID The integer value of the category ID.
     */
    public void setCategoryID(int catID) { categoryID = catID; }
    
    /**
     * Set the category name.
     * @param catName The String value of the category name.
     */
    public void setCategoryName(String catName) { categoryName = catName; }
    
}
