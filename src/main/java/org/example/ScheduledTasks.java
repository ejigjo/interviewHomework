package org.example;


import org.example.pojo.ExchangeRateRequest;
import org.example.service.HomeworkService;
import org.example.utils.ExchangeRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ScheduledTasks{
        @Autowired
        private HomeworkService homeworkService;

        @Scheduled(cron = "0 0 18 * * ?") // 每天18:00 call API
        public void scheduledTask() {
            String apiUrl = "https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates";
            //使用自訂義的工具類獲取API集合內容
            List<ExchangeRateRequest> exchangeRates = ExchangeRatesService.getExchangeRates(apiUrl);
            //將獲取來的集合數據加入數據庫
            homeworkService.insert(exchangeRates);
        }
}
