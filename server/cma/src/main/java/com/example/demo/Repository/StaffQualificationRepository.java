package com.example.demo.Repository;

import com.example.demo.Model.StaffQualification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffQualificationRepository extends JpaRepository<StaffQualification,Long> {
    List<StaffQualification> findAllByStaffId(long id);
    //按人员id查找全部
    //StaffQualification findByFlag(int flag);
    StaffQualification findByQualificationId(long qualificationId);
    //按资质id查找
    void deleteByQualificationId(long qualificationId);
    //按资质id删除
}
