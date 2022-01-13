package com.example.tutorial.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.ywbest.util.JsonToJavaCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class AutoCodingController {

    @GetMapping("/json")
    public String json(Model model) {
        model.addAttribute("username", "싸이언");
        return "board/convert";
    }

    @PostMapping("/json/convert")
    @ResponseBody
    public String boardCreate(@RequestParam Map<String, Object> param) throws IOException {
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
        String result = jsonToJavaCodeUtil.convertLinkedHashMapToJavaCode(listLinkedHashMap, "src/test/java/com/ywbest/util/test.txt", false);

        result = result.replaceAll("<", "&lt;"); // 치환 안해주면 textarea에서 인식 못하는 오류 발생
        result = result.replaceAll(">", "&gt;");

        return result;
    }

}
