package cn.itcast.jpa.springdata.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cn.itcast.jpa.springdata.entity.Order;

public interface OrderDao extends CrudRepository<Order, Integer> {
	Order findByOid(Integer oid);
	List<Order> findByCustomerCid(Integer cid);
}
