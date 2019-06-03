package com.example.demo.Repository;

import com.example.demo.Model.AnnualTrainingPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnualTrainingPlanRepository extends JpaRepository<AnnualTrainingPlan,Long> {
    List<AnnualTrainingPlan> findAllByYear(long year);
}
