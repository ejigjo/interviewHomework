package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result<T> {
    private Error error;
    private List<ExchangeRateResponse> currency;

    public static <E> Result<E> success(List<ExchangeRateResponse> currency){
        return new Result<>(new Error("0000","成功"),currency);
    }
    public static Result error() {
        return new Result<>(new Error("E001","日期區間不符"),null);
    }
}

