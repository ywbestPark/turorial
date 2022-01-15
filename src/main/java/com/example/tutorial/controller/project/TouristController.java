package com.example.tutorial.controller.project;

import com.example.tutorial.dto.Tour.Item;
import com.example.tutorial.entity.ZthmCommonCode;
import com.example.tutorial.service.TourService;
import com.example.tutorial.service.ZthmCommonCodeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/project")
public class TouristController {
    private final TourService tourService;
    private final ZthmCommonCodeService zthmCommonCodeService;

    @GetMapping("/tour_list_page")
    public String getUserListPage() throws Exception{
        return "admin/tourList";
    }

    @GetMapping("/tour_list")
    public ResponseEntity<List<Item>> getTourList(@ModelAttribute("courseId") int courseId) throws Exception{
        log.info("courseId {} ", courseId);
        return ResponseEntity.ok(tourService.getTourList(courseId));
    }

    @GetMapping("/city_category_code")
    public ResponseEntity<List<ZthmCommonCode>> getCityCategoryCode(@ModelAttribute("codeGroupId") String codeGroupId) throws Exception{
        log.info("codeGroupId {} ", codeGroupId);
        return ResponseEntity.ok(zthmCommonCodeService.getCityCategoryCode(codeGroupId));
    }

    @GetMapping("/city_code")
    public ResponseEntity<List<ZthmCommonCode>> getCityCategoryCode(@ModelAttribute("codeGroupId") String codeGroupId
    , @ModelAttribute("pCodeId") String pCodeId) throws Exception{
        log.info("codeGroupId {} ", codeGroupId);
        log.info("pCodeId {} ", pCodeId);
        return ResponseEntity.ok(zthmCommonCodeService.getCityCode(codeGroupId, pCodeId));
    }
}
