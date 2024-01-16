package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExchangeRateResponse {
    @JsonProperty("Date")
    @JsonFormat(pattern = "yyyyMMdd")
    private Date date;

    @JsonProperty("USD/NTD")
    private Double usdToNtd;

    @JsonProperty("RMB/NTD")
    private Double rmbToNtd;

    @JsonProperty("EUR/USD")
    private Double eurToUsd;

    @JsonProperty("USD/JPY")
    private Double usdToJpy;

    @JsonProperty("GBP/USD")
    private Double gbpToUsd;

    @JsonProperty("AUD/USD")
    private Double audToUsd;

    @JsonProperty("USD/HKD")
    private Double usdToHkd;

    @JsonProperty("USD/RMB")
    private Double usdToRmb;

    @JsonProperty("USD/ZAR")
    private Double usdToZar;

    @JsonProperty("NZD/USD")
    private Double nzdToUsd;
}
