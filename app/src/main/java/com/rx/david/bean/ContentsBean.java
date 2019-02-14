package com.rx.david.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 首页数据
 */
public class ContentsBean implements Serializable {

    private static final long serialVersionUID = -3892762561111935048L;

    // ID
    private String id;
    // 标题名称
    private String name;
    // 图片链接
    private String imageUrl;
    // 描述
    private String description;
    // 外链地址
    private String url;
    // 是否是商家
    private boolean isStore;
    // 商家ID
    private String storeId;
    // 小图标
    private String imageSmallUrl;
    // 大图标
    private String imageFullUrl;
    // 库存
    private int stock;
    // 卖出数量
    private int sold;
    // 原价
    private double priceOriginal;
    // 特殊营业时间
    private String hourSpecial;
    // 当前卖价
    private double priceCurrent;
    // 折扣
    private List<Object> discounts;
    // 颜色（RGB）
    private List<Integer> color;
    // 联系电话
    private String phone;
    // 纬度
    private double latitude;
    // 经度
    private double longitude;
    // 地址
    private String address;
    // 城市
    private String city;
    // 街道
    private String state;
    // 邮编
    private String zipcode;
    // 是否推荐
    private boolean recommend;
    // 数量
    private int count;
    // 均价
    private double average;
    // 评论数
    private int review;
    // 星级
    private int rating;
    // 标签
    private List<String> tags;
    // 营业时间
    private String hours;
    // 是否正在营业
    private boolean open;
    // 相关名称
    private String referName;
    // 相关ID
    private String referId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isStore() {
        return isStore;
    }

    public void setStore(boolean store) {
        isStore = store;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getImageSmallUrl() {
        return imageSmallUrl;
    }

    public void setImageSmallUrl(String imageSmallUrl) {
        this.imageSmallUrl = imageSmallUrl;
    }

    public String getImageFullUrl() {
        return imageFullUrl;
    }

    public void setImageFullUrl(String imageFullUrl) {
        this.imageFullUrl = imageFullUrl;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public double getPriceOriginal() {
        return priceOriginal;
    }

    public void setPriceOriginal(double priceOriginal) {
        this.priceOriginal = priceOriginal;
    }

    public String getHourSpecial() {
        return hourSpecial;
    }

    public void setHourSpecial(String hourSpecial) {
        this.hourSpecial = hourSpecial;
    }

    public double getPriceCurrent() {
        return priceCurrent;
    }

    public void setPriceCurrent(double priceCurrent) {
        this.priceCurrent = priceCurrent;
    }

    public List<Object> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Object> discounts) {
        this.discounts = discounts;
    }

    public List<Integer> getColor() {
        return color;
    }

    public void setColor(List<Integer> color) {
        this.color = color;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getReferName() {
        return referName;
    }

    public void setReferName(String referName) {
        this.referName = referName;
    }

    public String getReferId() {
        return referId;
    }

    public void setReferId(String referId) {
        this.referId = referId;
    }
}
