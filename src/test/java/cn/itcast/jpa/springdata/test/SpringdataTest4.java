package cn.itcast.jpa.springdata.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
	
	@Test
	public void testSave() {
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
	public void testFind() {
		Order order = orderService.findByOid(1);
		System.out.println(order);
	}
	
	@Test
	public void testFind2() {
		List<Order> list = orderService.findByCustomerCid(1);
		if(list != null) {
			for (Order order : list) {
				System.out.println(order);
			}
		}
	}
}
