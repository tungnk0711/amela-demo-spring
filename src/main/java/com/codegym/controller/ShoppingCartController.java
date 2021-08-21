package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.model.Items;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/shoppingcart")
public class ShoppingCartController {
    @Autowired
    private IProductService productService;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/ordernow/{id}", method = RequestMethod.GET)
    public String ordernow(@PathVariable(value = "id") Long id, ModelMap mm, HttpSession session) {

        if (session.getAttribute("cart") == null) {
            List<Items> cart = new ArrayList<Items>();
            Product product =  this.productService.findProductById(id);
            cart.add(new Items(product, 1));
            session.setAttribute("cart", cart);
        } else {
            List<Items> cart = (List<Items>) session.getAttribute("cart");

            // using method getIndex here
            int index = getIndex(id, session);
            if (index == -1)
                cart.add(new Items(this.productService.findProductById(id), 1));
            else {
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }

        return "/product/cart";
    }

    @SuppressWarnings("unchecked")
    private int getIndex(Long id, HttpSession session) {

        List<Items> cart = (List<Items>) session.getAttribute("cart");

        for (int i = 0; i < cart.size(); i++)

            if (cart.get(i).getProduct().getId() == id)
                return i;

        return -1;
    }

}
