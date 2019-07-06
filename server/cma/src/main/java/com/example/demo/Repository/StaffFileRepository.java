package com.example.demo.Repository;

import com.example.demo.Model.StaffFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffFileRepository extends JpaRepository<StaffFile,Long> {
    boolean existsByStaffId(long id);
    StaffFile findByStaffId(long id);
    void deleteByStaffId(long id);
    StaffFile findByFlag(int flag);
}
