package com.example.demo.Repository;
import com.example.demo.Model.SupervisionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SupervisionRecordRepository extends JpaRepository<SupervisionRecord, Long> {
    SupervisionRecord findByRecordId(Long recordId);
    List<SupervisionRecord> findAllByPlanId(Long planId);
}
