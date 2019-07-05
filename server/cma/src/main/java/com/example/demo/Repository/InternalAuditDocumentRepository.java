package com.example.demo.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import com.example.demo.Model.InternalAuditDocument;
import java.util.List;

public interface InternalAuditDocumentRepository extends JpaRepository<InternalAuditDocument, Long>{
    List<InternalAuditDocument> findAllByYear(long year);
    InternalAuditDocument findByFileId(long fileId);
    InternalAuditDocument findByYear(long year);
    boolean deleteByFileId(long fileId);
}