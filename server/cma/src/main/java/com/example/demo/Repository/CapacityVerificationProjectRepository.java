package com.example.demo.Repository;

import com.example.demo.Model.CapacityVerificationProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface CapacityVerificationProjectRepository extends JpaRepository<CapacityVerificationProject,Long>{
    List<CapacityVerificationProject> findAllByPlanId(Long planId);//查找所有计划对应的项目
    Optional<CapacityVerificationProject> findByPlanId(Long planId);//查找计划对应的项目
    List<CapacityVerificationProject> findAllByPlanIdAndState(Long planId,long state);//按计划序号和状态查找所有符合条件的项目

    @Modifying//修改项目信息

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

    @Modifying//修改对应的计划的状态

    @Query(value="update Capacity_Verification_Plan set state = :state where plan_id=:planid",nativeQuery=true)
    void updatePlanState(@Param("state")long state,
                    @Param("planid")Long planid);

    @Modifying//修改项目的状态

    @Query(value="update Capacity_Verification_Project set state = :state where project_id=:projectid",nativeQuery=true)
    void updateProjectState(@Param("projectid")Long projectid,
                    @Param("state")Long state);

    @Modifying//删除项目对应的记录
    @Query(value="delete from Capacity_Verification_Record where project_id=:projectid",nativeQuery = true)
    void deleteRecord(@Param("projectid")Long projectid);
}
