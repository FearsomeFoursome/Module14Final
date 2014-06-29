/*
 * ProductList class to create an object out of the Product array lists
 * #'s Company (Amy Roberts, Bella Belova, Scott Young)
 * "We pledge that we have complied with the AIC in this work."
 * AR / BB / SY
 */

package product;

import java.util.ArrayList;

/**
 *
 * @author Scott Young
 */
public class ProductList {
    private ArrayList prodList; 
    
    

    public ProductList() {
        prodList = new ArrayList[Product];
    }
    
    public void setProdList(Product pList) {
        prodList = pList;
    }
    
}
