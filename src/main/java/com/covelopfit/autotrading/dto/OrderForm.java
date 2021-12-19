package com.covelopfit.autotrading.dto;

import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderForm {

    @NotBlank
    @Description("Coin Name. Ex) KRW-BTC Format.")
    @Pattern(regexp = "^[A-Z]+[-]?[0-9A-Z]+$")
    private String market;

    @NotBlank
    @Description("Order Type Value. 1. bid = buy , 2. ask = sell ")
    @Pattern(regexp = "^[a-z]+$")
    private String side;

    @NotBlank
    @Positive
    @Description("Order Amount. -> 주문량")
    private String volume;

    @NotBlank
    @Positive
    @Description("Pirce at Order Time, 주문 당시 코인가격")
    private String price;

    @NotBlank
    @Description("Order Type.   1. limit(지정가주문) 2. price(시장가 매수 주문) 3. market(시장가 매도 주문)")
    @Pattern(regexp = "^[a-z]+$")
    private String ord_type;


}
