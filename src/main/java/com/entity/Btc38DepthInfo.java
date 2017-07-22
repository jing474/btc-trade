package com.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by caijing on 2017/7/19.
 */
public class Btc38DepthInfo implements Serializable{

    private long id;
    private Timestamp catchTime;

    private int priceType;

    private BigDecimal topPrice;
    private BigDecimal middlePrice;
    private BigDecimal lowPrice;
    private BigDecimal avgPrice;
    private BigDecimal totalNumber;
    private BigDecimal totalPrice;

    private String coinType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getCatchTime() {
        return catchTime;
    }

    public void setCatchTime(Timestamp catchTime) {
        this.catchTime = catchTime;
    }

    public int getPriceType() {
        return priceType;
    }

    public void setPriceType(int priceType) {
        this.priceType = priceType;
    }

    public BigDecimal getTopPrice() {
        return topPrice;
    }

    public void setTopPrice(BigDecimal topPrice) {
        this.topPrice = topPrice;
    }

    public BigDecimal getMiddlePrice() {
        return middlePrice;
    }

    public void setMiddlePrice(BigDecimal middlePrice) {
        this.middlePrice = middlePrice;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(BigDecimal avgPrice) {
        this.avgPrice = avgPrice;
    }

    public BigDecimal getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(BigDecimal totalNumber) {
        this.totalNumber = totalNumber;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCoinType() {
        return coinType;
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }
}
