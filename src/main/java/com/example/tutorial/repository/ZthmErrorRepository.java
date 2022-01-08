package com.example.tutorial.repository;

import com.example.tutorial.entity.ZthmError;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZthmErrorRepository extends JpaRepository<ZthmError, Long> {
}