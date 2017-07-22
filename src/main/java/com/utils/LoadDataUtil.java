package com.utils;

import com.service.LoadDepthDataService;
import com.service.LoadTradeDataService;

import javax.annotation.Resource;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by caijing on 2017/7/20.
 */
public class LoadDataUtil {
    @Resource
    static LoadDepthDataService loadDepthDataService;

    @Resource
    static LoadTradeDataService loadTradeDataService;

    volatile static String btcTradeId = "2593604";
    volatile static String dogeTradeId = "2459521";
    static int i = 0;
    static int j = 0;

    public static void scheduled(){
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        loadDepthDataService = SpringContexts.getBean("loadBtc38DepthDataService",LoadDepthDataService.class);

//        loadDepthDataService.loadData("btc");
//        loadDepthDataService.loadData("doge");
        ses.scheduleWithFixedDelay(new Runnable(){
            @Override
            public void run() {
                try{
                    loadDepthDataService.loadData("btc");
                    loadDepthDataService.loadData("doge");
                    System.out.println("抓取btc委托数据，第 "+ (++i) +" 次");
                    System.out.println("抓取doge委托数据，第 "+ (i) +" 次");
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        },0,15, TimeUnit.SECONDS);

        loadTradeDataService = SpringContexts.getBean("loadBtc38TradeDataService",LoadTradeDataService.class);
//        loadTradeDataService.loadData("btc",tradeId);
        ScheduledExecutorService ses2 = Executors.newScheduledThreadPool(1);
        ses2.scheduleWithFixedDelay(new Runnable(){
            @Override
            public void run() {
                try{
                    String returnBtcTradeId = loadTradeDataService.loadData("btc",btcTradeId);
                    String returnDogeTradeId = loadTradeDataService.loadData("doge",dogeTradeId);
                    btcTradeId  = returnBtcTradeId;
                    dogeTradeId = returnDogeTradeId;
                    System.out.println("抓取btc交易数据，第 "+ (++j) +" 次 交易ID为："+btcTradeId);
                    System.out.println("抓取doge交易数据，第 "+ (j) +" 次 交易ID为："+dogeTradeId);
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        },0,15, TimeUnit.SECONDS);

    }
}
