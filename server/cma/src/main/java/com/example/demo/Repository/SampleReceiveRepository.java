
package com.example.demo.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import com.example.demo.Model.SampleReceive;
import java.util.List;

public interface SampleReceiveRepository extends JpaRepository<SampleReceive, Long> {
    SampleReceive findBySampleNumber(String sampleNumber);
    SampleReceive findBySampleId(long sampleId);
    List<SampleReceive> findAll();
}