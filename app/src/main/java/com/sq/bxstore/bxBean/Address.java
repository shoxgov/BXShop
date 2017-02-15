package com.sq.bxstore.bxBean;

public class Address {
    private Long id;

    private Long uerid;

    private String username;
    
    private String address;

    private String name;

    private String post;

    private String phonenumb;

    private String telnumb;

    private Integer isuse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUerid() {
        return uerid;
    }

    public void setUerid(Long uerid) {
        this.uerid = uerid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    public String getPhonenumb() {
        return phonenumb;
    }

    public void setPhonenumb(String phonenumb) {
        this.phonenumb = phonenumb == null ? null : phonenumb.trim();
    }

    public String getTelnumb() {
        return telnumb;
    }

    public void setTelnumb(String telnumb) {
        this.telnumb = telnumb == null ? null : telnumb.trim();
    }

    public Integer getIsuse() {
        return isuse;
    }

    public void setIsuse(Integer isuse) {
        this.isuse = isuse;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}