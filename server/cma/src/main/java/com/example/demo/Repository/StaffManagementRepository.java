package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.StaffManagement;
public interface StaffManagementRepository extends JpaRepository<StaffManagement, Long> {
    StaffManagement findByName(String name);
    void deleteByName(String name);
}
