package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Transactional
public interface PlanRepository extends JpaRepository<IntermediateChecksPlan,Long> {
        @Modifying

    @Query(value="update Intermediate_Checks_Plan set object = :object,content = :content,check_Date = :checkDate,person_In_Charge = :personInCharge,state = :state where planid=:planid",nativeQuery=true)
    void updateById(@Param("planid")Long id,@Param("object")String object,@Param("content")String content,@Param("checkDate") Date checkDate,@Param("personInCharge")String personInCharge,@Param("state")byte state);

   /* @Query(value="update Intermediate_Checks_Plan set content = :content where planid=:planid",nativeQuery=true)
    void updatecontentById(@Param("planid")Long id,@Param("content")String content);


    @Query(value="update Intermediate_Checks_Plan set checkDate = :checkDate where planid=:planid",nativeQuery=true)
    void updateDateById(@Param("planid")Long id,@Param("checkDate") Date checkDate);


    @Query(value="update Intermediate_Checks_Plan set person_In_Charge = :personInCharge where planid=:planid",nativeQuery=true)
    void updatePersonById(@Param("planid")Long id,@Param("personInCharge")String personInCharge);


    @Query(value="update Intermediate_Checks_Plan set state = :state where planid=:planid",nativeQuery=true)
    void updateStateById(@Param("planid")Long id,@Param("state")byte state);*/
        //content,checkDate,personInCharge,state
}
