package com.anhdungpham.repository;

import com.anhdungpham.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
        CategoryEntity findOneByCode(String code);
}
