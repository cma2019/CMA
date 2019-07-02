package com.example.demo.Repository;

import com.example.demo.Model.CapacityVerificationPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CapacityVerificationPlanRepository extends JpaRepository<CapacityVerificationPlan,Long> {

    @Modifying

    @Query(value="update Capacity_Verification_Plan set plan_id=:planid," +
            "name = :name," +
            "organizer = :organizer," +
            "state = :state," +
            "year = :year," +
            "note=:note,",nativeQuery=true)
    void updateById(@Param("planid")Long planid,
                    @Param("name")String name,
                    @Param("organizer")String organizer,
                    @Param("state")Long state,
                    @Param("year") String year,
                    @Param("note") String note);
}