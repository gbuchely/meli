package com.meli.challenge.items.dto;

import java.util.Date;

public class ChildrenDTO {

    private String itemId;
    private Date stopTime;

    public ChildrenDTO() {
    }

    public ChildrenDTO(String itemId, Date stopTime) {
        this.itemId = itemId;
        this.stopTime = stopTime;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }
}
