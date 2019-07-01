package com.example.demo.Repository;
import com.example.demo.Model.EquipmentMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentMaintenanceRepository extends JpaRepository<EquipmentMaintenance, Long> {
    EquipmentMaintenance findByEquipmentId(Long id);
    EquipmentMaintenance findById(long id);
}

