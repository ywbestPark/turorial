package com.example.tutorial.excel.repository;

import com.example.tutorial.excel.entity.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
}
