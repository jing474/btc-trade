package com.dataSource.btc38;

import com.utils.HttpUtils;

import java.io.InputStream;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * 从比特时代获取数据
 * Created by caijing on 2017/7/19.
 */
public class Btc38DataLoader {

    static final Map<String,String> map = new HashMap<>();
    static{
        map.put("USER-AGENT","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36");
    }

    /**
     * 获取委托数据
     * @param coinType
     * @return
     * @throws Exception
     */
    public static String getDepthData(String coinType) {
        String response = null;
        try{
            URLConnection uc =
                    HttpUtils.sendGetRequest("http://api.btc38.com/v1/depth.php?c="+coinType+"&mk_type=cny",null,map);
            response = HttpUtils.read2String((InputStream)uc.getContent());
        }catch(Exception e){
            e.printStackTrace();
            System.out.print("getDepthData error");
        }
        return response;
    }

    /**
     * 获取最近的成交数据（30条）
     * @param coinType
     * @return
     * @throws Exception
     */
    public static String getTradesData(String coinType) {
        String response = null;
        try{
            URLConnection uc =
                    HttpUtils.sendGetRequest("http://api.btc38.com/v1/trades.php?c="+coinType+"&mk_type=cny",null,map);
            response = HttpUtils.read2String((InputStream)uc.getContent());
        }catch(Exception e){
            e.printStackTrace();
            System.out.print("getTradesData error");
        }
        return response;
    }
    /**
     * 根据tradeId获得该ID之后的成交数据
     * @param coinType
     * @return
     * @throws Exception
     */
    public static String getFollowTradesData(String coinType,String tradeId) {
        String response = null;
        try{
            URLConnection uc =
                    HttpUtils.sendGetRequest("http://api.btc38.com/v1/trades.php?c="+coinType+"&mk_type=cny&tid="+tradeId,null,map);
            response = HttpUtils.read2String((InputStream)uc.getContent());
        }catch(Exception e){
            e.printStackTrace();
            System.out.print("getTradesData error");
        }
        return response;
    }

}
