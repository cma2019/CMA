package com.example.demo.Repository;
import com.example.demo.Model.qsm;
import org.springframework.data.jpa.repository.JpaRepository;
public interface qsmRepository extends JpaRepository<qsm, Long>{
    qsm findById(long id);
}

