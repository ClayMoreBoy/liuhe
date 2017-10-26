package com.lin.ssh.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lin.ssh.bean.Lin;
import com.lin.ssh.dao.BaseDao;
import com.lin.ssh.service.LinService;
@Service
public class LinServiceImpl extends BaseDao<Lin> implements LinService {
	
	@Override
	public void saveData(Lin lin) {
		save(lin);
	}

	@Override
	public List<Lin> getData(String tableName) {
		return getAll(tableName);
	}

	@Override
	public void updateData(Lin lin) {
		update(lin);
	}

	@Override
	public void deleteData(Lin lin) {
		delete(lin);
	}

	@Override
	public void deleteData(int id) {
		delete(id);
	}

}
