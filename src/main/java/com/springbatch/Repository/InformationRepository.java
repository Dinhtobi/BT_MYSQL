package com.springbatch.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbatch.model.Information;

@Repository
public interface InformationRepository extends JpaRepository<Information, Integer> {

}
