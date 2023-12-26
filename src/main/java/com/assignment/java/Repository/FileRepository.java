package com.assignment.java.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.java.Entities.File;

public interface FileRepository extends JpaRepository<File, Integer>{
}
