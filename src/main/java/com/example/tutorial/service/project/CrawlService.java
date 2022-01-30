package com.example.tutorial.service.project;

import com.example.tutorial.entity.YttPrice;

import java.util.List;

public interface CrawlService {

    List<YttPrice> getCrawlList() throws Exception;
}
