package com.example.demo.Repository;

import com.example.demo.Model.StaffTraining;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffTrainingRepository extends JpaRepository<StaffTraining,Long> {
    StaffTraining findByTrainingIdAndId(long trainingId,long id);
    //按培训id和人员id查找
    boolean existsByTrainingIdAndId(long trainingId,long id);
    //按培训id和人员id判断是否存在
    List<StaffTraining> findAllByTrainingId(long trainingId);
    //按照培训id查找所有
    boolean existsByTrainingId(long trainingId);
    //按培训id判断是否存在
    void deleteAllByTrainingId(long trainingId);
    //按培训id删除所有
    //boolean existsById(long id);
    List<StaffTraining> findAllById(long id);
    //按人员id查找所有
    void deleteByTrainingIdAndId(long trainingId,long id);
    //按培训id和人员id删除

}
