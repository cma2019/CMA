package com.example.demo.StaffManagement;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.StaffManagement.StaffManagement;
public interface StaffManagementRepository extends JpaRepository<StaffManagement, Long> {
    StaffManagement findByName(String name);
    void deleteByName(String name);
}
