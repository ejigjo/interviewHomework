package org.example.service;

import com.mysql.cj.log.Log;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.HomeworkMapper;
import org.example.pojo.ExchangeRateRequest;
import org.example.pojo.ExchangeRateResponse;
import org.example.pojo.ForexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Slf4j
@Service
public class HomeworkServiceImpl implements HomeworkService {
    @Autowired
    private HomeworkMapper homeworkMapper;

    @Override
    public void insert(List<ExchangeRateRequest> rateRequest) {

        homeworkMapper.insert(rateRequest);
    }

    @Override
    public  List<ExchangeRateResponse> getHistoricalRates(ForexRequest forexRequest) {
        Date startDate = forexRequest.getStartDate();
        Date endDate = forexRequest.getEndDate();

        // 獲取當前日期
        Date currentDate = new Date();

        // 設置日期區間限制為1年前到當前日期前一天
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.YEAR, -1);
        //一年前的時間
        Date oneYearAgo = calendar.getTime();
        //當前時間前一天
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date yesterday = calendar.getTime();

        // 檢查日期範圍是否符合條件
        if (startDate.before(oneYearAgo) || endDate.after(yesterday) || startDate.after(endDate)) {
            return null;
        }
        List<ExchangeRateResponse> historicalRatesResponse = new ArrayList<>();
        List<ExchangeRateRequest> historicalRates = homeworkMapper.getHistoricalRates(forexRequest);
        //判斷幣別
        //並將判斷完成的數據返回給前端
        if(forexRequest.getCurrency().equals("usd")) {
            for (ExchangeRateRequest historicalRate : historicalRates) {
                ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();
                exchangeRateResponse.setDate(historicalRate.getDate());
                exchangeRateResponse.setUsdToNtd(historicalRate.getUsdToNtd());
                historicalRatesResponse.add(exchangeRateResponse);
            }
        }

        return historicalRatesResponse;

    }
}
