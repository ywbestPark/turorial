package com.example.tutorial.controller.project;

import com.example.tutorial.entity.YttPrice;
import com.example.tutorial.service.project.CrawlService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/project")
public class CrawlController {

    private final CrawlService crawlService;

    @GetMapping("/crawl_list_page")
    public String getUserListPage() throws Exception{
        return "project/crawl/crawlList";
    }

    @GetMapping("/crawl_list")
    public ResponseEntity<List<YttPrice>> getTourList() throws Exception{
        return ResponseEntity.ok(crawlService.getCrawlList());
    }

}
