package com.example.demo.Repository;
import com.example.demo.Model.ExternalReviewManagement;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ExternalReviewManagementRepository extends JpaRepository<ExternalReviewManagement,Long> {
    ExternalReviewManagement findAllByYear(long year);
    ExternalReviewManagement findById(long id);
}
