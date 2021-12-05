package com.covelopfit.autotrading.controller;

import com.covelopfit.autotrading.dto.CommonResponse;
import com.covelopfit.autotrading.dto.TickerApiResponse;
import com.covelopfit.autotrading.service.TickerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TickerController {

    private final TickerService tickerService;

    @GetMapping(value = "ticker")
    @ResponseBody
    public CommonResponse getTicker(String marketCodes) {

        TickerApiResponse tickerApiResponse = tickerService.getTicker(marketCodes);

        if(tickerApiResponse == null){
            return new CommonResponse(HttpStatus.INTERNAL_SERVER_ERROR, "TickerService 내 에러 or API 실패");
        }

        return new CommonResponse(HttpStatus.OK, "성공", tickerApiResponse);
    }

}
