package com.assignment.java.Repository;

import java.time.Instant;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignment.java.Entities.RefreshToken;

import jakarta.transaction.Transactional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer>{

	Optional<RefreshToken> findByToken(String token);
	
	@Query(value = "Select r.ID  from Refreshtoken as r where r.user_id = :id" , nativeQuery = true)
	Optional<Integer> findByUserId(@Param("id") int  id);
	
	@Modifying
	@Transactional
	@Query(value = "update Refreshtoken set token = :token , expiration = :expiration where user_id = :id", nativeQuery =  true)
	void updateToken(@Param("token") String token, @Param("expiration") Instant expiration , @Param("id") int id);
}
