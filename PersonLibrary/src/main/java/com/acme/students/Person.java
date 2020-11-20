package com.acme.students;

import java.io.Serializable;

public class Person implements Serializable, Comparable<Person> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 501468995992752019L;
	private String name;
	private String age;
	private String gender;

	public Person(String name, String age, String gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int compareTo(Person person) {
		if ((this.age == person.getAge() && this.getName().equals(person.getName())
				&& this.gender.equals(person.getGender()))) {
			return 0;
		} else if ((this.age > person.getAge())) {
			return 1;
		} else {
			return -1;
		}

	}

}
