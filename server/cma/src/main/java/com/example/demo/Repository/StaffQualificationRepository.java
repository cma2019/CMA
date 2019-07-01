package com.example.demo.Repository;

import com.example.demo.Model.StaffQualification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffQualificationRepository extends JpaRepository<StaffQualification,Long> {
    StaffQualification findAllById(long id);
}
