package com.example.tutorial.repository;

import com.example.tutorial.entity.ZthmPage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZthmPageRepository extends JpaRepository<ZthmPage, Long> {
    List<ZthmPage> findByRoleIdAndIsEnable(String roleId, boolean isEnable);
}