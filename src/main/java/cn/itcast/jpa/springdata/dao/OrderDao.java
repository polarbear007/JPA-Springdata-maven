package cn.itcast.jpa.springdata.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cn.itcast.jpa.springdata.entity.Order;

public interface OrderDao extends CrudRepository<Order, Integer> {
	// 以关联对象的主键作为查询条件
	List<Order> findByCustomerCid(Integer cid);
	
	// 以关联对象的其他属性作为查询条件，也是可以的
	List<Order> findByCustomerCidAndCustomerCname(Integer cid, String cname);
	
	// 以关联对象的属性 + 本对象的属性作为查询条件，也是没问题的
	List<Order> findByOidGreaterThanAndCustomerCid(Integer oid, Integer cid);
}
