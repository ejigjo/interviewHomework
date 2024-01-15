package org.example.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.pojo.ExchangeRateRequest;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

public  class ExchangeRatesService {
    public static List<ExchangeRateRequest> getExchangeRates(String apiUrl) {


        // 创建RestTemplate对象
        RestTemplate restTemplate = new RestTemplate();


        // 发起GET请求并获取响应
            String jsonResponse = restTemplate.getForObject(apiUrl,String.class);
        // 使用 Jackson 的 ObjectMapper 将 JSON 转换为对象列表
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
