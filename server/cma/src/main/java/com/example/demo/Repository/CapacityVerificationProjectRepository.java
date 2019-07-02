package com.example.demo.Repository;

import com.example.demo.Model.CapacityVerificationProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CapacityVerificationProjectRepository extends JpaRepository<CapacityVerificationProject,Long>{
    List<CapacityVerificationProject> findAllByPlanId(Long planId);
    CapacityVerificationProject findByPlanId(Long planId);

    @Modifying

    @Query(value="update Capacity_Verification_Project set plan_id=:planid,"+
            "name = :name," +
            "method = :method," +
            "state = :state," +
            "note=:note where project_id=:projectid",nativeQuery=true)
    void updateById(@Param("projectid")Long projectid,
                    @Param("planid")Long planid,
                    @Param("name")String name,
                    @Param("method")String method,
                    @Param("state")Long state,
                    @Param("note") String note);
}
