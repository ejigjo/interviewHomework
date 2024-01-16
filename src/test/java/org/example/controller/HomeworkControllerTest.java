package org.example.controller;

import org.example.pojo.ExchangeRateRequest;
import org.example.pojo.ExchangeRateResponse;
import org.example.pojo.ForexRequest;
import org.example.pojo.Result;
import org.example.service.HomeworkService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeworkControllerTest {

    @Mock
    private HomeworkService homeworkService;

    @InjectMocks
    private HomeworkController homeworkController;

    public HomeworkControllerTest() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testGetHistoricalRates_Success() throws ParseException {

        ForexRequest forexRequest = new ForexRequest();
        List<ExchangeRateResponse> mockHistoricalRates = new ArrayList<>();
        mockHistoricalRates.add(new ExchangeRateResponse());
        mockHistoricalRates.add(new ExchangeRateResponse());

        //return不是為Null
        Mockito.when(homeworkService.getHistoricalRates(forexRequest)).thenReturn(mockHistoricalRates);


        Result< List<ExchangeRateResponse>> result = homeworkController.getHistoricalRates(forexRequest);

        //結果是否符合預期
        assertEquals("0000", result.getError().getCode());
        assertEquals("成功", result.getError().getMessage());
        assertEquals(mockHistoricalRates, result.getCurrency());
    }


    @Test
    public void testGetHistoricalRates_Failed() {

        ForexRequest forexRequest = new ForexRequest();
        //假如homeworkService.getHistoricalRates(forexRequest) return為null
        Mockito.when(homeworkService.getHistoricalRates(forexRequest)).thenReturn(null);

        Result< List<ExchangeRateResponse>> result = homeworkController.getHistoricalRates(forexRequest);

        //結果是否符合預期
        assertEquals("E001", result.getError().getCode());
        assertEquals("日期區間不符", result.getError().getMessage());
        assertEquals(null, result.getCurrency());
    }
}