package org.example.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.pojo.ExchangeRateRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
@Component
public  class ExchangeRatesService {
    public static List<ExchangeRateRequest> getExchangeRates(String apiUrl) {
        // 創建RestTemplate
        RestTemplate restTemplate = new RestTemplate();


        // 發起GET請求並響應
        String jsonResponse = restTemplate.getForObject(apiUrl,String.class);
        // 使用 Jackson 的 ObjectMapper 將 JSON 轉換成List
        ObjectMapper objectMapper = new ObjectMapper();
        List<ExchangeRateRequest> exchangeRates = null;
        try {
            exchangeRates = objectMapper.readValue(jsonResponse, new TypeReference<List<ExchangeRateRequest>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return exchangeRates;
    }
}