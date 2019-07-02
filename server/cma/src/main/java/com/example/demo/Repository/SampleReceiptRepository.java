package com.example.demo.Repository;
import com.example.demo.Model.SampleReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface SampleReceiptRepository extends JpaRepository<SampleReceipt, Long> {
    SampleReceipt findBySampleId(Long sampleid);
    List<SampleReceipt> findAll();
}
