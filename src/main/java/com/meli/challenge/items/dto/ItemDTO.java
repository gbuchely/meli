package com.meli.challenge.items.dto;

import java.util.Date;
import java.util.List;

public class ItemDTO {

    private String itemId;
    private String title;
    private String categoryId;
    private String price;
    private Date startTime;
    private Date stopTime;

    private List<ChildrenDTO> children;

    public ItemDTO() {
    }

    public ItemDTO(String itemId, String title, String categoryId, String price, Date startTime, Date stopTime) {
        this.itemId = itemId;
        this.title = title;
        this.categoryId = categoryId;
        this.price = price;
        this.startTime = startTime;
        this.stopTime = stopTime;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public List<ChildrenDTO> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenDTO> children) {
        this.children = children;
    }
}

