package com.covelopfit.autotrading.controller.validator;

import com.covelopfit.autotrading.controller.ValidateParams;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;


@SpringBootTest
public class OrderParameterValidationTest {

    @Test
    public void checkSideTest(){
        String testSide = "bid";
        boolean result = ValidateParams.checkSide(testSide);

        Assert.assertTrue(result);
    }

    @Test
    public void checkVolumeTest(){
        String testVolume = "ten";
        boolean result = ValidateParams.checkVolume(testVolume);

        Assert.assertFalse(result);
    }

    @Test
    public void checkPriceTest(){
        String testPrice = "10000";
        boolean result = ValidateParams.checkVolume(testPrice);

        Assert.assertTrue(result);
    }

    @Test
    public void checkOrdTypeTest(){
        String testOrdType = "limited";
        boolean result = ValidateParams.checkVolume(testOrdType);

        Assert.assertFalse(result);
    }

}
