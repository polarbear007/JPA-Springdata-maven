package cn.itcast.jpa.springdata.dao;

import org.springframework.data.repository.Repository;

import cn.itcast.jpa.springdata.entity.Student;


public interface StudentDao extends Repository<Student, Integer> {
	public abstract Student findBySid(Integer sid);
	public abstract void save(Student stu);
}
