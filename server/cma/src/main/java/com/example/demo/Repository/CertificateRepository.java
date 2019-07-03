package com.example.demo.Repository;
import com.example.demo.Model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CertificateRepository extends JpaRepository<Certificate, Long>{
    Certificate findById(long id);
}
