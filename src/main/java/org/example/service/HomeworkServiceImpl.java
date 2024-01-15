package org.example.service;

import org.example.mapper.HomeworkMapper;
import org.example.pojo.ExchangeRateRequest;
import org.example.pojo.ForexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class HomeworkServiceImpl implements HomeworkService {
    @Autowired
    private HomeworkMapper homeworkMapper;

    @Override
    public void insert(List<ExchangeRateRequest> rateRequest) {

        homeworkMapper.insert(rateRequest);
    }

    @Override
    public List<ExchangeRateRequest> getHistoricalRates(ForexRequest forexRequest) {
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
        List<ExchangeRateRequest> historicalRates = homeworkMapper.getHistoricalRates(forexRequest);
        return historicalRates;

    }
}
