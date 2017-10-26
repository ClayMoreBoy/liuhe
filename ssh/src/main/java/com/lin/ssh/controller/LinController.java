package com.lin.ssh.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lin.ssh.bean.Lin;
import com.lin.ssh.service.LinService;

@Controller

public class LinController {

	@Autowired
	LinService linService;

	private static final Logger logger = Logger.getLogger(LinController.class);
	
	private static String  ERROR;

	@ResponseBody
	@RequestMapping("get")
	public List<Lin> getData(){
		List<Lin> data = linService.getData("lin");
		logger.info("查询数据成功......");
		return data;
	}

	@ResponseBody
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String saveData(@ModelAttribute("lin")Lin lin){
		try{
			linService.saveData(lin);
			ERROR = "save success";
			logger.info("保存数据成功......");
		}catch(Exception e){
			logger.info(e);
			ERROR = "save error";
		}
		return ERROR;
	}

	@ResponseBody
	@RequestMapping(value="delete",method=RequestMethod.DELETE)
	public String deleteData(int id){
		try{
			linService.deleteData(id);
			ERROR = "delete success";
			logger.info("删除数据成功......");
		}catch(Exception e){
			logger.info(e);
			ERROR = "delete error";
		}
		return ERROR;
	}

	@ResponseBody
	@RequestMapping(value="update",method=RequestMethod.PUT)
	public String updateData(@ModelAttribute("lin")Lin lin){
		try{
			linService.updateData(lin);
			ERROR = "update success";
			logger.info("更新数据成功......");
		}catch(Exception e){
			logger.info(e);
			ERROR = "update error";
		}
		return ERROR;
	}
}
