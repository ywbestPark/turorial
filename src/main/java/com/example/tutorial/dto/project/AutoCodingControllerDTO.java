package com.example.tutorial.dto.project;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
public class AutoCodingControllerDTO {
    private String packageName;
    private String className;
    private String injections;
    private String methodMapping;
    private boolean requestBodyCheck;
    private String requestMapping;
    private boolean requestModelAttributeCheck;
    private boolean requestParamCheck;
    private boolean requestPathCheck;
    private String responseParameter;
}
