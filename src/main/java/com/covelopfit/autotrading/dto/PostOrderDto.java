package com.covelopfit.autotrading.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostOrderDto {

    private String market;
    private String side;
    private String volume;
    private String price;
    private String ord_type;

}
