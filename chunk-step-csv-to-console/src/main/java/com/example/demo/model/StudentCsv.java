package com.example.demo.model;

public class StudentCsv {

	private long id ;
	private String first ;
	private String last ;
	private Integer age ;
	
	public StudentCsv()
	{
		
	}

	public StudentCsv(long id, String first, String last, Integer age) {
		super();
		this.id = id;
		this.first = first;
		this.last = last;
		this.age = age;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public String toString()
	{
		return "StudentCsv [id=" + id + " name = " + first + " " + last + " age=" + age +" ]";
	}
}
