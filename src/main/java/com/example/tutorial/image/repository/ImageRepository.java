package com.example.tutorial.image.repository;

import com.example.tutorial.image.entity.ImageEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    List<ImageEntity> findAll(Sort sort);
}
