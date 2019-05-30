
package com.example.demo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SampleIoRepository extends JpaRepository<SampleIO, Long> {
    SampleIO findBySampleNumber(String sampleNumber);
    SampleIO findBySampleIoId(long sampleId);
    List<SampleIO> findAll();
}