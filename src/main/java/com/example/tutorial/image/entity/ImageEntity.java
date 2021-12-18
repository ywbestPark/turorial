package com.example.tutorial.image.entity;

import com.example.tutorial.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "imageList")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageEntity extends BaseTimeEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "imagePath")
    private String imagePath;
}
