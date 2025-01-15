package com.FitnessWebApp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FitnessWebApp.Entities.UserInput;
@Repository
public interface UserInputRepository extends JpaRepository<UserInput, Long>{

}
