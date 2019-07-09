package com.example.demo.Repository;


import com.example.demo.Model.StandardManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandardManagementRepository extends JpaRepository<StandardManagement,Long> {
    StandardManagement findByFileId(long id);//自定义find方法，标准返回类型应该用Optional<StandardManagement>
}
