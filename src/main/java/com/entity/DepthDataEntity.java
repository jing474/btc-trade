package com.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by caijing on 2017/7/20.
 */
public class DepthDataEntity implements Serializable,Comparable<DepthDataEntity> {

    private BigDecimal price;
    private BigDecimal qty;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    @Override
    public int compareTo(DepthDataEntity o) {
        return this.getPrice().subtract(o.getPrice()).floatValue()>0?-1:1;
    }
}
