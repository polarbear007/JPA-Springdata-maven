package cn.itcast.jpa.springdata.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_students", catalog = "jpa_springdata10086")
@Entity
public class Student implements Serializable {
	private static final long serialVersionUID = -5927373208102959686L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer sid;
	private String name;
	private Integer age;

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
