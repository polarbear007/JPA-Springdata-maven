package cn.itcast.jpa.springdata.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.itcast.jpa.springdata.entity.Employees;
import cn.itcast.jpa.springdata.entity.Employees.GenderType;

public interface EmployeesDao extends JpaRepository<Employees, Integer>{
	// 通过lastName 和 firstName 查询(有可能出现同名的，如果你只用一个 Employees 去接，可能会报错)
	List<Employees> getByLastNameAndFirstName(String lastName, String firstName);
	
	// 查询出生日期早于某个指定时间的记录（比如查在2000年以前出生的记录）
	List<Employees> getByBirthDateBefore(Date date);
	
	// 查询出生日期早于某个指定时间的记录（比如查在2000年以前出生的记录）----> 条件带分页查询
	Page<Employees> getByBirthDateBefore(Date date, Pageable pageable);
	
	// 查询出生日期晚于某个时间，而且性别等于某个类型的记录（比如查在2000年以后出生的、性别为女的记录）
	List<Employees> getByBirthDateAfterAndGender(Date date, GenderType gender);
	
	// 查询 lastName 以 Ac 开头的
	List<Employees> getByLastNameStartingWith(String lastName);
	
	// 查询 lastName 以 d 结尾的
	List<Employees> getByLastNameEndingWith(String lastName);
	
	
}
