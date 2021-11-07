package com.covelopfit.autotrading.domain.upbitapi;

import lombok.Data;

@Data
public class OrderApiRequest {

    private String market;
    private String side;
    private String volume;
    private String price;
    private String ord_type;
    private String identifier;

}
