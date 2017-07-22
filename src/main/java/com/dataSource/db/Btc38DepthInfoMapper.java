package com.dataSource.db;

import com.entity.Btc38DepthInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by caijing on 2017/7/19.
 */
public interface Btc38DepthInfoMapper {


    int batchInsert(@Param("list") List<Btc38DepthInfo> btc38DepthBtcs);

    List<Btc38DepthInfo> queryForList(Btc38DepthInfo btc38DepthBtc);
}
