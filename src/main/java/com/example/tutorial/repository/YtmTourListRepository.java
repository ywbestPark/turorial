package com.example.tutorial.repository;

import com.example.tutorial.entity.YtmTourList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YtmTourListRepository extends JpaRepository<YtmTourList, Long> {
}