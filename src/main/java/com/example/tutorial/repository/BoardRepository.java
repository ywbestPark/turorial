package com.example.tutorial.repository;

import com.example.tutorial.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
