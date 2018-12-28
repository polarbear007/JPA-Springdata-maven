package cn.itcast.jpa.springdata.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.hibernate.jpa.criteria.OrderImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.jpa.springdata.entity.Customer;
import cn.itcast.jpa.springdata.entity.Employees;
import cn.itcast.jpa.springdata.entity.Order;
import cn.itcast.jpa.springdata.entity.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringDataTest6 {
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	private EntityManager entityManager;
	
	@Before
	public void before() {
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	@After
	public void after() {
		entityManager.close();
	}
	
	// select * from t_students;
	// 查全表，什么条件都不加
	@Test
	public void test1() {
		// 通过 entityManger 对象或者  builder 对象
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		// 通过 builder 对象获取CriteriaQuery 对象，当我们获取的时候，可以指定一个类的 Class 对象
		// 这个 类就是本次查询要返回的类型 或者 这个类型的List集合
		CriteriaQuery<Student> query = builder.createQuery(Student.class);
		// 通过 from()  ,指定要从哪张表中查询数据
		Root<Student> root = query.from(Student.class);
		// 这里不设置查询列表，不设置查询条件，默认是查全表
		// 前面我们在创建 query 对象的时候，已经指定了 Student.class ，所以这里返回的是   List<Student>
		List<Student> list = entityManager.createQuery(query).getResultList();
		
		// 遍历一下返回的数据
		if(list != null && list.size() > 0) {
			for (Student student : list) {
				System.out.println(student);
			}
		}
	}
	
	// select * from t_students where id = 1;
	// 添加一个简单的条件
	@Test
	public void test2() {
		// 通过 entityManger 对象或者  builder 对象
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		// 通过 builder 对象获取CriteriaQuery 对象，当我们获取的时候，可以指定一个类的 Class 对象
		// 这个 类就是本次查询要返回的类型 或者 这个类型的List集合
		CriteriaQuery<Student> query = builder.createQuery(Student.class);
		// 通过 from()  ,指定要从哪张表中查询数据
		Root<Student> root = query.from(Student.class);
		// 使用 builder 创建查询条件
		Predicate condition = builder.equal(root.get("sid"), 1);
		// 使用 where() 方法，添加查询条件
		query.where(condition);
		
		// 执行查询
		Student stu = entityManager.createQuery(query).getSingleResult();
		
		System.out.println(stu);
	}
	
	// 添加多个简单的where条件
	// select * from t_students where id > 1 and age = 12
	@Test
	public void test3() {
		// 通过 entityManger 对象或者  builder 对象
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		// 通过 builder 对象获取CriteriaQuery 对象，当我们获取的时候，可以指定一个类的 Class 对象
		// 这个 类就是本次查询要返回的类型 或者 这个类型的List集合
		CriteriaQuery<Student> query = builder.createQuery(Student.class);
		// 通过 from()  ,指定要从哪张表中查询数据
		Root<Student> root = query.from(Student.class);
		// 使用 builder 创建查询条件1
		Predicate condition1 = builder.gt(root.get("sid"), 1);
		// 使用 builder 创建查询条件2
		Predicate condition2 = builder.equal(root.get("age"), 12);
		// 添加查询条件
		query.where(condition1, condition2);
		
		// 执行查询
		List<Student> list = entityManager.createQuery(query).getResultList();
		
		if(list != null && list.size() > 0) {
			for (Student student : list) {
				System.out.println(student);
			}
		}
	}
	
	// 设置查询列表
	// select sid from student where age = 12;
	// 【注意】 如果我们的查询列表变了，那么我们在创建  criteriaQuery 的时候，就需要指定不一样的返回值类型
	@Test
	public void test4() {
		// 通过 entityManger 对象或者  builder 对象
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		// 通过 builder 对象获取CriteriaQuery 对象，当我们获取的时候，可以指定一个类的 Class 对象
		// 这个 类就是本次查询要返回的类型 或者 这个类型的List集合
		CriteriaQuery<Integer> query = builder.createQuery(Integer.class);
		// 指定查询哪张表
		Root<Student> root = query.from(Student.class);
		// 指定查询列表
		query.select(root.get("sid"));
		// 添加查询条件
		query.where(builder.equal(root.get("age"), 12));
		
		// 执行查询
		List<Integer> list = entityManager.createQuery(query).getResultList();
		if(list != null && list.size() > 0) {
			for (Integer integer : list) {
				System.out.println(integer);
			}
		}
	}
	
	
	// 设置查询列表(多个查询字段,可以使用 Object[] 来接收)
	// select sid, age from t_students where age = 12;
	@Test
	public void test5() {
		// 通过 entityManger 对象或者  builder 对象
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		// 通过 builder 对象获取CriteriaQuery 对象，当我们获取的时候，可以指定一个类的 Class 对象
		// 这个 类就是本次查询要返回的类型 或者 这个类型的List集合
		CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
		// 指定查询哪张表
		Root<Student> root = query.from(Student.class);
		// 指定查询列表
		query.multiselect(root.get("sid"), root.get("age"));
		// 添加查询条件
		query.where(builder.equal(root.get("age"), 12));
		
		// 执行查询
		List<Object[]> list = entityManager.createQuery(query).getResultList();
		if(list != null && list.size() > 0) {
			for (Object[] objects : list) {
				for (Object obj : objects) {
					System.out.println(obj);
				}
				System.out.println("------------");
			}
		}
	}
	
	// 如果我们都是查询一张表中的字段，而PO类刚好又有提供这些字段对应的构造方法，那么我们还是可以使用这个
	// po 类来接收查询的数据
	// Student 类有提供  ：  public Student(sid, age)   构造方法， 如果没有提供，会报异常
	//	select sid, age from t_students where age = 12;
	@Test
	public void test6() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		// 没有查全表，仍然使用 Student 作为返回类型
		CriteriaQuery<Student> query = builder.createQuery(Student.class);
		Root<Student> root = query.from(Student.class);
		// 设置查询列表 
		query.multiselect(root.get("sid"), root.get("age"));
		// 设置查询条件
		query.where(builder.equal(root.get("age"), 12));
		
		// 执行查询
		List<Student> list = entityManager.createQuery(query).getResultList();
		if(list != null && list.size() > 0) {
			for (Student student : list) {
				System.out.println(student);
			}
		}
	}
	
	// 设置查询列表的时候，我们还可以查询一个聚合函数
	// select count(*) from t_students;
	@Test
	public void test7() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Student> root = query.from(Student.class);
		query.multiselect(builder.count(root));
		
		Long result = entityManager.createQuery(query).getSingleResult();
		System.out.println(result);
	}
	
	// 设置查询列表的时候，我们还可以查询多个聚合函数，这个时候我们可以使用一个 Object[] 来接收
	@Test
	public void test8() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
		Root<Student> root = query.from(Student.class);
		// 设置查询列表
		query.multiselect(builder.avg(root.get("age")),
						 builder.sum(root.get("age")),
						 builder.max(root.get("age")),
						 builder.min(root.get("age"))
						);
		// 执行查询
		List<Object[]> list = entityManager.createQuery(query).getResultList();
		if(list != null && list.size() > 0) {
			for (Object[] objects : list) {
				for (Object object : objects) {
					System.out.println(object);
				}
			}
		}
	}
	
	// 如果使用 Object[] 不好取值的话，那么我们可以创建  TupleQuery
	// 查询的数据可以封装到一个 Tuple 里面去，这个Tuple 你可以看成是一个有序的 Map 集合
	// 从这个 Tuple 里面取值有三种方式：
	//     使用索引： 0， 1， 2， 3
	//     使用path:  其实  root.get("xxx")   的返回值就是一个 Path<T> 接口
	//     使用别名：   要求我们在设置查询列表的时候，就要设置好别名
	@Test
	public void test9() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tuple> tupleQuery = builder.createTupleQuery();
		// 设置从哪张表查询数据
		Root<Student> root = tupleQuery.from(Student.class);
		
		// 设置查询列表
		// 为什么明明需要的是  Selection ，传入 Path 类型也可以呢？
		// 因为 Path 接口是 Selection 的子接口！！！
		Path<Integer> sidPath = root.get("sid");
		Path<String> namePath = root.get("name");
		Path<Integer> agePath = root.get("age");
		tupleQuery.multiselect(sidPath, namePath, agePath);
		
		// 执行查询
		// 使用索引遍历
		List<Tuple> list = entityManager.createQuery(tupleQuery).getResultList();
//		if(list != null && list.size() > 0) {
//			for (Tuple tuple : list) {
//				System.out.println(tuple.get(0));
//				System.out.println(tuple.get(1));
//				System.out.println(tuple.get(2));
//				System.out.println("-------------");
//			}
//		}
		
		// 使用 path 来取值
		if(list != null && list.size() > 0) {
			for (Tuple tuple : list) {
				System.out.println(tuple.get(sidPath));
				System.out.println(tuple.get(namePath));
				System.out.println(tuple.get(agePath));
				System.out.println("=======================");
			}
		}
	}
	
	// 演示添加查询列表的时候，如何添加别名
	@Test
	public void test10() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tuple> tupleQuery = builder.createTupleQuery();
		// 设置从哪张表查询数据
		Root<Student> root = tupleQuery.from(Student.class);
		
		// 设置查询列表
		// 为什么明明需要的是  Selection ，传入 Path 类型也可以呢？
		// 因为 Path 接口是 Selection 的子接口！！！
		// 如果我们想要添加别名的话，那么这里的返回值必须是  Selection<Object> 而不能是 Path，不知道为什么
		Selection<Object> sidPath = root.get("sid").alias("sid");
		Selection<Object> namePath = root.get("name").alias("name");
		Selection<Object> agePath = root.get("age").alias("age");
		tupleQuery.multiselect(sidPath, namePath, agePath);
		
		// 执行查询
		List<Tuple> list = entityManager.createQuery(tupleQuery).getResultList();
		
		// 使用别名来取值，要求添加 查询列表的时候，有设置别名
		if(list != null && list.size() > 0 ) {
			for (Tuple tuple : list) {
				System.out.println(tuple.get("sid"));
				System.out.println(tuple.get("name"));
				System.out.println(tuple.get("age"));
				System.out.println("******************");
			}
		}
	}
	
	// 演示关联查询
	// 使用关联的另一个对象的属性作为查询条件
	// 【注意】前面我们已经说过了，如果不是要使用其他表的字段作为查询条件的话，我们一般是不使用关联查询的
	//       因为如果你设置非延迟加载的话， hibernate 本身就会加载关联对象或者关联集合了
	//       就算你设置延迟加载的话，当你需要使用关联对象的数据时，hibernate 也会自动发送 sql语句的
	@Test
	public void test11() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Order> query = builder.createQuery(Order.class);
		// 设置从哪张表获取根对象
		Root<Order> orderRoot = query.from(Order.class);
		// 从上一个根对象获取关联对象，关联到另一张表
		// 我们还可以设置关联的类型：
		// Join<Order, Customer> customerJoin = orderRoot.join("customer", JoinType.LEFT);
		Join<Order, Customer> customerJoin = orderRoot.join("customer", JoinType.LEFT);
		// 添加关联条件
		query.where(builder.equal(customerJoin.get("cname"), "周星星"));
		
		//执行查询
		List<Order> list = entityManager.createQuery(query).getResultList();
		
		if(list != null && list.size() > 0) {
			for (Order order : list) {
				System.out.println(order);
			}
		}
	}
	
	// 演示对查询结果进行排序
	@Test
	public void test12() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Student> query = builder.createQuery(Student.class);
		Root<Student> root = query.from(Student.class);
		// 添加排序字段
		query.orderBy(new OrderImpl(root.get("name"), true));
		// 执行查询
		List<Student> list = entityManager.createQuery(query).getResultList();
		if(list != null && list.size() > 0) {
			for (Student student : list) {
				System.out.println(student.getName());
			}
		}
	}
	
	// 演示添加分组字段
	// select gender, count(*) from employees group by gender;
	@Test
	public void test13() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tuple> tupleQuery = builder.createTupleQuery();
		// 设置查询哪张表
		Root<Employees> root = tupleQuery.from(Employees.class);
		// 设置查询列表
		tupleQuery.multiselect(root.get("gender"), builder.count(root.get("gender")));
		// 添加分组字段
		tupleQuery.groupBy(root.get("gender"));
		
		// 执行查询
		List<Tuple> list = entityManager.createQuery(tupleQuery).getResultList();
		
		if(list != null && list.size() > 0) {
			for (Tuple tuple : list) {
				System.out.println(tuple.get(0) + " : " + tuple.get(1));
			}
		}
	}
	
	// 演示分页查询
	@Test
	public void  test14() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Employees> query = builder.createQuery(Employees.class);
		// 设置从哪张表查询
		query.from(Employees.class);
		// 这里就不添加条件了
		List<Employees> list = entityManager.createQuery(query)
													.setFirstResult(0)
													.setMaxResults(10)
													.getResultList();
		if(list != null && list.size() >0) {
			for (Employees employees : list) {
				System.out.println(employees);
			}
		}
	}
	
	// 演示模糊查询
	@Test
	public void test15() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Employees> query = builder.createQuery(Employees.class);
		// 确定是从哪一张表查询
		Root<Employees> root = query.from(Employees.class);
		// 添加查询条件
		Predicate condition = builder.like(root.get("lastName"), "_as%");
		query.where(condition);
		
		// 执行查询
		List<Employees> list = entityManager.createQuery(query)
											.setFirstResult(0)
											.setMaxResults(100)
											.getResultList();
		if(list != null && list.size() >0) {
			for (Employees employees : list) {
				System.out.println(employees);
			}
		}
	}
}
