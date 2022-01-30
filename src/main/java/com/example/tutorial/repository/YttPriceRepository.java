package com.example.tutorial.repository;

import com.example.tutorial.entity.PriceId;
import com.example.tutorial.entity.YttPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YttPriceRepository extends JpaRepository<YttPrice, PriceId> {
}
