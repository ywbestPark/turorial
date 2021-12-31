package com.example.tutorial.image.controller;

import com.example.tutorial.image.entity.ImageEntity;
import com.example.tutorial.image.service.ImageService;
import com.example.tutorial.user.dto.CustomOAuth2User;
import com.ywbest.message.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@Slf4j
@RequestMapping("/image")
public class ImageController {
    @Value("${spring.servlet.multipart.location}")
    private String multiPathPath;

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("")
    public String getView(Model model, final Pageable pageable) {
        Page<ImageEntity> imageEntities = imageService.getAllImages(pageable);
        model.addAttribute("items", imageEntities);

        List<Map<String, Integer>> pageIndexList = new ArrayList<>();
        Map<String, Integer> pageIndexMap;
        for (int i=0; i<imageEntities.getTotalPages(); i++){
            pageIndexMap = new HashMap<>();
            pageIndexMap.put("index", i);
            pageIndexMap.put("page", i+1);
            pageIndexList.add(pageIndexMap);
        }
        model.addAttribute("pageIndex", pageIndexList);

        return "image/image";
    }

    @GetMapping("/{id}")
    public String imageUpdateView(@PathVariable("id") Long id, Model model){

        log.info("requested update ID : ",id+"");

        ImageEntity imageEntity = imageService.findByID(id);

        model.addAttribute("items", imageEntity);

        return "image/update";
    }

    @PutMapping("")
    @ResponseBody
    public ImageEntity imageUpdate(@RequestBody ImageEntity imageEntity){

        log.info("requested imageEntity {} ",imageEntity.toString());

        ImageEntity resultImageEntity = imageService.update(imageEntity);

        return resultImageEntity;
    }

    @PostMapping("/save")
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

                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                String current_date = now.format(dateTimeFormatter);

                String originalFileExtension;
                String contentType = file.getContentType();
                if (ObjectUtils.isEmpty(contentType)) {
                    message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
                } else {

                    Tika tika = new Tika();
                    String mimeType = tika.detect(file.getInputStream());
                    log.info("tikaMimeType {} : "+mimeType);
                    originalFileExtension = MimeTypeUtils.parseMimeType(mimeType).getSubtype();
                    originalFileExtension = "."+originalFileExtension;
                    log.info("originalFileExtension {} : "+originalFileExtension);
                }
                String new_file_name = current_date + "/" + System.nanoTime() + originalFileExtension;
                File newFile = new File(multiPathPath + new_file_name);
                if (!newFile.exists()) {
                    boolean wasSuccessful = newFile.mkdirs();
                }

                file.transferTo(newFile);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();

                CustomOAuth2User customOAuth2User = null;
                if(SecurityContextHolder.getContext().getAuthentication().getPrincipal()!=null){
                    if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof CustomOAuth2User){
                        customOAuth2User = (CustomOAuth2User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    }
                }

                ImageEntity imageEntity = ImageEntity.builder()
                                                .title((String)param.get("title"))
                                                .description((String)param.get("description"))
                                                .imagePath("/user-photos/" + new_file_name)
                                                .userImagePath(customOAuth2User.getPicture())
                                                .build();

                imageService.save(imageEntity);

            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }

        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    }
}
