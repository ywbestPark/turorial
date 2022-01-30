package com.ywbest.crawler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class WebCrawler {
    public static final String URL_NAVER_NEWS = "https://finance.naver.com/item/news_news.nhn?code=";
    public static final String URL_NAVER_NEWS_NOTICE = "https://finance.naver.com/item/news_notice.nhn?code=";
    public static final String CSS_NAVER_NEWS = "table.type5 tr";
    public static final String CSS_NAVER_NEWS_NOTICE = "table.type6 tr";
    public static final String URL_NAVER_PRICE = "https://finance.naver.com/item/main.nhn?code=";
    public static final String CSS_NAVER_PRICE = "div#middle dl.blind dd";

    public void crawlWeb(String keyword, String dataType) throws IOException{
        if("price".equals(dataType)){
            String symbol = getSymbol(keyword);
            if(StringUtils.isNotEmpty(symbol)){
                DataHandler dataHandler = new DataHandler();
                List<String> priceInfo = getPriceInfoFromWeb(symbol);
                if(!dataHandler.exist(priceInfo)){
                    dataHandler.addPriceInfo(priceInfo);
                }
                dataHandler.close();
            }
        }
    }

    public List<String> getPriceInfoFromWeb(String symbol) throws IOException {
        Elements elements = getElements(symbol, URL_NAVER_PRICE, CSS_NAVER_PRICE);
        return findPriceInfoFromElements(elements);
    }

    private Elements getElements(String symbol, String url, String urlQuery) throws IOException {
        Document document = Jsoup.connect(url+symbol).get();
        return document.select(urlQuery);
    }

    private List<String> findPriceInfoFromElements(Elements elements) {
        List<String> params = new ArrayList<>();
        for(int i=0; i<elements.size(); i++){
            Element element = elements.get(i);
            String text = element.text();
            log.info(text);
            String[] split = text.split(" ");
            if(i==0){
                String date = split[0].replaceAll("년", "/")
                        + split[1].replaceAll("월", "/")
                        + split[2].replaceAll("일", "");
                params.add(date);
                continue;
            }
            if(i==4 || i==7 || i==9 || i>10){
                continue;
            }
            params.add(split[1]);
        }
        return params;
    }

    private String getSymbol(String keyword) {
        String symbol = "";
        switch (keyword){
            case "삼성전자":
                symbol = "005930";
                break;
            case "현대차":
                symbol = "005380";
                break;
            case "SK하이닉스":
                symbol = "000660";
                break;
            case "LG화학":
                symbol = "051910";
                break;
            case "NAVER":
                symbol = "035420";
                break;
            case "삼성바이오로직스":
                symbol = "207940";
                break;
            case "삼성SDI":
                symbol = "006400";
                break;
            case "셀트리온":
                symbol = "068270";
                break;
            default:
                break;
        }
        return symbol;
    }

}
