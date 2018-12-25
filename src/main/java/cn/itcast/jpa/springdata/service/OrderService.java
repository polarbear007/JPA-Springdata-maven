package cn.itcast.jpa.springdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.jpa.springdata.dao.OrderDao;
import cn.itcast.jpa.springdata.entity.Order;

@Service
public class OrderService {
	@Autowired
	public OrderDao orderDao;
	
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}
	
	public List<Order> findByCustomerCid(Integer cid){
		return orderDao.findByCustomerCid(cid);
	}
	
	public void SaveOrder(Order o) {
		orderDao.save(o);
	}
}
