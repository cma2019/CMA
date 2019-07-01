package com.example.demo.Repository;

import com.example.demo.Model.StaffAuthorization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffAuthorizationRepository extends JpaRepository<StaffAuthorization,Long> {
    List<StaffAuthorization> findAllById(long id);
}
