package com.example.tutorial.dto.Tour;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Body{
    private String dataType;
    private Items items;
    private int pageNo;
    private int numOfRows;
    private int totalCount;
}