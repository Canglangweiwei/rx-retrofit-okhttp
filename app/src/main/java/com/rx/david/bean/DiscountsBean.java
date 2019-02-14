package com.rx.david.bean;

import java.io.Serializable;
import java.util.List;

public class DiscountsBean implements Serializable {
    private static final long serialVersionUID = 4319886144087046765L;

    // 折扣类型名称
    private String name;
    // 图标
    private String iconUrl;
    // 颜色
    private List<Integer> color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public List<Integer> getColor() {
        return color;
    }

    public void setColor(List<Integer> color) {
        this.color = color;
    }
}
