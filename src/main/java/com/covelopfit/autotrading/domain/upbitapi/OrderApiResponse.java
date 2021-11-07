package com.covelopfit.autotrading.domain.upbitapi;

import lombok.Data;

@Data
public class OrderApiResponse {
    private String uuid;
    private String side;
    private String ord_type;
    private String price;
    private String avg_price;
    private String state;
    private String market;
    private String created_at;
    private String volume;
    private String remaining_volume;
    private String reserved_fee;
    private String remaining_fee;
    private String paid_fee;
    private String locked;
    private String executed_volume;
    private Integer trades_count;
}
