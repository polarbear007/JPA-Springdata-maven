package cn.itcast.jpa.springdata.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.itcast.jpa.springdata.entity.Customer;

@Repository
public interface CustomerRepository  extends CrudRepository<Customer, Integer>{
	
	public abstract Customer findByCid(Integer cid);
	
}
