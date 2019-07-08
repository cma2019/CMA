package com.example.demo.Repository;

import com.example.demo.Model.AllAnnualPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllAnnualPlanRepository extends JpaRepository<AllAnnualPlan,Long> {
    AllAnnualPlan findByYear(long year);
    //按年份查找
    boolean existsByYear(long year);
    //按年份判断是否存在
}
