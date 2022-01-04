package com.anhdungpham.service;

import java.util.List;

import com.anhdungpham.dto.NewDTO;
import org.springframework.data.domain.Pageable;

public interface INewService {
    List<NewDTO> findAll(Pageable pageable);

    int getTotalItem();

    NewDTO findById(Long id);

    NewDTO insertNew(NewDTO dto);

    NewDTO updateNew(NewDTO dto);

    void delete(long[] ids);
}
