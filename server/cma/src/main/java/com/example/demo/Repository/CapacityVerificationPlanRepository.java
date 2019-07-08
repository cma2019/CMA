package com.example.demo.Repository;

import com.example.demo.Model.CapacityVerificationPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CapacityVerificationPlanRepository extends JpaRepository<CapacityVerificationPlan,Long> {
    CapacityVerificationPlan findByPlanId(Long planId);
    @Modifying

    @Query(value="update Capacity_Verification_Plan set name = :name," +
            "organizer = :organizer," +
            "year = :year," +
            "note=:note where plan_id=:planid",nativeQuery=true)
    void updateById(@Param("planid")Long planid,
                    @Param("name")String name,
                    @Param("organizer")String organizer,
                    @Param("year") String year,
                    @Param("note") String note);

    @Modifying
    @Query(value="update Capacity_Verification_Plan set analysis=:analysis where plan_id=:planid",nativeQuery = true)
    void updateAnalysis(@Param("analysis")String analysis,
                        @Param("planid")Long planid);

    @Modifying
    @Query(value="delete from Capacity_Verification_Project where plan_id=:planid",nativeQuery = true)
    void deleteProject(@Param("planid")Long planid);
}
