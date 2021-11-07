package com.covelopfit.autotrading.controller;

import com.covelopfit.autotrading.dto.PostOrderDto;
import com.covelopfit.autotrading.service.OrderService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/order", produces = {"application/json"})
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("")
    public ResponseEntity postOrder(@RequestBody @Valid PostOrderDto postOrderDto) {

        boolean flag = orderService.postOrder(postOrderDto);

        if(!flag){
            log.error("[OrderAPI] parameter validation fail");
            return new ResponseEntity("", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("",HttpStatus.OK);

    }

}
