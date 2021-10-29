package com.covelopfit.autotrading.controller.validator;

import com.covelopfit.autotrading.domain.upbitapi.OrderApiRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class OrderRequestValidator {

    public static String checkOrderApiRequest(OrderApiRequest orderApiRequest) {
        String validationResult = null;

        // 마켓 파라미터 검증
        validationResult = checkMarket(orderApiRequest.getMarket());
        if (StringUtils.hasText(validationResult)) {
            return validationResult;
        }

        // 주문 타입(매수/매도) 파라미터 검증
        validationResult = checkSide(orderApiRequest.getSide());
        if (StringUtils.hasText(validationResult)) {
            return validationResult;
        }

        // 수량 파라미터 검증
        validationResult = checkVolume(orderApiRequest.getVolume());
        if (StringUtils.hasText(validationResult)) {
            return validationResult;
        }

        // 가격 파라미터 검증
        validationResult = checkPrice(orderApiRequest.getPrice());
        if (StringUtils.hasText(validationResult)) {
            return validationResult;
        }

        // 주문 타입(시장가/지정가) 파라미터 검증
        validationResult = checkOrdType(orderApiRequest.getOrd_type());
        if (StringUtils.hasText(validationResult)) {
            return validationResult;
        }

        return validationResult;
    }

    private static String checkMarket(String marketParam) {
        String result = null;

        // 알파뱃, 숫자, '-' 문자만 포함 & 마켓 파라미터 타입 패턴과 일치하는지 확인
        if (!(marketParam.matches("^[0-9A-Z]*\\-[0-9A-Z]*$"))) {
            return "마켓 정보를 확인해주세요.";
        }

        return result;
    }

    private static String checkSide(String sideParam) {
        String result = null;

        // upbit API 스펙에서 지원하는 파라미터인지 검증("bid", "ask")
        if (!(sideParam.equals("bid") || sideParam.equals("ask"))) {
            result = "매수/매도 정보를 확인해주세요";
        }

        return result;
    }

    private static String checkVolume(String volumeParam) {
        String result = null;

        // 숫자 형식인지 검증
        if (!(volumeParam.matches("^[0-9]*\\.[0-9]*$") || volumeParam.matches("^[0-9]*$"))) {
            return "주문 수량을 확인해주세요";
        }

        return result;

    }

    private static String checkPrice(String priceParam) {
        String result = null;

        // 숫자 형식인지 검증
        if (!(priceParam.matches("^[0-9]*\\.[0-9]*$") || priceParam.matches("^[0-9]*$"))) {
            return "주문 수량을 확인해주세요";
        }

        return result;

    }

    private static String checkOrdType(String ordTypeParam) {
        String result = null;

        if (!(ordTypeParam.equals("limit") || ordTypeParam.equals("price") || ordTypeParam.equals("market"))) {
            return "주문 타입을 확인해주세요";
        }
        return result;

    }
}
