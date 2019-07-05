package com.example.demo.Repository;

import com.example.demo.Model.TestAbility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TestAbilityRepository extends JpaRepository<TestAbility,Long> {
    TestAbility findByFileName(String filename);
    TestAbility findByYear(long year);

    @Modifying

    @Query(value="update test_ability set file_name=:filename where year=:year",nativeQuery = true)
    void updateFileName(@Param("filename")String filename,@Param("year")long year);
}
