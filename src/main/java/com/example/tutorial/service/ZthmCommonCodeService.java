package com.example.tutorial.service;

import com.example.tutorial.entity.ZthmCommonCode;

import java.util.List;

public interface ZthmCommonCodeService {
    List<ZthmCommonCode> getCityCategoryCode(String codeGroupId) throws Exception;
    List<ZthmCommonCode> getCityCode(String codeGroupId, String pCodeId) throws Exception;
}
