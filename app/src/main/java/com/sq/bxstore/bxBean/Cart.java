package com.sq.bxstore.bxBean;

import java.math.BigDecimal;

public class Cart {
    private Long id;

    private Long userid;

    private String username;
    
    private Long goodsid;

    private Integer count;

    private BigDecimal price;
    
    private Goods goodsinfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Long goodsid) {
        this.goodsid = goodsid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

	public Goods getGoodsinfo() {
		return goodsinfo;
	}

	public void setGoodsinfo(Goods goodsinfo) {
		this.goodsinfo = goodsinfo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}