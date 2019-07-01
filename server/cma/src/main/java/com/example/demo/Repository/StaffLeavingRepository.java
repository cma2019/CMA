package com.example.demo.Repository;

import com.example.demo.Model.StaffLeaving;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StaffLeavingRepository extends JpaRepository<StaffLeaving,Long> {
}
