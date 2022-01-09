package com.example.tutorial.dto.Tour;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item{
    private String tm;
    private String thema;
    private String courseId;
    private String courseAreaId;
    private String courseAreaName;
    private String courseName;
    private int spotAreaId;
    private String spotAreaName;
    private String spotName;
    private int th3;
    private int wd;
    private int ws;
    private int sky;
    private int rhm;
    private int pop;
}
