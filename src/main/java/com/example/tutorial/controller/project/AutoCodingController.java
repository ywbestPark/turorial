package com.example.tutorial.controller.project;

import com.example.tutorial.dto.project.AutoCodingControllerDTO;
import com.example.tutorial.service.project.AutoCodingService;
import com.fasterxml.jackson.databind.JsonNode;
import com.ywbest.util.JsonToJavaCodeUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@AllArgsConstructor
public class AutoCodingController {

    private final AutoCodingService autoCodingService;

    @GetMapping("/json")
    public String json(Model model) {
        model.addAttribute("username", "싸이언");
        return "project/autogen/dto";
    }

    @GetMapping("/project/auto_generation_controller")
    public String getAutoGenerationControllerPage(Model model) {
        model.addAttribute("username", "싸이언");
        return "project/autogen/controller";
    }

    @PostMapping("/json/convert")
    @ResponseBody
    public LinkedHashMap<String, List<String>> convertDTO(@RequestParam Map<String, Object> param) throws IOException {
        log.info(param.toString());
        log.info(param.get("jsonString").toString());

        JsonToJavaCodeUtil jsonToJavaCodeUtil = new JsonToJavaCodeUtil();

        String inputString = param.get("jsonString").toString();
        JsonNode jsonNode;
        if(inputString.startsWith("<")){
            jsonNode = jsonToJavaCodeUtil.convertXmlStringToJsonNode(inputString);
        }else{
            jsonNode = jsonToJavaCodeUtil.convertJsonStringToJsonNode(inputString);
        }
        LinkedHashMap<String, List<String>> listLinkedHashMap = jsonToJavaCodeUtil.convertJsonNodeToMap(jsonNode);

        return listLinkedHashMap;
    }

    @PostMapping(value = "/project/auto_generation_controller", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public LinkedHashMap<String, List<String>> convertControllerFromJsonRequest(@RequestBody AutoCodingControllerDTO autoCodingControllerDTO) throws Exception {
        log.info("inputDTO : ", autoCodingControllerDTO.toString());
        LinkedHashMap<String, List<String>> result = autoCodingService.convertController(autoCodingControllerDTO);
        return result;
    }

//    @PostMapping(value = "/project/auto_generation_controller", consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public LinkedHashMap<String, List<String>> convertControllerFromJsonRequest(@RequestBody Map<String, Object> param) throws IOException {
//        log.info(param.toString());
//        return null;
//    }

    @PostMapping(value = "/project/auto_generation_controller", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public LinkedHashMap<String, List<String>> convertControllerFromFormRequest(@RequestParam Map<String, Object> param) throws IOException {
        log.info(param.toString());
        return null;
    }

}
