package cn.itcast.jpa.springdata.test;


import java.util.Date;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.jpa.springdata.dao.CustomerRepository;
import cn.itcast.jpa.springdata.entity.Customer;
import cn.itcast.jpa.springdata.service.CustomerService;

public class SpringdataTest {
	private ClassPathXmlApplicationContext context;
	private DataSource dataSource;
	private CustomerService customerService;
	private CustomerRepository customerRepository;
	
	@Before
	public void before() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		dataSource = context.getBean("dataSource", DataSource.class);
		customerService = context.getBean(CustomerService.class);
		customerRepository = context.getBean(CustomerRepository.class);
	}
	
	// 最好配置一点就测试一点，省得都配置完以后，出错，很难排察
    // 测试是否能够正确连接数据库
	@Test
	public void test() throws Exception {
		System.out.println(dataSource.getConnection());
	}
	
	@Test
	public void testRepository() {
		Customer c = new Customer();
		c.setCname("周星星");
		c.setBirth(new Date());
		customerRepository.save(c);
	}
	
	@Test
	public void testRepository2() {
		Customer customer = customerService.findByCid(1);
		System.out.println(customer.getCname() + "---" + customer.getBirth());
	}
}
