package com.meli.challenge.items.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "children")
public class Children {

    @Id
    @Column(name = "children_id")
    private String childrenId;

    @Column(name = "stop_time")
    private Date stopTime;

    @ManyToOne
    @JoinColumn(name = "item_id")
    public Item itemId;

    public Children() {
    }

    public Children(String childrenId, Date stopTime, Item itemId) {
        this.childrenId = childrenId;
        this.stopTime = stopTime;
        this.itemId = itemId;
    }

    public String getChildrenId() {
        return childrenId;
    }

    public void setChildrenId(String childrenId) {
        this.childrenId = childrenId;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }
}