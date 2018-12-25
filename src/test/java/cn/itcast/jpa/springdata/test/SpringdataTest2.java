package cn.itcast.jpa.springdata.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.jpa.springdata.dao.EmployeesDao;
import cn.itcast.jpa.springdata.entity.Employees;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringdataTest2 {
	
	//因为只是想测试一下接口本身的方法，所以我们干脆就直接注解 dao 的代理类对象
	@Autowired
	private EmployeesDao employeesDao;
	
	// 测试自动排序
	@Test
	public void test1() {
		// 首先，创建 Order 对象。 这个Order 对象是 Sort 对象的内部类
		// Order 对象主要包含三部分内容： 排序方向、排序属性名、是否忽略大小写
//		Order o1 = new Order(Direction.ASC, "firstName");
//		Order o2 = new Order(Direction.ASC, "empNo");
		// 然后通过构造方法把 order 传入 Sort 对象中
//		Sort sort = new Sort(o1, o2);
		
		// 因为默认就是升序，所以其实我们可以使用更简单的方法：
		Sort sort = new Sort("firstName", "empNo");
		
		List<Employees> list = (List<Employees>) employeesDao.findAll(sort);
		if(list != null) {
			int num  = 1;
			for (Employees employees : list) {
				System.out.println(employees);
				num++;
				if(num > 10) {
					break;
				}
			}
		}
	}
	
	// 测试一下单纯的分页查询
	@Test
	public void test2() {
		PageRequest pageRequest = new PageRequest(0, 30);
		Page<Employees> page = employeesDao.findAll(pageRequest);
		if(page.hasContent()) {
			List<Employees> content = page.getContent();
			for (Employees employees : content) {
				System.out.println(employees);
			}
		}
	}
	
	// 测试一下分页加排序
	@Test
	public void test3() {
		PageRequest pageRequest = new PageRequest(1, 30, Direction.DESC, "birthDate");
		Page<Employees> page = employeesDao.findAll(pageRequest);
		if(page.hasContent()) {
			List<Employees> content = page.getContent();
			for (Employees employees : content) {
				System.out.println(employees);
			}
		}
	}
	
	// 测试一下使用多列排序
	// 测试一下分页加排序
	@Test
	public void test4() {
		PageRequest pageRequest = new PageRequest(1, 30, Direction.DESC, "birthDate", "firstName");
		Page<Employees> page = employeesDao.findAll(pageRequest);
		if(page.hasContent()) {
			List<Employees> content = page.getContent();
			for (Employees employees : content) {
				System.out.println(employees);
			}
		}
	}
}
