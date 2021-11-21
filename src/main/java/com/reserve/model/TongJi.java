package com.reserve.model;

import lombok.Data;

@Data
public class TongJi {

    private String name;
    private Integer counts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }
}
