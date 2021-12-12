package com.covelopfit.autotrading.controller;

import com.covelopfit.autotrading.dto.AssetResponse;
import com.covelopfit.autotrading.dto.CommonResponse;
import com.covelopfit.autotrading.service.AssetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class assetController {

    private final AssetService assetService;

    @GetMapping("/accounts")
    @ResponseBody
    public CommonResponse getAllAsset() {

        List<AssetResponse> assetList = assetService.getAllAsset();

        if (assetList == null ) {
            log.error("자산조회 API 호출 실패");
            return new CommonResponse(HttpStatus.INTERNAL_SERVER_ERROR, "실패");
        }

        return new CommonResponse(HttpStatus.OK, "성공", assetList);
    }
}
