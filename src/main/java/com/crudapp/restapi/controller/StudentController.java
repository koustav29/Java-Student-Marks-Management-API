package com.crudapp.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crudapp.restapi.entity.Student;
import com.crudapp.restapi.repository.StudentRepository;

@RestController
public class StudentController {
	
	@Autowired
	StudentRepository repo;
	
	//get all students
	@GetMapping(path = "/students")
	public List<Student> getAllStudents(){
		List<Student> students = repo.findAll();
		return students;
	}
	
	//get student with particular id
	@GetMapping(path = "/students/{id}")
	public Student getStudent(@PathVariable int id){
		Student student = repo.findById(id).get();
		return student;
	}
	
	//create a new student
	@PostMapping(path = "/student/add")
	@ResponseStatus(code=HttpStatus.CREATED)
	public String createStudent(@RequestBody Student student) {
		repo.save(student);
		String flashmsg = "New student "+student.getName()+" is added successfully!!!!";
		return flashmsg;
	}
	
	
	//update a student data with ID
	@PutMapping(path = "/student/update/{id}")
	public Student updateStudents(@PathVariable int id, @RequestBody Student student) {
		Student s = repo.findById(id).get(); 
		
		//change the values
		s.setName(student.getName());
		s.setPercentage(student.getPercentage());
		s.setBranch(student.getBranch());
		
		//then remember to update the table
		repo.save(s);
		
		return s;
	}
	
	//Delete a student from db with ID
	@DeleteMapping(path="/student/delete/{id}")
	public String removeStudent(@PathVariable int id) {
		Student s = repo.getById(id);
		String flashmsg = "Student "+s.getName()+" is deleted successfully!!!!";
		repo.deleteById(id);
		return flashmsg;
	}
	
}
