package com.anhdungpham.dao.imple;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.anhdungpham.dao.INewDAO;
import com.anhdungpham.mapper.NewMapper;
import com.anhdungpham.model.NewModel;
import com.anhdungpham.paging.Pageble;

@Repository
public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {

	@Override
	public List<NewModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM news");
		if (pageble.getSorter() != null ) {
			sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");			
		} 
		if (pageble.getOffSet() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT " + pageble.getOffSet() + " " + ", " + pageble.getLimit()+ "");			
		} 			
		return query(sql.toString(), new NewMapper());				
	}

	@Override
	public int getTotalItem() {
		StringBuilder sql = new StringBuilder("SELECT count(*) FROM news");
		return count(sql.toString());
	}

	
}
