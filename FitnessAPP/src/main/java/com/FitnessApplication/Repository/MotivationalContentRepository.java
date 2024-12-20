package com.FitnessApplication.Repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FitnessApplication.Model.MotivationalContent;
@Repository
public interface MotivationalContentRepository  extends JpaRepository<MotivationalContent, Long>{
	List<MotivationalContent> findAll();

}
