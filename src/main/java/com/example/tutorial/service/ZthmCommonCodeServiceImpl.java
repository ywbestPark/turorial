package com.example.tutorial.service;

import com.example.tutorial.entity.ZthmCommonCode;
import com.example.tutorial.repository.ZthmCommonCodeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ZthmCommonCodeServiceImpl implements ZthmCommonCodeService {
    private final ZthmCommonCodeRepository zthmCommonCodeRepository;


    @Override
    public List<ZthmCommonCode> getCityCategoryCode(String codeGroupId) throws Exception {
        return zthmCommonCodeRepository.findByCodeGroupIdAndIsEnable(codeGroupId, true);
    }

    @Override
    public List<ZthmCommonCode> getCityCode(String codeGroupId, String pCodeId) throws Exception {
        return zthmCommonCodeRepository.findByCodeGroupIdAndIsEnableAndPCodeId(codeGroupId, true, pCodeId);
    }
}
