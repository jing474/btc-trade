package com.service.btc38.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dataSource.btc38.Btc38DataLoader;
import com.dataSource.db.Btc38DepthInfoMapper;
import com.entity.Btc38DepthInfo;
import com.entity.DepthDataEntity;
import com.service.LoadDepthDataService;
import com.utils.Constant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by caijing on 2017/7/20.
 */
@Service("loadBtc38DepthDataService")
public class LoadBtc38DepthDataServiceImpl implements LoadDepthDataService {

    @Resource
    private Btc38DepthInfoMapper btc38DepthInfoMapper;

    /**
     * 同时获取委托和交易数据
     */
    public void loadData(String coin){
        long time = System.currentTimeMillis();
        String response = Btc38DataLoader.getDepthData(coin);
        JSONObject jsonObj = JSON.parseObject(response);
        JSONArray buyJsonArray = (JSONArray)jsonObj.get("bids");
        Btc38DepthInfo buyDepth = getBtc38DepthBtc(buyJsonArray,time,coin);
        buyDepth.setPriceType(Constant.PriceType.BUY);

        JSONArray sellJsonArray = (JSONArray)jsonObj.get("asks");
        Btc38DepthInfo sellDepth = getBtc38DepthBtc(sellJsonArray,time,coin);
        sellDepth.setPriceType(Constant.PriceType.SELL);

        List<Btc38DepthInfo> list = new ArrayList<>();
        list.add(buyDepth);
        list.add(sellDepth);
        btc38DepthInfoMapper.batchInsert(list);
        System.out.println("LoadDepthDataService finish");
    }

    private Btc38DepthInfo getBtc38DepthBtc(JSONArray jsonArray,long time,String coin){
        List<DepthDataEntity> list = new ArrayList<>();
        for(int i=0;i<jsonArray.size();i++){
            JSONArray obj = (JSONArray)jsonArray.get(i);
            DepthDataEntity depthDataEntity = new DepthDataEntity();
            depthDataEntity.setPrice(new BigDecimal(String.valueOf(obj.get(0)), new MathContext(8, RoundingMode.HALF_UP)));
            depthDataEntity.setQty(new BigDecimal(String.valueOf(obj.get(1)), new MathContext(8, RoundingMode.HALF_UP)));
            list.add(depthDataEntity);
        }
        Collections.sort(list);

        List<BigDecimal> valueList = list.stream().map((e1)->{
            return e1.getPrice().multiply(e1.getQty(),new MathContext(8, RoundingMode.HALF_UP));
        }).collect(Collectors.toList());

        DepthDataEntity depthDataEntity = list.stream().reduce((e1,e2)->{
            DepthDataEntity d = new DepthDataEntity();
            d.setQty(e1.getQty().add(e2.getQty()));
            return d;
        }).get();

        BigDecimal totalPrice = valueList.stream().reduce((e1,e2)->{
            return e1.add(e2);
        }).get();

        Btc38DepthInfo btc38DepthBtc = new Btc38DepthInfo();
        btc38DepthBtc.setCatchTime(new Timestamp(time));
        btc38DepthBtc.setAvgPrice(totalPrice.divide(depthDataEntity.getQty(),new MathContext(8, RoundingMode.HALF_UP)));
        btc38DepthBtc.setLowPrice(list.get(list.size()-1).getPrice());
        btc38DepthBtc.setMiddlePrice(list.get((list.size()/2)).getPrice());
//        btc38DepthBtc.setPriceType(Constant.PriceType.BUY);
        btc38DepthBtc.setTopPrice(list.get(0).getPrice());
        btc38DepthBtc.setTotalNumber(depthDataEntity.getQty());
        btc38DepthBtc.setTotalPrice(totalPrice);
        btc38DepthBtc.setCoinType(coin);
        return btc38DepthBtc;
    }


}
