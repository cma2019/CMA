package com.example.demo.Repository;
import com.example.demo.Model.EquipmentApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentApplicationRepository extends JpaRepository<EquipmentApplication, Long>{
    EquipmentApplication findById(long id);
}

