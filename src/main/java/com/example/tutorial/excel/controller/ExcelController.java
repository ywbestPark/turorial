package com.example.tutorial.excel.controller;

import com.example.tutorial.excel.entity.Tutorial;
import com.example.tutorial.excel.service.ExcelService;
import com.ywbest.message.ResponseMessage;
import com.ywbest.util.ExcelHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:8081")
@Controller
@RequestMapping("/excel")
@Slf4j
public class ExcelController {

    @Autowired
    ExcelService fileService;

    @GetMapping("/view")
    public String view(){
        log.info("reqUrl : /excel/view");
        return "excel/excelView";
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam Map<String, Object> param,
                                                      MultipartHttpServletRequest multipartFiles) {
        log.info("requestUrl : /excel/upload");
        log.info("requestParam {} ", param.toString());

        String message = "";

        Iterator<String> fileNames = multipartFiles.getFileNames();
        MultipartFile file = null;
        String fileName = "";
        while (fileNames.hasNext()){
            fileName = fileNames.next();
            log.info("requestFile {} ", fileName);

            file = multipartFiles.getFile(fileName);
            if (ExcelHelper.hasExcelFormat(file)) {
                try {
                    fileService.save(file);
                    message = "Uploaded the file successfully: " + file.getOriginalFilename();
                } catch (Exception e) {
                    message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
                }
            }else{
                message = "Please upload an excel file!";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

//        for(MultipartFile file : multipartFiles) {
//            if (ExcelHelper.hasExcelFormat(file)) {
//                try {
//                    fileService.save(file);
//
//                    message = "Uploaded the file successfully: " + file.getOriginalFilename();
//                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//                } catch (Exception e) {
//                    message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//                    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//                }
//            }
//        }


    }

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials() {
        try {
            List<Tutorial> tutorials = fileService.getAllTutorials();

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> getFile() {
        String filename = "tutorials.xlsx";
        InputStreamResource file = new InputStreamResource(fileService.load());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }
}
