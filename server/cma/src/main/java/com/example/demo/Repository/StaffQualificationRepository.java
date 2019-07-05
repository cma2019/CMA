package com.example.demo.Repository;

import com.example.demo.Model.StaffQualification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffQualificationRepository extends JpaRepository<StaffQualification,Long> {
    List<StaffQualification> findAllByStaffId(long id);
    StaffQualification findByFlag(int flag);
    StaffQualification findByQualificationId(long qualificationId);
    void deleteByQualificationId(long qualificationId);
}
