/*
***************************************************************************
*	Author:    Lisa Frye											      *
*   Filename:  utils.js                                                   *
*   Purpose:   JavaScript utililty functions for online olympic store for *
*              a class project                                            *
***************************************************************************
*/

/* Function to print common header on all web pages */
function header() {
  document.write('<div id="container" style="width:100%">');

  document.write('<div id="header" style="background-color:aqua;">');
  document.write('<h1 style="margin-bottom:0;text-align:center">Olympic Store Project</h1>');
  document.write('</div>');


  document.write('<div id="menu">');
  document.write('<a href="createAccount.html">Create Account</a> - ');

  document.write("<a href='login.html'>Login</a> - ");
  document.write("<a href='logout.html'>Logout</a> - ");
   
  document.write('<a href="browseCats.html">Browse Catalog</a>');
   
  document.write(' - <a href="checkout.html">Checkout</a> - ');
  document.write('<a href="checkOrders.html">Check Orders</a>');


   document.write('<form id="search" method="post" action="searchProds.html">');
   //document.write('<form id="search" method="post" action="/default/SearchProduct_servlet.java">');
   document.write('<input name="prodNumber" id="prodNumber" size="12" maxlength="15"/>');
   document.write(' <input type="submit" value="Search"/>');
   document.write('</form>');
   
   
   document.write('</div>');   // end menu div

  document.write('<div id="content">');   // content area
}  // end function header


/* Function to print common footer to all web pages */
function footer() {
  document.write("</div>");  // end content div

  document.write("<div id='footer'>");
  document.write("<a style='margin-right:100px' href='contact.html'>Contact Us</a>");
  document.write("Copyright &#64; 2014 Frye");
  document.write("</div>");

   document.write("</div>");
}  // end function footer
