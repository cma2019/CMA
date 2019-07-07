package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.Model.IntermediateChecksRecord;
import java.sql.Date;
import java.util.Optional;

@Transactional
public interface IntermediateChecksRecordRepository extends JpaRepository<IntermediateChecksRecord,Long>{
    Optional<IntermediateChecksRecord> findByPlanId(Long planId);

    @Modifying

    @Query(value="update Intermediate_Checks_Record set plan_id=:planid," +
            "object = :object," +
            "check_Date = :checkDate," +
            "process_Record = :processRecord," +
            "process_Record_Person = :processRecordPerson," +
            "process_Record_Date=:processRecordDate,"+
            "result_Record=:resultRecord,"+
            "result_Record_Person=:resultRecordPerson,"+
            "result_Record_Date=:resultRecordDate,"+
            "note = :note where record_id=:recordid",nativeQuery=true)
    void updateById(@Param("recordid")Long recordid,
                    @Param("planid")Long planid,
                    @Param("object")String object,
                    @Param("checkDate") Date checkDate,
                    @Param("processRecord")String processRecord,
                    @Param("processRecordPerson")String processRecordPerson,
                    @Param("processRecordDate") Date processRecordDate,
                    @Param("resultRecord") String resultRecord,
                    @Param("resultRecordPerson") String resultRecordPerson,
                    @Param("resultRecordDate") Date resultRecordDate,
                    @Param("note") String note);

}
