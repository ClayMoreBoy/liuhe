package com.lin.ssh.service;

import java.util.List;

import com.lin.ssh.bean.Lin;


public interface LinService {
	public abstract void saveData(Lin lin);
	public abstract List<Lin> getData(String tableName);
	public abstract void updateData(Lin lin);
	public abstract void deleteData(Lin lin);
	public abstract void deleteData(int id);
}
