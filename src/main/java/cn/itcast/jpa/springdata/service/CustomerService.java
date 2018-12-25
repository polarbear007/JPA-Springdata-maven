package cn.itcast.jpa.springdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.jpa.springdata.dao.CustomerRepository;
import cn.itcast.jpa.springdata.entity.Customer;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	// 不需要添加事务
	public Customer findByCid(Integer cid) {
		return customerRepository.findOne(cid);
	}
	
	// 查询所有
	public List<Customer> findAll(){
		return (List<Customer>) customerRepository.findAll();
	}
	
	// 通过一个 集合的 id 查询对应的对象
	public List<Customer> findByList(List<Integer> list) {
		return (List<Customer>) customerRepository.findAll(list);
	}
	
	// 查询就否存在某个 id
	public boolean existsId(Integer cid) {
		return customerRepository.exists(cid);
	}
	
	// 统计总共有多少条记录
	public Long getCount() {
		return customerRepository.count();
	}
	
	// 插入一条数据，需要添加事务
	@Transactional
	public  void saveCustomer(Customer customer) {
		customerRepository.save(customer);
	}
	
	// 插入一个集合的数据，需要添加事务
	@Transactional
	public void saveCustomerList(List<Customer> customerList) {
		customerRepository.save(customerList);
	}
	
	// 根据id删除数据, 其实还有其他的删除方法，但是删除我们很少会用到
	@Transactional
	public void deleteByCid(Integer cid) {
		customerRepository.delete(cid);
	}

}
