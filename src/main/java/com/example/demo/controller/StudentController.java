package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Student;
import com.example.demo.services.StudentServices;

@Controller
public class StudentController {
	
	StudentServices ss;
	
	public StudentController(StudentServices ss) {
		super();
		this.ss = ss;
	}

	@GetMapping("/addSt")
	public String mapAddStudent() {
		return "register";
	}
	
	@GetMapping("/getSt")
	public String mapGetStudent() {
		return "getDetails";
	}
	
	@GetMapping("/getAllSt")
	public String mapGetAllStudents() {
		return "getAllDetails";
	}
	
	@GetMapping("/updateSt")
	public String mapUpdateStudent() {
		return "updateDetails";
	}
	
	@GetMapping("/deleteSt")
	public String mapDeleteStudent() {
		return "deleteDetails";
	}
	
	@PostMapping("/addStudent")
	public String addStudent(@RequestParam("roll") String roll, 
			@RequestParam("name") String name, 
			@RequestParam("branch") String branch, Model model) {
		
		try {
			ss.getStudent(roll);
			model.addAttribute("message", "Student with same id present");
			return "status";
		} catch (Exception e) {
			Student s = new Student(roll, name, branch);
			String msg = ss.addStudent(s);
			model.addAttribute("message", msg);
			return "status";
		}
		
	}
	
	@GetMapping("/getStudent")
	public String getStudent(@RequestParam("roll") String roll, Model model) {
		Student s = ss.getStudent(roll);
		model.addAttribute("student", s);
		return "showDetails";
	}
	
	@GetMapping("/getAllStudents")
	public String getStudent(Model model) {
		List<Student> stList = ss.getAllStudent();
		model.addAttribute("student", stList);
		return "showDetails";
	}
	
	@PutMapping("/updateStudent")
	public String updateStudent(@RequestParam("roll") String roll,
			@RequestParam("name") String name, 
			@RequestParam("branch") String branch, Model model) {
		Student s = ss.getStudent(roll);
		if(!name.isBlank()) {
			s.setName(name);
		}
		if(!branch.isBlank()) {
			s.setBranch(branch);
		}
		String msg = ss.updateStudent(s);
		model.addAttribute("message", msg);
		return "status";
	}
	
	@DeleteMapping("/delStudent")
	public String deleteStudent(@RequestParam("roll") String roll, Model model) {
		String msg = ss.deleteStudent(roll);
		model.addAttribute("message", msg);
		return "status";
	}
}
