package com.example.demo.Repository;

import com.example.demo.Model.ManagementReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagementReviewRepository extends JpaRepository<ManagementReview,Long> {
    boolean existsByYear(long year);
    //按年份判断是否存在
    void deleteByYear(long year);
    //按年份删除
}
