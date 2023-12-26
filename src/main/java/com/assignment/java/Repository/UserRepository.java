package com.assignment.java.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import com.assignment.java.Entities.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email) ;
	
	@Modifying
	@Transactional
	@Query("delete from User where id = ?1")
	void deleteUser(int id);
	
	@Query(value = "Select id from user as u where u.created_at <= :now and u.enabled = false ",nativeQuery = true)
	List<Integer> getIds(@Param("now") @DateTimeFormat(pattern =  "yyyy-MM-dd HH-mm-ss") Date now);
}
