package com.example.tutorial.image.controller;

import com.example.tutorial.image.entity.ImageEntity;
import com.example.tutorial.image.entity.Res;
import com.example.tutorial.image.service.ImageService;
import com.ywbest.message.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/")
    public String getView(Model model) {
        List<ImageEntity> imageEntities = imageService.getAllImages();
        model.addAttribute("items", imageEntities);

        return "image/image";
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam Map<String, Object> param,
                                                      MultipartHttpServletRequest multipartFiles) {
        log.info("requestUrl : /image/upload");
        log.info("requestParam {} ", param.toString());

        String message = "";

        Iterator<String> fileNames = multipartFiles.getFileNames();
        MultipartFile file = null;
        String fileName = "";
        while (fileNames.hasNext()) {
            try {
                fileName = fileNames.next();
                log.info("requestFile {} ", fileName);

                file = multipartFiles.getFile(fileName);


                String absolutePath = new File("").getAbsolutePath() + File.separator;
                log.error("absolutePath {} " + absolutePath);

                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                String current_date = now.format(dateTimeFormatter);
                //String path = "user-photos" + File.separator + current_date;
                String path = "user-photos";

                String originalFileExtension;
                String contentType = file.getContentType();
                if (ObjectUtils.isEmpty(contentType)) {
                    message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
                } else {  // 확장자가 jpeg, png인 파일들만 받아서 처리
                    if (contentType.contains("image/jpeg")) {
                        originalFileExtension = ".jpg";
                    } else if (contentType.contains("image/png")) {
                        originalFileExtension = ".png";
                    } else {  // 다른 확장자일 경우 처리 x
                        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
                    }
                }
                String new_file_name = System.nanoTime() + originalFileExtension;
                //File newFile = new File(absolutePath + path + File.separator + new_file_name);
                //File newFile = new File(new_file_name);
                File newFile = new File("D:/Users/ywbest/workspace/turorial/src/main/resources/static/img" + File.separator + new_file_name);
                System.out.println("bbbbbbbbbbbbbbbbbbbb " + "D:/Users/ywbest/workspace/turorial/src/main/resources/static/img" + File.separator + new_file_name);

                if (!newFile.exists()) {
                    boolean wasSuccessful = newFile.mkdirs();
                }

                file.transferTo(newFile);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();

                ImageEntity imageEntity = new ImageEntity(null, (String)param.get("title"), (String)param.get("description"), "/img/"+new_file_name);
                imageService.save(imageEntity);



            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }

        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    }

    @GetMapping("/lists")
    public ResponseEntity<Res> getAllImages() {
        Res res = new Res();
        try {
            List<ImageEntity> imageEntities = imageService.getAllImages();
            res.setItems(imageEntities);
            if (imageEntities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
