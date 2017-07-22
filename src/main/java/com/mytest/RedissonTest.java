package com.mytest;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by caijing on 2017/6/10.
 */
public class RedissonTest {
    public static void main(String[] args){
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("10.40.6.48:6379");
        singleServerConfig.setPassword("3EdiQ83TPh83DDhyy3C8WGY");
//        config.useClusterServers()
//                // use "rediss://" for SSL connection
//                .addNodeAddress("10.40.6.48:6379").setPassword("3EdiQ83TPh83DDhyy3C8WGY");
//        singleServerConfig.setDatabase(0);
        RedissonClient redisson = Redisson.create(config);

        // 3. Get object you needa
//        RMap<MyKey, MyValue> map = redisson.getMap("myMap");

        RMap<String, String> test = redisson.getMap("test");
//        test.put("test","test");


        RBucket<String> bucket=redisson.getBucket("cart.shop.GLB.6270207331632476160");
        bucket.set("test");
//        bucket.expireAt(1L);
        System.out.println("111111111111:"+ bucket.get());
        try{
            RLock lock = redisson.getLock("myLock");
            lock.tryLock();
            lock.unlock();
        }catch(Exception e){
            //do nothing
        }finally{

        }

        Date date = new Date(1497953848000L);
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        System.out.println(sdf.format(date));


    }
}
