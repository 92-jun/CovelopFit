package com.covelopfit.autotrading.controller;
import com.covelopfit.autotrading.domain.Member;
import com.covelopfit.autotrading.domain.UpbitKey;
import com.covelopfit.autotrading.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Controller
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("order-page")
    public String showOrderPage(){
        return "order-page";
    }

    @PostMapping("buy-order")
    public String buyOrder(OrderForm form) throws IOException, NoSuchAlgorithmException {

        Member member = new Member();
        UpbitKey upbitKey = new UpbitKey();

        member.setKey(upbitKey);
        member.setName("taewoo");

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
