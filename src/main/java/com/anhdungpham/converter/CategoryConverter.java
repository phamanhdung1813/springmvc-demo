package com.anhdungpham.converter;

import com.anhdungpham.dto.CategoryDTO;
import com.anhdungpham.dto.NewDTO;
import com.anhdungpham.entity.CategoryEntity;
import com.anhdungpham.entity.NewEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {
    public CategoryDTO toDto(CategoryEntity entity) {
        CategoryDTO result = new CategoryDTO();
        result.setId(entity.getId());
        result.setCode(entity.getCode());
        result.setName(entity.getName());
        return result;
    }

    public CategoryEntity toEntity(CategoryDTO dto) {
        CategoryEntity result = new CategoryEntity();
        result.setCode(dto.getCode());
        result.setName(dto.getName());
        return result;
    }
}
