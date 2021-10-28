package com.covelopfit.autotrading.controller;

import com.covelopfit.autotrading.domain.upbitapi.OrderApiRequest;
import com.covelopfit.autotrading.domain.upbitapi.OrderApiResponse;
import com.covelopfit.autotrading.service.upbitapi.UpbitApiCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Controller
public class OrderController {

    @Autowired
    private UpbitApiCallService upbitApiCallService;

    @PostMapping("/order")
    @ResponseBody
    public OrderApiResponse makeOrder(@RequestBody OrderApiRequest orderApiRequest) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return upbitApiCallService.callUpbitOrderApi(orderApiRequest);
    }
}
