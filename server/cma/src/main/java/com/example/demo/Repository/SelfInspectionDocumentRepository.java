package com.example.demo.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import com.example.demo.Model.SelfInspectionDocument;
import java.util.List;

public interface SelfInspectionDocumentRepository extends JpaRepository<SelfInspectionDocument, Long>{
    List<SelfInspectionDocument> findAllById(long id);
    SelfInspectionDocument findByFileId(long fileId);
}
