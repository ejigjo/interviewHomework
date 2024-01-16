package org.example.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.pojo.ExchangeRateRequest;
import org.example.pojo.ExchangeRateResponse;
import org.example.pojo.ForexRequest;
import org.example.pojo.Result;
import org.example.service.HomeworkService;
import org.example.utils.ExchangeRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class HomeworkController {
    @Autowired
    private HomeworkService homeworkService;
    @GetMapping
    public void test(){

    }
    @PostMapping("/historical-rates")
    public Result< List<ExchangeRateResponse>> getHistoricalRates(@RequestBody ForexRequest forexRequest) {
        //獲取歷史匯率的集合
        List<ExchangeRateResponse>historicalRates = homeworkService.getHistoricalRates(forexRequest);
        //如果數據日期區間不符
        if (historicalRates == null) {
            return Result.error();
        }
        return Result.success(historicalRates);

    }


}
