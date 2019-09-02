package com.rajesh.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rajesh.model.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long>{

}
