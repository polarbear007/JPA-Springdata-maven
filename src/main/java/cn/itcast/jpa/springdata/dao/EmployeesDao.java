package cn.itcast.jpa.springdata.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.itcast.jpa.springdata.entity.Employees;
import cn.itcast.jpa.springdata.entity.Employees.GenderType;

public interface EmployeesDao extends JpaRepository<Employees, Integer>{
	// 通过lastName 和 firstName 查询(有可能出现同名的，如果你只用一个 Employees 去接，可能会报错)
	List<Employees> getByLastNameAndFirstName(String lastName, String firstName);
	
	// 查询出生日期早于某个指定时间的记录（比如查在2000年以前出生的记录）
	List<Employees> getByBirthDateBefore(Date date);
	
	// 查询出生日期早于某个指定时间的记录（比如查在2000年以前出生的记录）----> 条件带分页查询
	Page<Employees> getByBirthDateBefore(Date date, Pageable pageable);
	
	// 查询出生日期晚于某个时间，而且性别等于某个类型的记录（比如查在2000年以后出生的、性别为女的记录）
	List<Employees> getByBirthDateAfterAndGender(Date date, GenderType gender);
	
	// 查询 lastName 以 Ac 开头的
	List<Employees> getByLastNameStartingWith(String lastName);
	
	// 查询 lastName 以 d 结尾的
	List<Employees> getByLastNameEndingWith(String lastName);
	
	// 演示使用 @query 进行简单的查询
	@Query(value="Select e from Employees e where e.empNo = ?")
	Employees getByEmpNo(Integer empNo);
	
	// 演示使用 @query 进行简单的查询
	@Query(value="Select e from Employees e where e.birthDate > ? and e.gender = ? order by e.birthDate")
	List<Employees> getByBirthDateAfterAndGender2(Date date, GenderType gender);
	
	// 演示使用 @query 进行模糊查询
	// 【注意】 @query 如果想要把 %% 写进预定义的sql 中，那么 %% 中间的参数必须使用   : 参数名 的形式定义，不能单纯地使用 ?
	//        然后在我们传的参数前面，必须使用  @param("参数名" ) 的形式进行配对
	@Query(value="Select e from Employees e where e.lastName like %:lastName%")
	List<Employees> getByLastName(@Param("lastName") String lastName);
	
	// 演示使用 @query 写更新语句
	// 注意：增删改等操作，必须添加 @Modifying 注解才行； 而且必须在事务中进行，也就是必须在Service 层来个 @transactional 注解
	@Modifying
	@Query(value="update Employees e set e.hireDate = ? where e.lastName = ?")
	Integer updateHireDateByLastName(Date newHireDate, String lastName);
	
	// 演示使用 @query 写普通的 sql 语句
	// 需要添加 nativeQuery 属性，告诉springdata 这是一个原生的sql 语句
	@Query(value="select * from employees where first_name like %:firstName% limit 10", nativeQuery=true)
	List<Employees> getByFirstName(@Param("firstName") String firstName);
}
