package com.example.tutorial.repository;

import com.example.tutorial.entity.ZthmCommonCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZthmCommonCodeRepository extends JpaRepository<ZthmCommonCode, Long> {
    List<ZthmCommonCode> findByCodeGroupIdAndIsEnable(String codeGroupId, boolean isEnable);
    List<ZthmCommonCode> findByCodeGroupIdAndIsEnableAndPCodeId(String codeGroupId, boolean isEnable, String PCodeId);
}