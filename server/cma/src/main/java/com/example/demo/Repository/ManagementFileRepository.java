package com.example.demo.Repository;

import com.example.demo.Model.ManagementFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagementFileRepository extends JpaRepository<ManagementFile,Long> {
    List<ManagementFile> findAllByYear(long year);
    //按年份获取所有信息
    ManagementFile findByFileId(long fileId);
    //按fileId获取信息
    boolean existsByFileId(long fileId);
    //按fileId判断是否存在
    //ManagementFile findByFlag(int flag);
    void deleteByFileId(long fileId);
    //按fileId删除
}
