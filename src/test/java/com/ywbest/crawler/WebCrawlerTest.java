package com.ywbest.crawler;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class WebCrawlerTest {

    WebCrawler webCrawler;

    @Before
    public void init(){
        webCrawler = new WebCrawler();
    }

    @Test
    public void crawlWeb() throws IOException {
        webCrawler.crawlWeb("삼성전자", "price");
    }
}