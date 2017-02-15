package com.sq.bxstore.bxBean;



public class GoodsCategory {
    private Integer id;

    private String cateName;

    private Integer level;
    
    private Integer parentId;

    private Integer cateOrder; 

    public Integer getGoodslist() {
		return cateOrder;
	}

	public void setGoodslist(Integer cateOrder) {
		this.cateOrder = cateOrder;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName == null ? null : cateName.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    
}