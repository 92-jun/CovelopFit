package com.covelopfit.autotrading.controller.validator;

import com.covelopfit.autotrading.domain.upbitapi.OrderApiRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class OrderRequestValidatorTest {

    @Test
    public void regexTest() {
        String testString = "KRW-1ETC";
        String testNumberString = "134234";

        assertThat(testString.matches("^[0-9A-Z]*\\-[0-9A-Z]*$")).isEqualTo(true);
        assertThat(testNumberString.matches("^[0-9]*\\.[0-9]*$") || testNumberString.matches("^[0-9]*$")).isEqualTo(true);
    }

    @Test
    public void testMarketParam() {
        OrderApiRequest testRequest = this.getValidRequest();

        // 정상 요청
        testRequest.setMarket("KRW-ETC");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest)).isEqualTo(null);

        testRequest.setMarket("KRW-1INCH");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest)).isEqualTo(null);


        // 비정상 요청
        testRequest.setMarket(" KRW-ETC");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest) != null).isEqualTo(true);

        testRequest.setMarket("KRWETCt");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest) != null).isEqualTo(true);

        testRequest.setMarket("KRWETC ");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest) != null).isEqualTo(true);

        testRequest.setMarket("!@");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest) != null).isEqualTo(true);
    }

    @Test
    public void testSideParam() {
        OrderApiRequest testRequest = this.getValidRequest();

        // 정상 요청
        testRequest.setSide("bid");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest)).isEqualTo(null);

        testRequest.setSide("ask");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest)).isEqualTo(null);

        // 비정상 요청
        testRequest.setSide("!@");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest) != null).isEqualTo(true);

        testRequest.setSide("buy");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest) != null).isEqualTo(true);

        testRequest.setSide("sell");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest) != null).isEqualTo(true);

        testRequest.setSide("123");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest) != null).isEqualTo(true);
    }

    @Test
    public void testVolumeParam() {
        OrderApiRequest testRequest = this.getValidRequest();

        // 정상 요청
        testRequest.setVolume("0.1");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest)).isEqualTo(null);

        testRequest.setVolume("1.1");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest)).isEqualTo(null);

        testRequest.setVolume("1234");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest)).isEqualTo(null);

        // 비정상 요청
        testRequest.setVolume("!@");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest) != null).isEqualTo(true);
    }

    @Test
    public void testPriceParam() {
        OrderApiRequest testRequest = this.getValidRequest();

        // 정상 요청
        testRequest.setPrice("0.1");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest)).isEqualTo(null);

        testRequest.setPrice("1.1");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest)).isEqualTo(null);

        testRequest.setPrice("1234");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest)).isEqualTo(null);

        // 비정상 요청
        testRequest.setPrice("!@");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest) != null).isEqualTo(true);
    }

    @Test
    public void testOrderTypeParam() {
        OrderApiRequest testRequest = this.getValidRequest();

        // 정상 요청
        testRequest.setOrd_type("limit");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest)).isEqualTo(null);

        testRequest.setOrd_type("price");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest)).isEqualTo(null);

        testRequest.setOrd_type("market");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest)).isEqualTo(null);

        // 비정상 요청
        testRequest.setOrd_type("!@");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest) != null).isEqualTo(true);

        testRequest.setOrd_type("buy");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest) != null).isEqualTo(true);

        testRequest.setOrd_type("sell");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest) != null).isEqualTo(true);

        testRequest.setOrd_type("123");
        assertThat(OrderRequestValidator.checkOrderApiRequest(testRequest) != null).isEqualTo(true);
    }

    private OrderApiRequest getValidRequest() {
        OrderApiRequest orderApiRequest = new OrderApiRequest();

        orderApiRequest.setMarket("KRW-DKA");
        orderApiRequest.setSide("ask");
        orderApiRequest.setVolume("1");
        orderApiRequest.setPrice("1000000");
        orderApiRequest.setOrd_type("limit");

        return orderApiRequest;
    }
}