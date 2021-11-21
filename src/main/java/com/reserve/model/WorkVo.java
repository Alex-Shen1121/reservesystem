package com.reserve.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class WorkVo {
    @JsonFormat(locale = "zh",timezone = "GMT+8" ,pattern = "yyyy-MM-dd")
    Date day;
    String title;
    String color;
    String dayTime;
    Integer userId;
}
