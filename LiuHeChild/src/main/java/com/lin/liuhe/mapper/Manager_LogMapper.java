package com.lin.liuhe.mapper;

import java.util.List;

import com.lin.liuhe.pojo.Log;

public interface Manager_LogMapper {

	List<String> selectLogAllTable(String dataBaseName);

	List<Log> getAllLogList(List<String> tableNameList);

	void creataTableLog(String createTableLog1);

	void insert(Log log, String createTableLog);

}
