package com.FitnessApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FitnessApplication.Model.UserProfile;
@Repository
public interface ProfileRepository extends JpaRepository<UserProfile , Long>{

}
