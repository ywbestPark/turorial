package com.example.tutorial.service.project;

import com.example.tutorial.dto.project.AutoCodingControllerDTO;
import com.ywbest.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class AutoCodingServiceImpl implements AutoCodingService {
    @Override
    public LinkedHashMap<String, List<String>> convertController(AutoCodingControllerDTO autoCodingControllerDTO) throws Exception {
        LinkedHashMap<String, List<String>> listLinkedHashMap = new LinkedHashMap<>();
        List<String> stringList = new ArrayList<>();
        listLinkedHashMap.put(autoCodingControllerDTO.getClassName(), stringList);
        StringBuilder sb = new StringBuilder();

        sb.append(StringUtil.addLineWithTabWithEnter("package  "+autoCodingControllerDTO.getPackageName()+";", 0));
        sb.append(StringUtil.addLineWithTabWithEnter("", 0));

        String[] injectsArray = autoCodingControllerDTO.getInjections().split(",");
        for(String inject : injectsArray){
            sb.append(StringUtil.addLineWithTabWithEnter("import "+autoCodingControllerDTO.getPackageName()+".service."+inject+";", 0));
        }
        sb.append(StringUtil.addLineWithTabWithEnter("import lombok.AllArgsConstructor;", 0));
        sb.append(StringUtil.addLineWithTabWithEnter("import lombok.extern.slf4j.Slf4j;", 0));
        sb.append(StringUtil.addLineWithTabWithEnter("import org.springframework.http.ResponseEntity;", 0));
        sb.append(StringUtil.addLineWithTabWithEnter("import org.springframework.stereotype.Controller;", 0));
        sb.append(StringUtil.addLineWithTabWithEnter("import org.springframework.web.bind.annotation.GetMapping;", 0));
        sb.append(StringUtil.addLineWithTabWithEnter("import org.springframework.web.bind.annotation.ModelAttribute;", 0));
        sb.append(StringUtil.addLineWithTabWithEnter("import org.springframework.web.bind.annotation.RequestMapping;", 0));
        sb.append(StringUtil.addLineWithTabWithEnter("", 0));
        sb.append(StringUtil.addLineWithTabWithEnter("@Controller", 0));
        sb.append(StringUtil.addLineWithTabWithEnter("@AllArgsConstructor", 0));
        sb.append(StringUtil.addLineWithTabWithEnter("@Slf4j", 0));
        sb.append(StringUtil.addLineWithTabWithEnter("@RequestMapping(\""+autoCodingControllerDTO.getRequestMapping()+"\")", 0));
        sb.append(StringUtil.addLineWithTabWithEnter("public class "+autoCodingControllerDTO.getClassName()+"Controller {", 0));
        for(String inject : injectsArray){
            sb.append(StringUtil.addLineWithTabWithEnter("private final "+inject+" "+inject+";", 1));
        }
        sb.append(StringUtil.addLineWithTabWithEnter("", 0));
        sb.append(StringUtil.addLineWithTabWithEnter("@GetMapping(\"/get_list_page\")", 1));
        sb.append(StringUtil.addLineWithTabWithEnter("public String getListPage() throws Exception{", 1));
        sb.append(StringUtil.addLineWithTabWithEnter("return \"admin/tourList\";", 2));
        sb.append(StringUtil.addLineWithTabWithEnter("}", 1));
        sb.append(StringUtil.addLineWithTabWithEnter("", 0));
        sb.append(StringUtil.addLineWithTabWithEnter("}", 0));

        stringList.add(sb.toString());
        return listLinkedHashMap;
    }
}
