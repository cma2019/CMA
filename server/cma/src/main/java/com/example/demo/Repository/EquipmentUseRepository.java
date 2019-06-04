package com.example.demo.Repository;


import com.example.demo.Model.EquipmentUse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentUseRepository extends JpaRepository<EquipmentUse, Long>{
    EquipmentUse findByid(long id);
}
