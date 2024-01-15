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

        @Scheduled(cron = "0 07 19 * * ?") // 每天凌晨执行
        public void scheduledTask() {
            String apiUrl = "https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates";
            List<ExchangeRateRequest> exchangeRates = ExchangeRatesService.getExchangeRates(apiUrl);
            homeworkService.insert(exchangeRates);
        }
}
