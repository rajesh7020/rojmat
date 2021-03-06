package com.rajesh.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rajesh.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByEmail(String email);
	@Query("from User as u where u.email=:email and u.password=:password")
	public User loginUser(String email,String password);
	@Query("select active from User as u where u.email=:email")
	public Integer isActiveUserStatus(String email);
}
