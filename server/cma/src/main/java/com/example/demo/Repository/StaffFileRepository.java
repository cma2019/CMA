package com.example.demo.Repository;

import com.example.demo.Model.StaffFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffFileRepository extends JpaRepository<StaffFile,Long> {
    boolean existsById(long id);
    StaffFile findById(long id);
    void deleteById(long id);
    StaffFile findByFlag(int flag);
}
