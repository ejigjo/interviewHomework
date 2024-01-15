package org.example.service;

import org.example.pojo.ExchangeRateRequest;
import org.example.pojo.ForexRequest;

import java.util.List;

public interface HomeworkService {
    void insert(List<ExchangeRateRequest> rateRequest);

    List<ExchangeRateRequest> getHistoricalRates(ForexRequest forexRequest);
}


