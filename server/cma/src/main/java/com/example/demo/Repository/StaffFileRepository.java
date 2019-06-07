package com.example.demo.Repository;

import com.example.demo.Model.StaffFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffFileRepository extends JpaRepository<StaffFile,Long> {
    boolean existsByStaffid(long id);
    StaffFile findByStaffid(long id);
    void deleteByStaffid(long id);
}
