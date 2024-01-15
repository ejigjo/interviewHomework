package org.example.mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.ExchangeRateRequest;
import org.example.pojo.ForexRequest;

import java.util.List;

@Mapper
public interface HomeworkMapper {
    @Insert({
            "<script>",
            "INSERT IGNORE INTO collection (date, usd_to_ntd)",
            "VALUES ",
            "<foreach collection='list' item='item' index='index' separator=','>",
            "(#{item.date}, #{item.usdToNtd})",
            "</foreach>",
            "</script>"
    })
   void insert(List<ExchangeRateRequest> rateRequest);
    @Select("SELECT id, date, usd_to_ntd " +
            "FROM collection " +
            "WHERE date BETWEEN #{startDate} AND #{endDate} " +
            "  AND usd_to_ntd IS NOT NULL ")
   List<ExchangeRateRequest> getHistoricalRates(ForexRequest forexRequest);
}
