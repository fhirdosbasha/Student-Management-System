package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentServicesImplementation implements StudentServices{

	StudentRepository srepo;
	
	public StudentServicesImplementation(StudentRepository srepo) {
		super();
		this.srepo = srepo;
	}

	@Override
	public String addStudent(Student s) {
		srepo.save(s);
		return "Student added successfully";
	}

	@Override
	public Student getStudent(String kodId) {
		Student s = srepo.findById(kodId).get();
		return s;
	}

	@Override
	public List<Student> getAllStudent() {
		List<Student> stList = srepo.findAll();
		return stList;
	}

	@Override
	public String updateStudent(Student s) {
		srepo.save(s);
		return "Student updated successfully";
	}

	@Override
	public String deleteStudent(String kodId) {
		srepo.deleteById(kodId);
		return "Student deleted successfully";
	}

}
