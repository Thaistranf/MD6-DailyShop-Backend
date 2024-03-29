package com.example.dailyshop.controller.RestController;

import com.example.dailyshop.model.entity.CartDetails;
import com.example.dailyshop.model.revenue.Revenue;
import com.example.dailyshop.service.AccountService;
import com.example.dailyshop.service.webservice.CartDetailsService;
import com.example.dailyshop.service.webservice.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class CartDetailsController {
    @Autowired
    private CartDetailsService cartDetailsService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CartService cartService;

    @GetMapping("/account/getOrderDetails")
    public ResponseEntity<List<CartDetails>> getAllOrderDetails() {
        try {
            List<CartDetails> cartDetails = cartDetailsService.findAll();
            if (cartDetails.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(cartDetails, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println("1223" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/account/findOrderDetailsByOrderId/{cartId}")
    ResponseEntity<List<CartDetails>> findOrderDetails(@PathVariable Long cartId) {
        List<CartDetails> cartDetails = cartDetailsService.findCartDetailsByCartId(cartId);
        if (cartDetails != null) {
            return new ResponseEntity<>(cartDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


}
