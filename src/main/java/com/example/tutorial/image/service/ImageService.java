package com.example.tutorial.image.service;

import com.example.tutorial.image.entity.ImageEntity;
import com.example.tutorial.image.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.tutorial.entity.BaseTimeEntity.SORT_BY_CREATED_DATE_DESC;

@Service
public class ImageService {

    @Autowired
    ImageRepository repository;

    public List<ImageEntity> getAllImages(){
        return repository.findAll(SORT_BY_CREATED_DATE_DESC);
    }
    public ImageEntity save(ImageEntity imageEntity) { return repository.save(imageEntity); };
}
