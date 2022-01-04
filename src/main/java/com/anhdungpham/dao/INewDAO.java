package com.anhdungpham.dao;

import java.util.List;

import com.anhdungpham.model.NewModel;
import com.anhdungpham.paging.Pageble;

public interface INewDAO extends GenericDAO<NewModel>{
	List<NewModel> findAll(Pageble pageble);
	int getTotalItem();
}
