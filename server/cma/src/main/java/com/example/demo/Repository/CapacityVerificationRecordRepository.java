package com.example.demo.Repository;

import com.example.demo.Model.CapacityVerificationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Transactional
public interface CapacityVerificationRecordRepository extends JpaRepository<CapacityVerificationRecord,Long> {
    List<CapacityVerificationRecord> findAllByProjectId(Long projectId);
    @Modifying

    @Query(value="update Capacity_Verification_Record set project_id=:projectid,"+
            "date = :date," +
            "method_id = :methodId," +
            "equipment_name = :equipmentName," +
            "equipment_id = :equipmentId," +
            "experimenter = :experimenter," +
            "result = :result," +
            "result_deal = :resultDeal," +
            "note=:note where record_id=:recordid",nativeQuery=true)
    void updateById(@Param("recordid")Long recordid,
                    @Param("projectid")Long projectid,
                    @Param("date") Date date,
                    @Param("methodId")String methodId,
                    @Param("equipmentName")String equipmentName,
                    @Param("equipmentId")String equipmentId,
                    @Param("experimenter")String experimenter,
                    @Param("result")String result,
                    @Param("resultDeal")String resultDeal,
                    @Param("note") String note);
}
