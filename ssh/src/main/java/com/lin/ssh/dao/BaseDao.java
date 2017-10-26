package com.lin.ssh.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lin.ssh.bean.Lin;

public class BaseDao<T>{
	@Autowired
	SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
    public void save(T t) {  
    	getSession().save(t);  
    }  
  
    public void delete(int id) {  
    	Lin lin = new Lin();
    	lin.setId(id);
    	getSession().delete(lin);
    }  
    public void delete(Lin lin) {  
    	getSession().delete(lin);
    }  
  
    @SuppressWarnings("unchecked")
	public List<T> getAll(String tableName) {  
    	return getSession().createQuery(tableName).list();
    }  
  
    public void update(Lin lin){
    	getSession().saveOrUpdate(lin);
    }
}
