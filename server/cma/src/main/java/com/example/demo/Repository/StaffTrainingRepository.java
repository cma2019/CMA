package com.example.demo.Repository;

import com.example.demo.Model.StaffTraining;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffTrainingRepository extends JpaRepository<StaffTraining,Long> {
    StaffTraining findByTrainingIdAndId(long trainingId,long id);
    boolean existsByTrainingIdAndId(long trainingId,long id);
    List<StaffTraining> findAllByTrainingId(long trainingId);
    boolean existsByTrainingId(long trainingId);
    void deleteAllByTrainingId(long trainingId);
    boolean existsById(long id);
    List<StaffTraining> findAllById(long id);
    void deleteByTrainingIdAndId(long trainingId,long id);

}
