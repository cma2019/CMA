package com.example.demo.Repository;

import com.example.demo.Model.TestAbility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TestAbilityRepository extends JpaRepository<TestAbility,Long> {
    TestAbility findByFileName(String filename);//根据文件名查找
    TestAbility findByYear(long year);//根据年份（Id)查找

    @Modifying

    @Query(value="update test_ability set file_name=:filename where year=:year",nativeQuery = true)
    void updateFileName(@Param("filename")String filename,@Param("year")long year);//更新数据库中文件名
}
