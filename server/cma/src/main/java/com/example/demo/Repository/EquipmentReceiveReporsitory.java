package com.example.demo.Repository;
import com.example.demo.Model.EquipmentReceive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentReceiveReporsitory extends JpaRepository<EquipmentReceive, Long>{
    EquipmentReceive findById(long id);
}
