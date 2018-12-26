package cn.itcast.jpa.springdata.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.jpa.springdata.dao.EmployeesDao;
import cn.itcast.jpa.springdata.entity.Employees;
import cn.itcast.jpa.springdata.entity.Employees.GenderType;
import cn.itcast.jpa.springdata.service.EmployeesService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringdataTest5 {
	@Autowired
	private EmployeesDao employeesDao;
	
	@Autowired
	private EmployeesService employeesService;
	
	@Test
	public void test1() {
		Employees employees = employeesDao.getByEmpNo(10001);
		System.out.println(employees);
	}
	
	@Test
	public void test2() throws ParseException {
		String str = "1963-12-12";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
		List<Employees> list = employeesDao.getByBirthDateAfterAndGender2(date, GenderType.F);
		if(list != null && list.size() > 0) {
			for (Employees employees : list) {
				System.out.println(employees);
			}
		}
	}
	
	@Test
	public void test3() {
		List<Employees> list = employeesDao.getByLastName("f");
		if(list != null && list.size() > 0) {
			for (Employees employees : list) {
				System.out.println(employees);
			}
		}
	}
	
	// 【注意】 增删改 操作必须使用事务，也就是说必须通过 service 层调用，然后加 @transactional
	@Test
	public void test4() {
		Integer rows = employeesService.updateHireDateByLastName(new Date(), "Facello");
		System.out.println("修改了 " + rows + "行数据");
	}
	
	// 测试原生的sql 语句
	@Test
	public void test5() {
		List<Employees> list = employeesDao.getByFirstName("m");
		if(list != null && list.size() > 0) {
			for (Employees employees : list) {
				System.out.println(employees);
			}
		}
	}
}
