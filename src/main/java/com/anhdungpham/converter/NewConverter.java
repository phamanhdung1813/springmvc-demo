package com.anhdungpham.converter;

import com.anhdungpham.dto.NewDTO;
import com.anhdungpham.entity.NewEntity;
import org.springframework.stereotype.Component;

@Component
//embed to service
public class NewConverter {
    public NewDTO toDto(NewEntity newEntity) {
        NewDTO result = new NewDTO();
        result.setId(newEntity.getId());
        result.setTitle(newEntity.getTitle());
        result.setShortDescription(newEntity.getShortDescription());
        result.setContent(newEntity.getContent());
        result.setThumbnail(newEntity.getThumbnail());
        result.setCategoryCode(newEntity.getCategory().getCode()); //NewEntity is foreign key to CategoryEntity => getCagetgory() is an object on CategoryEntiry
        return result;
    }

    public NewEntity toEntity(NewDTO dto) {
        NewEntity result = new NewEntity();
        result.setTitle(dto.getTitle());
        result.setShortDescription(dto.getShortDescription());
        result.setContent(dto.getContent());
        result.setThumbnail(dto.getThumbnail());
        return result;
    }
}
