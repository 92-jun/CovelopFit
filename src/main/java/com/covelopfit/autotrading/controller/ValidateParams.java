package com.covelopfit.autotrading.controller;

public class ValidateParams {
    public static String checkValidation(OrderForm form){

        //form.getMarket()

        if(!checkSide(form.getSide()))
            return "주문 종류를 확인해주세요.";

        if(!checkVolume(form.getVolume()))
            return "주문 수량을 확인해주세요.";

        if(!checkPrice(form.getPrice()))
            return "주문 가격을 확인해주세요.";

        if(!checkOrdType(form.getOrd_type()))
            return "주문 타입을 확인해주세요.";

        return "정상";
    }

    public boolean checkMarket(String market){
        return true;
    }

    public static boolean checkSide(String side){
        return side.equals("bid") || side.equals("ask");
    }

    public static boolean checkVolume(String volume){
        return volume.matches("^[0-9]*\\.[0-9]*$") || volume.matches("^[0-9]*$");
    }

    public static boolean checkPrice(String price){
        return price.matches("^[0-9]*\\.[0-9]*$") || price .matches("^[0-9]*$");
    }

    public static boolean checkOrdType(String ordType){
        return ordType.equals("limit") || ordType.equals("price") || ordType.equals("market");
    }

}
