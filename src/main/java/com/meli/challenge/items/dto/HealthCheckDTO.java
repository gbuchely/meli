package com.meli.challenge.items.dto;

import java.util.Date;
import java.util.Map;


public class HealthCheckDTO {

    private Date date;
    private Double avgResponseTime;
    private Integer totalRequest;
    private Double avgResponseTimeApiCalls;
    private Integer totalCountApiCalls;
    private Map<String, Integer> infoRequest;

    public HealthCheckDTO() {
    }

    public HealthCheckDTO(Date date, Double avgResponseTime, Integer totalRequest, Double avgResponseTimeApiCalls, Integer totalCountApiCalls, Map<String, Integer> infoRequest) {
        this.date = date;
        this.avgResponseTime = avgResponseTime;
        this.totalRequest = totalRequest;
        this.avgResponseTimeApiCalls = avgResponseTimeApiCalls;
        this.totalCountApiCalls = totalCountApiCalls;
        this.infoRequest = infoRequest;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAvgResponseTime() {
        return avgResponseTime;
    }

    public void setAvgResponseTime(Double avgResponseTime) {
        this.avgResponseTime = avgResponseTime;
    }

    public Integer getTotalRequest() {
        return totalRequest;
    }

    public void setTotalRequest(Integer totalRequest) {
        this.totalRequest = totalRequest;
    }

    public Double getAvgResponseTimeApiCalls() {
        return avgResponseTimeApiCalls;
    }

    public void setAvgResponseTimeApiCalls(Double avgResponseTimeApiCalls) {
        this.avgResponseTimeApiCalls = avgResponseTimeApiCalls;
    }

    public Integer getTotalCountApiCalls() {
        return totalCountApiCalls;
    }

    public void setTotalCountApiCalls(Integer totalCountApiCalls) {
        this.totalCountApiCalls = totalCountApiCalls;
    }

    public Map<String, Integer> getInfoRequest() {
        return infoRequest;
    }

    public void setInfoRequest(Map<String, Integer> infoRequest) {
        this.infoRequest = infoRequest;
    }
}
