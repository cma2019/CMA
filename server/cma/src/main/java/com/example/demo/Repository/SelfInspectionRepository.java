package com.example.demo.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import com.example.demo.Model.SelfInspection;
import java.util.List;

public interface SelfInspectionRepository extends JpaRepository<SelfInspection, Long>{
    SelfInspection findById(long id);
    SelfInspection findByName(String name);
    List<SelfInspection> findAll();
}
