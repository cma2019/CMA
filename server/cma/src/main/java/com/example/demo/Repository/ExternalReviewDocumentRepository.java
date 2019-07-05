package com.example.demo.Repository;
import com.example.demo.Model.ExternalReviewDocument;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ExternalReviewDocumentRepository extends JpaRepository<ExternalReviewDocument,Long>{
    ExternalReviewDocument findAllByYear(long year)   ;
    ExternalReviewDocument findByFlag(int flag);
    ExternalReviewDocument findById(long id);
}
