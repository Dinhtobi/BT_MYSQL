package com.springcore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T,ID> extends JpaRepository<T, ID> {
	Optional<T> findById(ID id);
	
	<S extends T> S save(S s);
}
