/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package product;

import product.Product_Queries.*;
import java.util.Arrays;
import product.Product;
/**
 *
 * @author Bella Belova
 */
public class SearchProduct {
   private Product_Queries pq;
   boolean letter;
   int max_index;
   int index;
   int prod_id_num;
   String prod_id_letter;
   boolean wrong_search_param;
   int num_records;
   Product product[];
   int prodID[];
   String descr[];
   float  price[];
   int qty[];
    
   
   // Constructor for Java Interface for Initialize array results
   public SearchProduct()
    {
        pq = new Product_Queries();
        wrong_search_param = true;      // for empty field return true
        num_records = 0;
        for(index = 0; index < 100; index++)
        {
            // Maximum result arrays capacity is 100 rows
            
            prodID = new int[100];
            descr = new String[100];
            price = new float[100];
            qty = new int[100];
            product[index] = new Product();
        }
    }
    
   // User search by Product ID
    public void setProductID(String prod_id)
    {
        if(prod_id.isEmpty())
        {
            letter = true;
            prod_id_letter = "";
            wrong_search_param = true;
        }
        else
        {
            wrong_search_param = false;
            max_index = prod_id.length();       // Check number of symbols
            prod_id_num = 0;
            for(index = 0; index < max_index; index++)
            {
                if((prod_id.charAt(index) < '0')||(prod_id.charAt(index) > '9'))
                {
                    // At lease one symbol is not a digit
                    letter = true;
                    prod_id_letter = prod_id;
                    break;
                }
                
                // in opposite case, if the whole input in digits, convert to Integer
                prod_id_num += prod_id_num*10 + prod_id.charAt(index) - '0';
            }
            if(index == max_index)
            {
                letter = false;
            }
        }    
    }
   
    // User enter characters for search. Search by Product Description
    public void getDataFromTable() throws TableException
    {
        if(letter == true)
        {
            product = pq.searchByProdDesc(prod_id_letter);
        }
        else
        {
            product = pq.searchbyProductID(prod_id_num);
        }
        num_records = pq.getMaxIndex();
        for(index = 0; index < num_records; index++)
        {
            // Filling up an arrays with SQL results
           prodID[index] = product[index].getProdID();
           descr[index] = product[index].getProdName();
           price[index] = product[index].getProdPrice();
           qty[index] = product[index].getStockQty();            
        }
    }
    
    public int getNumRecords()
    {
        if(wrong_search_param == true)
            return 0;
        else
            return num_records;
    }
    public int getProdId(int index)
    {
        return prodID[index];
    }
    
    public String getDescr(int index)
    {
        return descr[index];
    }
    
    public float getPrice(int index)
    {
        return price[index];
    }
    public int getQty(int index)
    {
        return qty[index];
    }
}