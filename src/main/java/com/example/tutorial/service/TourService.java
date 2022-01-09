package com.example.tutorial.service;

import com.example.tutorial.dto.Tour.Item;

import java.util.List;

public interface TourService {
    List<Item> getTourList(int courseId) throws Exception;
}
