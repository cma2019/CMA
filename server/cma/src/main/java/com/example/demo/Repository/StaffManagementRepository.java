package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.StaffManagement;
public interface StaffManagementRepository extends JpaRepository<StaffManagement, Long> {
    StaffManagement findByName(String name);
    //按姓名查找
    void deleteByName(String name);
    //按姓名删除
}
