package com.crudapp.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crudapp.restapi.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Integer>{

}
