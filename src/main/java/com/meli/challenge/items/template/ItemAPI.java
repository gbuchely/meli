package com.meli.challenge.items.template;

import java.util.Date;

public class ItemAPI {

    private String id;
    private String title;
    private String category_id;
    private Double price;
    private Date start_time;
    private Date stop_time;

    @Override
    public String toString(){
        return
            "\n" + "ID: " + id +
            "\n" +"TITLE: " + title +
            "\n" +"CATEGORY ID: " + category_id +
            "\n" +"PRICE: " + price +
            "\n" +"START TIME: " + start_time +
            "\n" +"STOP TIME: " + stop_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getStop_time() {
        return stop_time;
    }

    public void setStop_time(Date stop_time) {
        this.stop_time = stop_time;
    }
}
