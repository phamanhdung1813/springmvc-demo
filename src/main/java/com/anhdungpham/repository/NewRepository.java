package com.anhdungpham.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.anhdungpham.entity.NewEntity;


public interface NewRepository extends JpaRepository<NewEntity, Long> {

}
