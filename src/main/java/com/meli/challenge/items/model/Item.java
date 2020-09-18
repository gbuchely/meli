package com.meli.challenge.items.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @Column(name = "item_id")
    private String itemId;

    @OneToMany(targetEntity = Children.class, mappedBy = "childrenId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Children> children;

    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "stop_time")
    private Date stopTime;

    @Column(name = "last_modification")
    private Date lastModification;

    private String title;
    private String price;

    public Item() {
    }

    public Item(String itemId, List<Children> children, String categoryId, Date startTime, Date stopTime, Date lastModification, String title, String price) {
        this.itemId = itemId;
        this.children = children;
        this.categoryId = categoryId;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.lastModification = lastModification;
        this.title = title;
        this.price = price;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public List<Children> getChildren() {
        return children;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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

    public Date getLastModification() {
        return lastModification;
    }

    public void setLastModification(Date lastModification) {
        this.lastModification = lastModification;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
