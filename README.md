# WESTERN GOVERNOR UNIVERSITY 
## D287 – JAVA FRAMEWORKS

## -Task C-
Customize the HTML user interface for your customer’s application. The user interface should include the shop name, the product names, and the names of the parts. \
\
renamed title - line 14  ```<title>Sim Racing Hub</title>``` \
renamed header - line 19 ```<h1>Sim Racing Hub</h1>``` \
renamed header - line 22 ```<h2>Components</h2>``` \
renamed header - line 54 ```<h2>Full Setups</h2>```

## -Task D-
Add an “About” page to the application to describe your chosen customer’s company to web viewers and include navigation to and from the “About” page and the main screen. \
\
about.html created\
title changed - line 14 ```<title>About Us</title>``` \
about paragraph added - line 20-23
```
<p>
    Welcome to the home of sim racing greatness! We strive to create a community around our hobby that is fun
     and competitive for all ages. Our shop, since 1995, has been the go-to for all your gaming and performance
    needs. Need help deciding? each one of our staff is a die hard racing enthusiast ready to put together your
    next tournament winning setup, or assist with any questions you might have. You're in good hands.
</p>
```
added aboutcontroller.java to controllers with mapping
```
package com.example.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class aboutcontroller {



    @GetMapping("/about")
    public String showAboutPage() {
        return "about"; // Returns about.html
    }
}
```
about us button added mainscreen.html line 20 ```<a th:href="@{/about}" class="btn btn-primary btn-sm mb-3">About Us</a>``` \
added home button about.html - line 17 ```<a th:href="@{/mainscreen}" class="btn btn-primary btn-sm mb-3">Home</a>``` 

## -Task E-
Add a sample inventory appropriate for your chosen store to the application. You should have five parts and five products in your sample inventory and should not overwrite existing data in the database. \
\
added 5 parts in BootStrapData.java - line 44-109 *template below*
```
OutsourcedPart o= new OutsourcedPart();
        o.setCompanyName("Logitech");
        o.setName("Steering wheel");
        o.setInv(5);
        o.setPrice(20.0);
        o.setId(101L);
        outsourcedPartRepository.save(o);
        OutsourcedPart thePart=null;
        outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            if(part.getName().equals("Csl steering wheel"))thePart=part;
        }
```

added 5 products in bootstrapdata.java - line 125-134 *template below*
```
Product Budget= new Product("Budget setup",100.0,15);
productRepository.save(Budget);
```

## -Task F-
Add a “Buy Now” button to your product list. Your “Buy Now” button must meet each of the following parameters:\
•  The “Buy Now” button must be next to the buttons that update and delete products.\
•  The button should decrement the inventory of that product by one. It should not affect the inventory of any of the associated parts.\
•  Display a message that indicates the success or failure of a purchase.\
\
added button to mainscreen.html - line 86 ``` <a th:href="@{/buy(productID=${tempProduct.id})}" class="btn btn-primary btn-sm mb-3">Buy Now</a> ``` \
added BuyController.java to controllers with logic for decrementing inventory
```
    @GetMapping("/buy")
    public String buyProduct(@RequestParam("productID") Long theId, Model theModel) {
        Optional<Product> productToBuy = productRepository.findById(theId);

        if (productToBuy.isPresent()) {
            Product product = productToBuy.get();

            if (product.getInv() > 0) {
                product.setInv(product.getInv() - 1);
                productRepository.save(product);

                return "/success";
            } else {
                return "/failure";
            }
        } else {
            return "/failure";
        }
    }
}
```

Added success.html and failure.html pages for notification of purchase

## -Task G-
Modify the parts to track maximum and minimum inventory by doing the following: \
•  Add additional fields to the part entity for maximum and minimum inventory. \
•  Modify the sample inventory to include the maximum and minimum fields. \
•  Add to the InhousePartForm and OutsourcedPartForm forms additional text inputs for the inventory so the user can set the maximum and minimum values. \
•  Rename the file the persistent storage is saved to. \
•  Modify the code to enforce that the inventory is between or at the minimum and maximum value. 

set min and max fields in bootstrapdata.java - line 52-53 67-68 82-83 97-98 112-113\
added minimum and maximum inventory fields to part.java - line 31-34
```
@Min(value = 0, message = "Minimum Inventory value must be positive")
    int minInv;
    @Min(value = 0, message = "Maximum Inventory value must be positive")
    int maxInv;
```

min and max constructors part.java - line 48-49 57-58
```
this.minInv = minInv;
        this.maxInv = maxInv;
```
min and max getters/setters part.java - line 63-74
```
    public int getMinInv() {
        return minInv;
    }
    public void setMinInv(int minInv) {
        this.minInv = minInv;
    }
    public int getMaxInv() {
        return maxInv;
    }
    public void setMaxInv(int maxInv) {
        this.maxInv = maxInv;
    }
```
renamed called setters in bootstrapdata.java\
renamed spring-boot-h2-db.mv.db database to "ItemData" and renamed in app. properties\
added boolean method in part.java to check if min and max are in bounds - line 129-138
```
    public boolean checkValid() {
        if (this.inv < this.minInv) {
            return false;
        } else if (this.inv > this.maxInv) {
            return false;
        }
        else {
            return true;
        }
    }
```

## -Task H-
call checkValid method as part of form submission Addinhousepartcontroller.java - line 43-48\
call checkValid method as part of form submission Addoutsourcedpartcontroller.java 44-49
```
boolean isValid = part.checkValid();

        if (!isValid) {
            theBindingResult.rejectValue("inv", "invalid.inventory.range", "Invalid inventory range");

        }
```

## -Task I-
created 2 test classes in PartTest.java for max test and min test - line 159-175
```
@Test
void minTest(){

        partIn.setMinInv(1);
        assertEquals(1,partIn.getMinInv());
        partOut.setMinInv(1);
        assertEquals(1,partOut.getMinInv());
    }

    @Test
    void maxTest() {

        partIn.setMaxInv(100);
        assertEquals(100,partIn.getMaxInv());
        partOut.setMaxInv(100);
        assertEquals(100,partOut.getMaxInv());
    }
```



