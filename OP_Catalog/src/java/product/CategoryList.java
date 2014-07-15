/*
 * Category List Class to create an array list of Category objects.
 * 3's Company (Amy Roberts, Bella Belova, Scott Young)
 * Author: Scott Young, Amy Roberts
 * "We pledge that we have complied with the AIC in this work."
 * AR / BB / SY
 */

package product;

import java.util.ArrayList;

/**
 *
 * @author Scott Young, Amy Roberts
 */
public class CategoryList {
    // Assign category array list instance variable:
    private ArrayList<Category> catList; 
       
    /**
     * Default constructor to create a new category list object.
     */
    public CategoryList() { catList = new ArrayList<Category>(); } 
    
    /**
     * Set the category list containing an array of Category objects.
     * @param cList An array list of category objects.
     */
    public void setCatList(ArrayList<Category> cList) { catList = cList; }    
    
    /**
     * Retrieve the category list.
     * @return An array list of categories.
     */
    public ArrayList<Category> getCatList(){ return catList; }
    
    /**
     * Add the passed parameter into the category array list.
     * @param cat The product object.
     * @author Amy Roberts
     */
    public void addCategoryList(Category cat) { catList.add(cat); }    
    
}
