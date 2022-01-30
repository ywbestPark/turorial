package com.example.tutorial.scheduler;

import com.example.tutorial.entity.PriceId;
import com.example.tutorial.entity.YttPrice;
import com.example.tutorial.entity.ZthmCommonCode;
import com.example.tutorial.repository.YttPriceRepository;
import com.example.tutorial.repository.ZthmCommonCodeRepository;
import com.ywbest.crawler.WebCrawler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@EnableAsync
@RequiredArgsConstructor
public class TradingScheduler {
    private final YttPriceRepository yttPriceRepository;
    private final ZthmCommonCodeRepository zthmCommonCodeRepository;

    /** * Cron 표현식을 사용한 작업 예약 * 초(0-59) 분(0-59) 시간(0-23) 일(1-31) 월(1-12) 요일(0-7) */
    @Scheduled(cron = "0 0 9-16 * * 0-5")
    //@Scheduled(fixedRate = 1000*60*60)//60분마다 수행
    public void scheduleTaskUsingCronExpression() {
        log.info("########## TradingScheduler Started ##########");

        WebCrawler webCrawler = new WebCrawler();
        String symbol = "";
        List<ZthmCommonCode> zthmCommonCodeList = zthmCommonCodeRepository.findByCodeGroupIdAndIsEnable("price", true);
        for (ZthmCommonCode zthmCommonCode : zthmCommonCodeList){
            symbol = zthmCommonCode.getCodeId();
            try {
                List<String> priceInfo = webCrawler.getPriceInfoFromWeb(symbol);
                log.info("Price Info {}", priceInfo);
                PriceId priceId = new PriceId();
                priceId.setDate(priceInfo.get(0));
                priceId.setSymbol(priceInfo.get(2));
//            Optional<YttPrice> yttPriceOptional = yttPriceRepository.findById(priceId);
//            if(yttPriceOptional.isPresent()){
//                log.info("price information exists");
//            }else{
//                YttPrice yttPrice = new YttPrice();
//                yttPrice.setPriceId(priceId);
//                yttPrice.setName(priceInfo.get(2));
//                yttPrice.setClose(priceInfo.get(3));
//                yttPrice.setOpen(priceInfo.get(4));
//                yttPrice.setHigh(priceInfo.get(5));
//                yttPrice.setLow(priceInfo.get(6));
//                yttPrice.setVolume(priceInfo.get(7));
//                yttPriceRepository.save(yttPrice);
//            }
                YttPrice yttPrice = new YttPrice();
                yttPrice.setPriceId(priceId);
                yttPrice.setName(priceInfo.get(1));
                yttPrice.setClose(priceInfo.get(3));
                yttPrice.setOpen(priceInfo.get(4));
                yttPrice.setHigh(priceInfo.get(5));
                yttPrice.setLow(priceInfo.get(6));
                yttPrice.setVolume(priceInfo.get(7));
                yttPriceRepository.save(yttPrice);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("########## TradingScheduler Ended ##########");
    }
}
