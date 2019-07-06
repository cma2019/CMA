package com.example.demo.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import com.example.demo.Model.InternalAuditManagement;
import java.util.List;

public interface InternalAuditManagementRepository extends JpaRepository<InternalAuditManagement, Long>{
    List<InternalAuditManagement> findAll();
    InternalAuditManagement findByYear(Long year);
    //boolean deleteByYear(long Year);
}
