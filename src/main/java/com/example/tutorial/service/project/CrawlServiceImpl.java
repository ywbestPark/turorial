package com.example.tutorial.service.project;

import com.example.tutorial.entity.YttPrice;
import com.example.tutorial.repository.YttPriceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CrawlServiceImpl implements CrawlService {
    private final YttPriceRepository yttPriceRepository;
    @Override
    public List<YttPrice> getCrawlList() throws Exception {
        return yttPriceRepository.findAll();
    }
}
