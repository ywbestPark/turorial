package com.example.tutorial.service.project;

import com.example.tutorial.dto.project.AutoCodingControllerDTO;

import java.util.LinkedHashMap;
import java.util.List;

public interface AutoCodingService {
    LinkedHashMap<String, List<String>> convertController(AutoCodingControllerDTO autoCodingControllerDTO) throws Exception;
}
