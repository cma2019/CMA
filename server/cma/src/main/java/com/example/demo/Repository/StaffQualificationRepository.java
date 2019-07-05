package com.example.demo.Repository;

import com.example.demo.Model.StaffQualification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffQualificationRepository extends JpaRepository<StaffQualification,Long> {
    StaffQualification findAllById(long id);
    StaffQualification findByFlag(int flag);
    StaffQualification findByQualificationId(long qualificationId);
    void deleteByQualificationId(long qualificationId);
}
