package com.lin.liuhe.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lin.liuhe.mapper.Manager_LogMapper;
import com.lin.liuhe.pojo.Log;
import com.lin.liuhe.service.ManagerLogService;
import com.lin.liuhe.utils.QuarthCreateTableLogUtils;

@Service
public class ManagerLogServiceImpl implements ManagerLogService {
	@Autowired(required=false)
	Manager_LogMapper manager_LogMapper;
	/**
	 * 保存日志
	 */
	@Override
	public void saveCurrentLog(Log log) {
		//获得当前当前当月的数据名
		String createTableLog = QuarthCreateTableLogUtils.createTableLog(0);
		//保存数据
		manager_LogMapper.insert(log,createTableLog);
	}
	/**
	 * 建立日志表
	 */
	@Override
	public void createTableLog(String createTableLog1) {
		manager_LogMapper.creataTableLog(createTableLog1);
	}
	/**
	 * 获取所有日志数据表的数据
	 */
	@Override
	public List<Log> getAllLogList(List<String> tableNameList) {
		return manager_LogMapper.getAllLogList(tableNameList);
	}
	/**
	 * //获取该日志数据库中已经建立了多少个表
	 */
	@Override
	public List<String> getLogAllTable(String dataBaseName) {
	
		return manager_LogMapper.selectLogAllTable(dataBaseName);
	}

}
