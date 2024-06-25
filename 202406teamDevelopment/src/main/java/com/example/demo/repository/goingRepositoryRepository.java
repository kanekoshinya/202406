package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.goingRepositoryEntity;

@Repository
public interface goingRepositoryRepository extends JpaRepository<goingRepositoryEntity, Integer> {
}