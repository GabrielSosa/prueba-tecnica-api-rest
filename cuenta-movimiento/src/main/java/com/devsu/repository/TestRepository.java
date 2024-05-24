package com.devsu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devsu.domain.TestEntity;

public interface TestRepository extends JpaRepository<TestEntity, Long> {
}
