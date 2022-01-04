package com.anhdungpham.service.imple;

import com.anhdungpham.converter.NewConverter;
import com.anhdungpham.dto.NewDTO;
import com.anhdungpham.entity.CategoryEntity;
import com.anhdungpham.entity.NewEntity;
import com.anhdungpham.repository.CategoryRepository;
import org.springframework.data.domain.Pageable;
import com.anhdungpham.repository.NewRepository;
import com.anhdungpham.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewService implements INewService {

	@Autowired
	private NewRepository newRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private NewConverter newConverter;

	@Override
	public List<NewDTO> findAll(Pageable pageable) {
		List<NewDTO> models = new ArrayList<>();
		List<NewEntity> newEntities = newRepository.findAll(pageable).getContent();

		//Entity is a ResultSet in jdbc
		for (NewEntity item: newEntities) {
			// Mapper in jdbc (entity - dto)
			NewDTO newDTO = newConverter.toDto(item);
			models.add(newDTO);
		}
		return models;
	}

	@Override
	public int getTotalItem() {
		return (int) newRepository.count();
	}

	@Override
	public NewDTO findById(Long id) {
		NewEntity entity = newRepository.findOne(id);
		return newConverter.toDto(entity);
	}

	@Override
	@Transactional
	public NewDTO insertNew(NewDTO newDto) {
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDto.getCategoryCode());
		NewEntity newEntity = newConverter.toEntity(newDto);
		newEntity.setCategory(categoryEntity);
		newEntity = newRepository.save(newEntity);
		return newConverter.toDto(newEntity);
	}

	@Override
	@Transactional
	public NewDTO updateNew(NewDTO dto) {
		NewEntity oldNew = newRepository.findOne(dto.getId());
		oldNew.setCategory(categoryRepository.findOneByCode(dto.getCategoryCode()));
		oldNew.setTitle(dto.getTitle());
		oldNew.setContent(dto.getContent());
		oldNew.setShortDescription(dto.getShortDescription());
		oldNew = newRepository.save(oldNew);
		return newConverter.toDto(oldNew);
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for (long id: ids) {
			newRepository.delete(id);
		}
	}

}
