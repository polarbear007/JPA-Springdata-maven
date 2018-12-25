package cn.itcast.jpa.springdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.jpa.springdata.dao.CustomerRepository;
import cn.itcast.jpa.springdata.entity.Customer;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	
	public Customer findByCid(Integer cid) {
		return customerRepository.findByCid(cid);
	}
	
	@Transactional
	public  void saveCustomer(Customer customer) {
		customerRepository.save(customer);
	}
}
