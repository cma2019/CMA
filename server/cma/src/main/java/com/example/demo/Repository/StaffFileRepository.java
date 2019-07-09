package com.example.demo.Repository;

import com.example.demo.Model.StaffFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffFileRepository extends JpaRepository<StaffFile,Long> {
    boolean existsByStaffId(long id);
    //按人员id判断是否存在
    StaffFile findByStaffId(long id);
    //按人员id查找
    void deleteByStaffId(long id);
    //按人员id删除
    //StaffFile findByFlag(int flag);
}
