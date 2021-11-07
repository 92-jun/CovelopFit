package com.covelopfit.autotrading.controller;

import com.covelopfit.autotrading.dto.PostOrderDto;
import com.covelopfit.autotrading.service.OrderService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("order-page")
    public String getOrderPage(){
        return "order-page";
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

    @PostMapping("orders")
    public String orderApi(OrderForm form) throws IOException, NoSuchAlgorithmException {

        Member member = new Member();
        UpbitKey upbitKey = new UpbitKey();

        member.setKey(upbitKey);
        member.setName("taewoo");

        if(!ValidateParams.checkValidation(form).equals("정상")) {
            log.error("Parameter validation error");
            return "redirect:/";
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("market", form.getMarket());
        params.put("side", form.getSide());
        params.put("volume", form.getVolume());
        params.put("price", form.getPrice());
        params.put("ord_type", form.getOrd_type());

        orderService.orderLimitPrice(member, params);

        return "redirect:/";
    }
}
