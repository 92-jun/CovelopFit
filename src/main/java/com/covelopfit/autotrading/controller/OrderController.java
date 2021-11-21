package com.covelopfit.autotrading.controller;

import com.covelopfit.autotrading.dto.OrderApiResponse;
import com.covelopfit.autotrading.dto.OrderForm;
import com.covelopfit.autotrading.service.OrderService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("order-page")
    public String getOrderPage(){
        return "order-page";
    }

    @PostMapping(value = "order")
    public ResponseEntity postOrder(@ModelAttribute @Valid OrderForm orderForm) {

        OrderApiResponse orderApiResponse = orderService.postOrder(orderForm);

        if(orderApiResponse == null){
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok().body(orderApiResponse);

    }

}
