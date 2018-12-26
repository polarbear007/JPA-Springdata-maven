package cn.itcast.jpa.springdata.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.jpa.springdata.dao.EmployeesDao;
import cn.itcast.jpa.springdata.entity.Employees;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringdataTest3 {
	// 同样，因为只是要测试一下 dao 层的方法，根本就不想通过 service 层调用，
	// 所以这里直接注入 dao 层的代理对象，然后直接测试方法
	@Autowired
	private EmployeesDao employeesDao;
	
	@Test
	public void test1() {
		List<Employees> list = employeesDao.getByLastNameAndFirstName("Simmel", "Bezalel");
		if(list != null && list.size() > 0) {
			for (Employees employees : list) {
				System.out.println(employees);
			}
		}
	}
	
	@Test
	public void test2() throws ParseException {
		String str = "1955-3-1";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
		List<Employees> list = employeesDao.getByBirthDateBefore(date);
		if(list != null && list.size() > 0) {
			for (Employees employees : list) {
				System.out.println(employees);
			}
		}
	}
	
	//【注意】 通过关键字带声明的 dao 方法，还可以额外加上排序或者分页的条件！！！ 也就是加上 Sort 或者  PageRequest 对象。
	@Test
	public void test3() throws ParseException {
		String str = "1955-3-1";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
		Pageable pageable = new PageRequest(0, 10);
		Page<Employees> page = employeesDao.getByBirthDateBefore(date, pageable);
		
		if(page.hasContent()) {
			List<Employees> list = page.getContent();
			for (Employees employees : list) {
				System.out.println(employees);
			}
		}
	}
	
	@Test
	public void test4() throws ParseException {
		String str = "1955-3-1";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
		List<Employees> list = employeesDao.getByBirthDateAfterAndGender(date, Employees.GenderType.F);
		if(list != null && list.size() > 0) {
			for (Employees employees : list) {
				System.out.println(employees);
			}
		}
	}
	
	@Test
	public void test5() {
		List<Employees> list = employeesDao.getByLastNameStartingWith("ad");
		if(list != null && list.size() > 0) {
			for (Employees employees : list) {
				System.out.println(employees);
			}
		}
	}
	
	@Test
	public void  test6() {
		List<Employees> list = employeesDao.getByLastNameEndingWith("s");
		if(list != null && list.size() > 0) {
			for (Employees employees : list) {
				System.out.println(employees);
			}
		}
	}
}
