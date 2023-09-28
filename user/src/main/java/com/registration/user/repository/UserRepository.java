package com.registration.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.registration.user.model.Users;

 public interface UserRepository extends JpaRepository<Users, Long> {
	 
	 @Query(value="select * from USERS a where a.first_name like %:keyword% or a.last_name like %:keyword% or a.email like %:keyword% ", nativeQuery = true)
	 List<Users> findByKeyword(@Param("keyword") String keyword);
	 
	 Users findByEmail(String email);

}