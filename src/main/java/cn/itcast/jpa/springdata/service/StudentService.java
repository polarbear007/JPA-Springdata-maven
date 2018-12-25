package cn.itcast.jpa.springdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.jpa.springdata.dao.StudentDao;
import cn.itcast.jpa.springdata.entity.Student;

@Service
public class StudentService {
	@Autowired
	private StudentDao studentDao;
	
	@Transactional
	public void saveStudent(Student stu) {
		studentDao.save(stu);
	}
	
	// 查询不可以不添加事务，但是增删改必须添加事务
	public Student findBySid(Integer sid) {
		return studentDao.findBySid(sid);
	}
	
}
