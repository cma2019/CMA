package com.example.demo.Repository;

import com.example.demo.Model.AllAnnualPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllAnnualPlanRepository extends JpaRepository<AllAnnualPlan,Long> {
    AllAnnualPlan findByYear(long year);
    boolean existsByYear(long year);
}
