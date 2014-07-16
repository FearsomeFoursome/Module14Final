/*
 * ProductList class to create an object out of the Product array lists
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
public class ProductList {
    // Assign product array list instance variable:
    private ArrayList<Product> prodList; 
       
    /**
     * Default constructor to create a new product list object.
     */
    public ProductList() { prodList = new ArrayList<Product>(); }
    
    /**
     * Method to set or change a product list object.
     * @param pList An array list of products.
     */
    public void setProdList(ArrayList<Product> pList) { prodList = pList; }
	
    /**
     * Retrieve product list.
     * @return An array list of products.
     */
    public ArrayList<Product> getProdList(){ return prodList; }
    
    /**
     * Pass the product objects into the product list array list object.
     * @param prod The product object.
     * @author Amy Roberts
     */
    public void addProductList(Product prod) { prodList.add(prod); }
    
}
