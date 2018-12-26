package cn.itcast.jpa.springdata.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.jpa.springdata.dao.OrderDao;
import cn.itcast.jpa.springdata.entity.Customer;
import cn.itcast.jpa.springdata.entity.Order;
import cn.itcast.jpa.springdata.service.CustomerService;
import cn.itcast.jpa.springdata.service.OrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringdataTest4 {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OrderDao orderDao;
	
	// 设置关联关系，方便后面的测试
	@Test
	public void test1() {
		Customer c1 = customerService.findByCid(1);
		Customer c2 = customerService.findByCid(2);
		
		Order o1 = new Order();
		o1.setCustomer(c1);
		o1.setName(c1.getCname() + Math.random());
		orderService.SaveOrder(o1);
		
		Order o2 = new Order();
		o2.setCustomer(c1);
		o2.setName(c1.getCname() + Math.random());
		orderService.SaveOrder(o2);
		
		Order o3 = new Order();
		o3.setCustomer(c2);
		o3.setName(c2.getCname() + Math.random());
		orderService.SaveOrder(o3);
	}
	
	@Test
	public void test2() {
		List<Order> list = orderDao.findByCustomerCid(1);
		if(list != null && list.size() > 0) {
			for (Order order : list) {
				System.out.println(order);
			}
		}
	}
	
	@Test
	public void test3() {
		List<Order> list = orderDao.findByCustomerCidAndCustomerCname(1, "周星星");
		if(list != null && list.size() > 0) {
			for (Order order : list) {
				System.out.println(order);
			}
		}
	}
	
	@Test
	public void test4() {
		List<Order> list = orderDao.findByOidGreaterThanAndCustomerCid(2, 2);
		if(list != null && list.size() > 0) {
			for (Order order : list) {
				System.out.println(order);
			}
		}
	}
	
}
