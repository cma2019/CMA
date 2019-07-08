package com.example.demo.Repository;

import com.example.demo.Model.ManagementFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagementFileRepository extends JpaRepository<ManagementFile,Long> {
    List<ManagementFile> findAllByYear(long year);
    ManagementFile findByFileId(long fileId);
    boolean existsByFileId(long fileId);
    //ManagementFile findByFlag(int flag);
    void deleteByFileId(long fileId);
}
