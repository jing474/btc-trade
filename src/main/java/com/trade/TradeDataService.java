package com.trade;


import com.utils.HttpUtils;

import java.io.InputStream;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by caijing on 2017/7/19.
 */
public class TradeDataService {
    public static void main(String[] args) throws Exception {

        Map<String,String> map = new HashMap<String,String>();
        map.put("USER-AGENT","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36");

        URLConnection uc =
                HttpUtils.sendGetRequest("http://api.btc38.com/v1/depth.php?c=btc&mk_type=cny",null,map);

        System.out.print(HttpUtils.read2String((InputStream)uc.getContent()));


    }
}
