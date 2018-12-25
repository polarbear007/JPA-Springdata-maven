package cn.itcast.jpa.springdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cn.itcast.jpa.springdata.dao.EmployeesDao;
import cn.itcast.jpa.springdata.entity.Employees;

@Service
public class EmployeesService {
	@Autowired
	private EmployeesDao employeesDao;
	
	public List<Employees> findAllSorted(Sort sort) {
		return (List<Employees>) employeesDao.findAll(sort);
	}
	
	public Page<Employees> findPage(Pageable pageable){
		return employeesDao.findAll(pageable);
	}

}
