package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserListEntity;

@Repository
public interface UserListRepository extends JpaRepository<UserListEntity, Integer> {

}