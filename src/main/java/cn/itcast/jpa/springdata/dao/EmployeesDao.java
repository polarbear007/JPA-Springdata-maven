package cn.itcast.jpa.springdata.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import cn.itcast.jpa.springdata.entity.Employees;
import cn.itcast.jpa.springdata.entity.Employees.GenderType;

public interface EmployeesDao extends PagingAndSortingRepository<Employees, Integer> {
	Employees findByEmpNo(Integer empNo);
	Employees getByLastNameAndFirstName(String lastName, String firstName);
	List<Employees> getByBirthDateBefore(Date date);
	List<Employees> getByBirthDateAfterAndGender(Date date, GenderType gender);
}
