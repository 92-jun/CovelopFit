package com.covelopfit.autotrading.controller;

import com.covelopfit.autotrading.controller.validator.OrderRequestValidator;
import com.covelopfit.autotrading.domain.upbitapi.OrderApiRequest;
import com.covelopfit.autotrading.domain.upbitapi.OrderApiResponse;
import com.covelopfit.autotrading.service.upbitapi.UpbitApiCallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Controller
public class OrderController {

    @Autowired
    private UpbitApiCallService upbitApiCallService;

    @PostMapping("/order")
    @ResponseBody
    public OrderApiResponse makeOrder(@RequestBody OrderApiRequest orderApiRequest) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        // 주문 API 파라미터 검증
        String validationResult = OrderRequestValidator.checkOrderApiRequest(orderApiRequest);
        if (validationResult != null) {
            log.error(String.format("[OrderAPI] parameter validation fail : %s", validationResult));
            return null;
        }

        return upbitApiCallService.callUpbitOrderApi(orderApiRequest);
    }
}
