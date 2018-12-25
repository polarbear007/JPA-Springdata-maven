package cn.itcast.jpa.springdata.test;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.jpa.springdata.entity.Customer;
import cn.itcast.jpa.springdata.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringdataTest1 {
	@Autowired
	private CustomerService customerService; 
	
	// 先保存一些数据，后面好测试
	@Test
	public void test1() {
		Customer c1 = new Customer();
		c1.setCname("周星星");
		c1.setBirth(new Date());
		customerService.saveCustomer(c1);
		
		Customer c2 = new Customer();
		c2.setCname("刘德华");
		c2.setBirth(new Date());
		customerService.saveCustomer(c2);
		
		Customer c3 = new Customer();
		c3.setCname("林青霞");
		c3.setBirth(new Date());
		customerService.saveCustomer(c3);
	}
	
	// 保存一个集合
	@Test
	public void test2() {
		List<Customer> list = new ArrayList<>();
		Customer c1 = new Customer();
		c1.setCname("eric");
		c1.setBirth(new Date());
		list.add(c1);
		
		Customer c2 = new Customer();
		c2.setCname("rose");
		c2.setBirth(new Date());
		list.add(c2);
		
		Customer c3 = new Customer();
		c3.setCname("tom");
		c3.setBirth(new Date());
		list.add(c3);
		
		customerService.saveCustomerList(list);
	}
	
	// 测试查询通过 cid 查询customer对象的方法
	@Test
	public void test3() {
		Customer customer = customerService.findByCid(1);
		System.out.println(customer.getCname() + "---" + customer.getBirth());
	}
	
	// 测试通过一个 cid 集合查询对应的对象集合
	@Test
	public void test4() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(4);
		list.add(5);
		list.add(500); // 如果 查询的 id 不存在，也不会报错
		
		List<Customer> customerList = customerService.findByList(list);
		if(customerList != null) {
			for (Customer customer : customerList) {
				System.out.println(customer);
			}
		}
	}
	
	// 测试查询所有对象
	@Test
	public void test5() {
		List<Customer> list = customerService.findAll();
		if(list != null) {
			for (Customer customer : list) {
				System.out.println(customer);
			}
		}
	}
	
	// 测试是否存在 某个 id
	@Test
	public void test6() {
		System.out.println(customerService.existsId(4));
		System.out.println(customerService.existsId(10));
	}
	
	// 查询总共有多个条记录
	@Test
	public void test7() {
		System.out.println(customerService.getCount());
	}
	
	// 测试根据 id 删除
	// 删除之前会先根据这个 id 去查询一次，如果查到了，再执行删除
	// 如果没有查到会报错
	@Test
	public void test8() {
//		customerService.deleteByCid(10);
		customerService.deleteByCid(6);
	}
	
	// 测试能不能保存一个带有 id 的对象----> 可以
	// 保存一个 带有 id 的对象时，会先根据这个 id 查询，如果这个 id 存在对象，那么执行 update 操作
	//  如果这个  id 不存在，那么就执行 insert 操作， 不过插入的时候使用的主键值不是根据我们自己设置的 id
	@Test
	public void test9() {
		Customer c = new Customer();
		c.setCid(7);
		c.setCname("lala");
		c.setBirth(new Date());
		
		customerService.saveCustomer(c);
	}
	
}
