package com.dataSource.db;

import com.entity.Btc38TradeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by caijing on 2017/7/19.
 */
public interface Btc38TradeInfoMapper {
    int batchInsert(@Param("list") List<Btc38TradeInfo> btc38TradeBtc);

    List<Btc38TradeInfo> queryForList(Btc38TradeInfo btc38TradeBtc);
}
