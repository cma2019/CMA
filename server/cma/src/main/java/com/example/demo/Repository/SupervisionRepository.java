package com.example.demo.Repository;
import com.example.demo.Model.Supervision;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SupervisionRepository extends JpaRepository<Supervision, Long> {
    Supervision findById(long id);
    List<Supervision> findAll();
}