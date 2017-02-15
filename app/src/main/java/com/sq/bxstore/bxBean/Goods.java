package com.sq.bxstore.bxBean;

import java.math.BigDecimal;

public class Goods {
    private Long id;

    private String gname;

    private BigDecimal price;

    private Long brand;

    private String goodimglist;

    private String gfullname;

    private Integer storenumb;

    private String goodimg;

    private String detail;
    
    private String img1;

    private String img2;

    private String img3;

    private String img4;

    private String img5;

    public Goods(){
    	
    }
    
    public Goods(Long id,String gname,
    		BigDecimal price,Long brand,
    		String goodimglist,String gfullname,
    		Integer storenumb,String goodimg,
    		String detail,String img1,String img2,String img3,String img4,String img5) {
		this.id=id;
		this.gname=gname;
		this.price=price;
		this.brand=brand;
		this.goodimglist=goodimglist;
		this.gfullname=gfullname;
		this.storenumb=storenumb;
		this.goodimg=goodimg;
		this.detail=detail;
		this.img1=img1;
		this.img2=img2;
		this.img3=img3;
		this.img4=img4;
		this.img5=img5;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname == null ? null : gname.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getBrand() {
        return brand;
    }

    public void setBrand(Long brand) {
        this.brand = brand;
    }

    public String getGoodimglist() {
        return goodimglist;
    }

    public void setGoodimglist(String goodimglist) {
        this.goodimglist = goodimglist == null ? null : goodimglist.trim();
    }

    public String getGfullname() {
        return gfullname;
    }

    public void setGfullname(String gfullname) {
        this.gfullname = gfullname == null ? null : gfullname.trim();
    }

    public Integer getStorenumb() {
        return storenumb;
    }

    public void setStorenumb(Integer storenumb) {
        this.storenumb = storenumb;
    }

    public String getGoodimg() {
        return goodimg;
    }

    public void setGoodimg(String goodimg) {
        this.goodimg = goodimg == null ? null : goodimg.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public String getImg3() {
		return img3;
	}

	public void setImg3(String img3) {
		this.img3 = img3;
	}

	public String getImg4() {
		return img4;
	}

	public void setImg4(String img4) {
		this.img4 = img4;
	}

	public String getImg5() {
		return img5;
	}

	public void setImg5(String img5) {
		this.img5 = img5;
	}
}