package com.assignment.java.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.java.Entities.Post;

public interface PostRepository extends JpaRepository<Post,Integer> {

}
