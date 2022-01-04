package com.anhdungpham.service.imple;

import com.anhdungpham.converter.CategoryConverter;
import com.anhdungpham.dto.CategoryDTO;
import com.anhdungpham.entity.CategoryEntity;
import com.anhdungpham.repository.CategoryRepository;
import com.anhdungpham.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    public Map<String, String> findAll() {
        Map<String,String> result = new HashMap<>();
        List<CategoryEntity> entities = categoryRepository.findAll();
        for (CategoryEntity item: entities) {
            // Mapper in jdbc (entity - dto)
            result.put(item.getCode(), item.getName());
        }
        return result;
    }
}
