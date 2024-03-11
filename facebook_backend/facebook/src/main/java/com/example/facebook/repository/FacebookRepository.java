package com.example.facebook.repository;

import com.example.facebook.entity.FacebookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacebookRepository extends JpaRepository<FacebookEntity, Long> {
}
