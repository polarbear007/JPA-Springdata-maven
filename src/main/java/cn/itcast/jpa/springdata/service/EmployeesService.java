package cn.itcast.jpa.springdata.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional
	public Integer updateHireDateByLastName(Date newHireDate, String lastName) {
		return employeesDao.updateHireDateByLastName(newHireDate, lastName);
	}

}
