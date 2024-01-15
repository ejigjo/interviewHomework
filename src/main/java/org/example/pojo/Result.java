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
    private List<ExchangeRateRequest> currency;

    public static <E> Result<E> success(List<ExchangeRateRequest> currency){
//        Result<E> result = new Result<>();
//        result.setError(new Error("0000", "成功"));
//        result.setCurrency(currency);
        return new Result<>(new Error("0000","成功"),currency);
    }
    public static Result error() {
//        Result result = new Result<>();
//        result.setError(new Error("E001", "日期區間不符" ));
        return new Result<>(new Error("E001","日期區間不符"),null);
    }
}

