package com.lin.liuhe.service;

import java.util.List;

import com.lin.liuhe.pojo.Log;

public interface ManagerLogService {

	void saveCurrentLog(Log log);

	void createTableLog(String createTableLog1);

	List<Log> getAllLogList(List<String> tableNameList);

	List<String> getLogAllTable(String dataBaseName);

}
