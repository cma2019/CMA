package com.example.demo.Repository;

import com.example.demo.Model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
/**
 * @author yxp
 */
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
   Equipment findById(long id);
}