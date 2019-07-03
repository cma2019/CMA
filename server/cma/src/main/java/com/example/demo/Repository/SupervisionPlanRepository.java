package com.example.demo.Repository;
import com.example.demo.Model.SupervisionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SupervisionPlanRepository extends JpaRepository<SupervisionPlan, Long> {
    SupervisionPlan findByPlanID(Long planId);
    List<SupervisionPlan> findAllById(Long id);
}
