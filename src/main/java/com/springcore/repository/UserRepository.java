package com.springcore.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryRewriter;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import com.springcore.entities.User;

@Repository
public interface UserRepository extends BaseRepository<User, Long>, QueryRewriter {
	Optional<User> findByEmail(String email);

	List<User> findDistinctByEmail(String email);

	Streamable<User> findByEmailContaining(String email);

	Streamable<User> findByUsernameContaining(String username);

	@Query("SELECT u FROM User u")
	Streamable<User> findByCustomQueryAndStreamable();

	User findByEmailAndUsername(String email, String username);

	List<User> findByEmailOrUsername(String email, String username);

	List<User> findByAgeBetween(int age1, int age2);

	List<User> findByAgeLessThan(int age);

	List<User> findByAgeLessThanEqual(int age);

	List<User> findByAgeGreaterThanEqual(int age);

	List<User> findByAgeGreaterThan(int age);

	List<User> findByCreateatAfter(Date date1);

	List<User> findByCreateatBefore(Date date1);

	List<User> findByEmailNotLike(String email);

	List<User> findByEmailStartingWith(String email);

	Streamable<User> findByAgeOrderByUsernameDesc(int age);

	Streamable<User> findByAgeIn(List<Integer> age);

	Streamable<User> findByEmailIgnoreCase(String email);

	@Query("SELECT u FROM User u WHERE u.email = ?1")
	User findByEmails(String email);
//
//	@Query(value = "SELECT original_user_alias.* FROM SD_USER original_user_alias", nativeQuery = true, queryRewriter = UserRepository.class)
//	List<User> findByNativeQuery(String param);
//
//	@Query(value = "SELECT original_user_alias FROM User original_user_alias", queryRewriter = UserRepository.class)
//	List<User> findBYNonNativeQuery(String param);

	@Override
	default String rewrite(String query, Sort sort) {

		return query.replace("original_user_alias", "rewritten_user_alias");
	}

	@Query("select u from User u where u.email like ?1%")
	List<User> findByEmailEndsWith(String email);

	@Query(value = "Select * from user as u where u.email = ?1" , nativeQuery = true)
	List<User> findByEmailNative(String email);
	
	@Query("SELECT u FROM User u where u.id >= ?1")
	Page<User> findUserById(int id , Pageable pageable );
	
	@Query("SELECT u.id, u.username FROM User u ")
	List<Object[]> getIdandUsername();
}
