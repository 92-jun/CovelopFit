package com.covelopfit.autotrading.testdata;

public class PostOrderTestData {
    private String market;
    private String side;
    private String volume;
    private String price;
    private String ord_type;
    private String accessKey;
    private String secretKey;

    public PostOrderTestData(String market, String side, String volume, String price, String ord_type, String accessKey, String secretKey) {
        this.market = market;
        this.side = side;
        this.volume = volume;
        this.price = price;
        this.ord_type = ord_type;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }


    public String getJsonString() {

        StringBuilder saveDtoJson = new StringBuilder();
        saveDtoJson.append("{")
                .append("\"market\":\"").append(market).append("\",")
                .append("\"side\":\"").append(side).append("\",")
                .append("\"volume\":\"").append(volume).append("\",")
                .append("\"price\":\"").append(price).append("\",")
                .append("\"ord_type\":\"").append(ord_type).append("\",")
                .append("\"accessKey\":\"").append(accessKey).append("\",")
                .append("\"secretKey\":\"").append(secretKey).append("\"}");

        return saveDtoJson.toString();
    }
}
