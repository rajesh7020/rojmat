package com.rajesh.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rajesh.model.Gst;

@Repository
public interface GstRepository extends JpaRepository<Gst, Long>{

}
