package com.covelopfit.autotrading.dto;

import lombok.Data;

@Data
public class AssetResponse {

    private String currency;
    private String balance;
    private String locked;
    private String avg_buy_price;
    private Boolean avg_buy_price_modified;
    private String unit_currency;

}
