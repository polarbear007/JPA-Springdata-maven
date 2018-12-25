package cn.itcast.jpa.springdata.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.jpa.springdata.entity.Student;
import cn.itcast.jpa.springdata.service.StudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringdataTest {
	@Autowired
	private StudentService studentService;
	
	@Test
	public void testSave() {
		Student stu = new Student();
		stu.setName("张三");
		stu.setAge(12);
		studentService.saveStudent(stu);
	}
	
	@Test
	public void testFind() {
		Student student = studentService.findBySid(1);
		System.out.println(student.getName() + "--" + student.getAge());
	}
}
