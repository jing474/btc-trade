package com.service;

import org.springframework.stereotype.Service;

/**
 * Created by caijing on 2017/7/20.
 */
@Service
public interface LoadTradeDataService {
    public String loadData(String coin,String tradeId);
}
