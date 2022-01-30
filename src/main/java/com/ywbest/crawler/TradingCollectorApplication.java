package com.ywbest.crawler;

import java.io.IOException;

public class TradingCollectorApplication {
    public static void main(String[] args) throws IOException {

        /**
         * 1. 정보 수집 기능
         *   - args[0] : crawler
         *   - args[1] : 키워드 / ex) 삼성전자
         *   - args[2] : 정보 유형 / price or news
         *   - ex) crawler 삼성전자 price
         */

        String type = args[0];
        if("crawler".equalsIgnoreCase(type)){
            String keyword = args[1];
            String dataType = args[2];
            WebCrawler webCrawler = new WebCrawler();
            webCrawler.crawlWeb(keyword, dataType);
        }
    }
}
