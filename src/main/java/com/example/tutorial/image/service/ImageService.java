package com.example.tutorial.image.service;

import com.example.tutorial.image.entity.ImageEntity;
import com.example.tutorial.image.repository.ImageRepository;
import com.example.tutorial.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.tutorial.exception.ErrorCode.NOT_FOUND;

@Slf4j
@Service
public class ImageService {

    @Autowired
    ImageRepository repository;

    public Page<ImageEntity> getAllImages(Pageable pageable){
        return repository.findAll(pageable);
    }

    public ImageEntity save(ImageEntity imageEntity) { return repository.save(imageEntity); };

    public ImageEntity findByID(Long id) {
        Optional<ImageEntity> optionalImageEntity = repository.findById(id);
        if(optionalImageEntity.isPresent()){
            ImageEntity imageEntity = optionalImageEntity.get();
            log.info(imageEntity.toString());
            return imageEntity;
        }else{
            throw new NotFoundException("Image not found.", NOT_FOUND);
        }
    }

    public ImageEntity update(ImageEntity imageEntity) {
        ImageEntity beforeImageEntity = this.findByID(imageEntity.getId());
        imageEntity.setImagePath(beforeImageEntity.getImagePath());
        return repository.save(imageEntity);
    }
}
