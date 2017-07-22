package com.service.btc38.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dataSource.btc38.Btc38DataLoader;
import com.dataSource.db.Btc38TradeInfoMapper;
import com.entity.Btc38TradeInfo;
import com.service.LoadTradeDataService;
import com.utils.Constant;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caijing on 2017/7/20.
 */
@Service("loadBtc38TradeDataService")
public class LoadBtc38TradeDataServiceImpl implements LoadTradeDataService{
    @Resource
    private Btc38TradeInfoMapper btc38TradeInfoMapper;

    /**
     * 同时获取委托和交易数据
     */
    public String loadData(String coin,String tradeId){
        long time = System.currentTimeMillis();
        String response = Btc38DataLoader.getFollowTradesData(coin,tradeId);
        JSONArray array = JSON.parseArray(response);

        List<Btc38TradeInfo> list = new ArrayList<>();
        for(Object obj:array){
            JSONObject jsonObj = (JSONObject)obj;
            Long tradeTime = jsonObj.getLongValue("date");
            BigDecimal price = jsonObj.getBigDecimal("price");
            BigDecimal amount = jsonObj.getBigDecimal("amount");
            String tid = jsonObj.getString("tid");
            int type = "buy".equalsIgnoreCase(jsonObj.getString("type"))? Constant.TradeType.BUY:Constant.TradeType.SELL;

            Btc38TradeInfo btc38TradeBtc = new Btc38TradeInfo();
            btc38TradeBtc.setPrice(price);
            btc38TradeBtc.setAmount(amount);
            btc38TradeBtc.setCatchTime(new Timestamp(time));
            btc38TradeBtc.setTid(tid);
            btc38TradeBtc.setTradeType(type);
            btc38TradeBtc.setTradeTime(new Timestamp(tradeTime*1000));
            btc38TradeBtc.setCoinType(coin);
            list.add(btc38TradeBtc);
        }
        System.out.println("LoadTradeDataService finish!");
        if(CollectionUtils.isNotEmpty(list)){
            btc38TradeInfoMapper.batchInsert(list);
            return list.get(list.size()-1).getTid();
        }else{
            return tradeId;
        }
    }

    public static void main(String[] args){
        LoadTradeDataService loadDataService = new LoadBtc38TradeDataServiceImpl();
        loadDataService.loadData("btc","2587601");
    }
}
