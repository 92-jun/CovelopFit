package com.covelopfit.autotrading.controller;

import com.covelopfit.autotrading.dto.PostOrderDto;
import com.covelopfit.autotrading.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(value = "/order", produces = {"application/json"})
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("")
    public ResponseEntity postOrder(@RequestBody @Valid PostOrderDto postOrderDto) throws NoSuchAlgorithmException {

        boolean flag = orderService.postOrder(postOrderDto);

        if(!flag){
            return new ResponseEntity("", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("",HttpStatus.OK);

    }

}
